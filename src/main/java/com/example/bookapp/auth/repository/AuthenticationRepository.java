package com.example.bookapp.auth.repository;

import com.example.bookapp.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
