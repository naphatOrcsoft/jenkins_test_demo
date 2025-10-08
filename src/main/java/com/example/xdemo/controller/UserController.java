package com.example.xdemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.xdemo.dto.UserDto;
import com.example.xdemo.request.CreateUserRequest;
import com.example.xdemo.request.UpdateNameRequest;
import com.example.xdemo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService svc;

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody CreateUserRequest req) {
        UserDto saved = svc.register(req.getName(), req.getEmail());
        return ResponseEntity.ok(saved);
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<UserDto> updateName(@PathVariable Long id,
                                                 @RequestBody UpdateNameRequest req) {
        UserDto updated = svc.updateName(id, req.getName());
        return ResponseEntity.ok(updated);
    }

    /** GET /api/users/{name}/altered-email -> "local:mail" */
    @GetMapping("/{name}/altered-email")
    public ResponseEntity<String> getAlteredEmail(@PathVariable("name") String name) {
        String result = svc.getAlteredEmail(name);
        return ResponseEntity.ok(result);
    }


   
}

