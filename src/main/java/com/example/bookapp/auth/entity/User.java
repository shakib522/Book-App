package com.example.bookapp.auth.entity;

import com.example.bookapp.auth.model.Role;
import com.example.bookapp.books.entity.Ratings;
import com.example.bookapp.userBook.entity.UserBook;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user",uniqueConstraints = {
        @UniqueConstraint(name = "email", columnNames = {"email"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;
    private String name;
    @Column(name = "email",unique = true)
    private String email;
    @Getter
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    @NotBlank(message = "District can not empty")
    private String district;
    @NotBlank(message = "Upozila can not empty")
    private String upazila;
    @NotBlank(message = "Area can not empty")
    private String area;
    @NotBlank(message = "Phone number can not empty")
    private String phoneNumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<UserBook> userBooks;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Ratings> ratings;
}
