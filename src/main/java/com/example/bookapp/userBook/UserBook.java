package com.example.bookapp.userBook;


import com.example.bookapp.auth.entity.User;
import com.example.bookapp.books.entity.Books;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    @Column(length = Length.LONG16)
    private String type;
    @Column(length = Length.LONG16)
    private String book_condition;
    private Integer price;
}
