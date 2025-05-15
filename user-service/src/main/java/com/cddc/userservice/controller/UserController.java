package com.cddc.userservice.controller;

import com.cddc.userservice.dto.UserRegistrationDto;
import com.cddc.userservice.model.User;
import com.cddc.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto) {
        User newUser = userService.registerUser(registrationDto);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto loginDto) {
        // Authentication logic
        return ResponseEntity.ok(jwtTokenProvider.generateToken(authentication));
    }
}
