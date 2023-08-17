package com.example.bookapp.auth.service;

import com.example.bookapp.books.entity.Books;
import com.example.bookapp.auth.model.AuthResponse;
import com.example.bookapp.auth.model.LoginRequest;
import com.example.bookapp.auth.model.RegisterRequest;
import com.example.bookapp.error.DefaultException;

import java.util.List;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest) throws DefaultException;
    AuthResponse login(LoginRequest loginRequest) throws DefaultException;
}
