package com.example.bookapp.userBook.entity;


import com.example.bookapp.auth.entity.User;
import com.example.bookapp.books.entity.Books;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_book")
public class UserBook {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_book_id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private Books book;

    @NotNull
    @Column(length = Length.LONG16)
    private String type;
    @NotNull
    @Column(length = Length.LONG16)
    @NotBlank(message = "Book condition can not empty")
    private String book_condition;
    @NotNull
    private Integer price;
}
