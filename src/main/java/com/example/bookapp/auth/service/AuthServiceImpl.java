package com.example.bookapp.auth.service;

import com.example.bookapp.books.entity.Books;
import com.example.bookapp.auth.entity.User;
import com.example.bookapp.auth.model.AuthResponse;
import com.example.bookapp.auth.model.LoginRequest;
import com.example.bookapp.auth.model.RegisterRequest;
import com.example.bookapp.auth.model.Role;
import com.example.bookapp.auth.repository.AuthenticationRepository;
import com.example.bookapp.books.repository.BookRepository;
import com.example.bookapp.config.JwtService;
import com.example.bookapp.error.DefaultException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthenticationRepository repository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    @Override
    public AuthResponse register(RegisterRequest registerRequest)throws DefaultException{
        if(repository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new DefaultException("Email already exist",HttpStatus.BAD_REQUEST.value());
        }
        var user= User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
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
        var user=repository.findByEmail(loginRequest.getEmail());
        if(user.isEmpty()){
            throw new DefaultException("Not found", HttpStatus.NOT_FOUND.value());
        }
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken(user.get());
        return AuthResponse.builder()
                .token(jwtToken)
                .user_id(user.get().getUser_id())
                .build();
    }
}
