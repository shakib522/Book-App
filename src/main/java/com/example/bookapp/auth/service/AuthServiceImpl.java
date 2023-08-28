package com.example.bookapp.auth.service;

import com.example.bookapp.auth.entity.User;
import com.example.bookapp.auth.model.*;
import com.example.bookapp.auth.repository.AuthenticationRepository;
import com.example.bookapp.config.JwtService;
import com.example.bookapp.error.DefaultException;
import com.example.bookapp.error.DefaultMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationRepository repository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    @Override
    public AuthResponse register(RegisterRequest registerRequest) throws DefaultException {
        if (repository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new DefaultException("Email already exist", HttpStatus.BAD_REQUEST.value());
        }
        var user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .area(registerRequest.getArea())
                .upazila(registerRequest.getUpazila())
                .phoneNumber(registerRequest.getPhoneNumber())
                .district(registerRequest.getDistrict())
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .user_id(user.getUser_id())
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) throws DefaultException {
        var user = repository.findByEmail(loginRequest.getEmail());
        if (user.isEmpty()) {
            throw new DefaultException("Not found", HttpStatus.NOT_FOUND.value());
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            throw new DefaultException("Password is incorrect", HttpStatus.BAD_REQUEST.value());
        }
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword(),
                        user.get().getAuthorities()
                )
        );
        var jwtToken = jwtService.generateToken(user.get());
        return AuthResponse.builder()
                .token(jwtToken)
                .user_id(user.get().getUser_id())
                .build();
    }

    @Override
    public DefaultMessage changePassword(PasswordChangeRequest request) throws DefaultException {
        Optional<User> user = repository.findByEmail(request.getEmail());
        System.out.println("after user field line" + user.isPresent());
        if (user.isEmpty()) {
            throw new DefaultException("User not found with this email", HttpStatus.NOT_FOUND.value());
        }
        if (!passwordEncoder.matches(request.getOldPassword(), user.get().getPassword())) {
            throw new DefaultException("Password is incorrect", HttpStatus.BAD_REQUEST.value());
        }
        System.out.println("before set password");
        user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(user.get());
        return DefaultMessage.builder()
                .message("Password changed successfully")
                .status("Success")
                .statusCode(200)
                .build();
    }
}
