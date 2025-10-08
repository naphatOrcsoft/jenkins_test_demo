package com.example.xdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.xdemo.dto.UserDto;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Long> {
  Optional<UserDto> findByEmail(String email);
  Optional<UserDto> findByName(String name);
}
