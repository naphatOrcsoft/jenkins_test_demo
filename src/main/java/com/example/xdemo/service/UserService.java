package com.example.xdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.xdemo.dto.UserDto;
import com.example.xdemo.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    // ✅ Register new user
    public UserDto register(String name, String email) {
        UserDto user = new UserDto(null, name, email);
        return repo.save(user);
    }

    // ✅ Update user's name by ID
    public UserDto updateName(Long id, String newName) {
        UserDto user = repo.findById(id)
                              .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(newName);
        return repo.save(user);
    }

    // ✅ Find by email
    public UserDto findByEmail(String email) {
        return repo.findByEmail(email)
                   .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ✅ Delete user
    public void deleteUser(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        repo.deleteById(id);
    }

    public String getAlteredEmail(String username) {
        UserDto user = repo.findByName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String email = user.getEmail();
        if (email == null || email.indexOf('@') < 1) {
            throw new IllegalArgumentException("Invalid email for user: " + username);
        }
        String local = email.substring(0, email.indexOf('@'));
        return local + ":mail";
    }
}
