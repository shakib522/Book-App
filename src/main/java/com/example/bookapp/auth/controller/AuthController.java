package com.example.bookapp.auth.controller;

import com.example.bookapp.auth.model.AuthResponse;
import com.example.bookapp.auth.model.LoginRequest;
import com.example.bookapp.auth.model.PasswordChangeRequest;
import com.example.bookapp.auth.model.RegisterRequest;
import com.example.bookapp.auth.service.AuthService;
import com.example.bookapp.error.DefaultException;
import com.example.bookapp.error.DefaultMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
             @RequestBody RegisterRequest registerRequest
    ) throws DefaultException {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest loginRequest
    ) throws DefaultException {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<DefaultMessage> changePassword(
            @RequestBody PasswordChangeRequest request
            ) throws DefaultException {

        return ResponseEntity.ok(authService.changePassword(request));

    }



}
