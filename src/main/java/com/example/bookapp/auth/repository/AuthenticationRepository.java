package com.example.bookapp.auth.repository;

import com.example.bookapp.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthenticationRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Query(value="SELECT * FROM bookApp.user u WHERE u.user_id = ?1",nativeQuery = true )
    Optional<User> findUserById(Long Id);
}
