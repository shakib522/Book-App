package com.example.bookapp.auth.model;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String name;
    private String email;
    @Size(min = 6,max = 60,message = "Password size at least 6")
    private String password;
    @Size(min = 6,max = 60,message = "Password size at least 6")
    private String confirmPassword;
}
