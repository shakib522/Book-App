package com.example.bookapp.author.entity;

import com.example.bookapp.books.entity.Books;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long author_id;
    private String author_name;
    @ManyToMany(mappedBy = "bookAuthors",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List<Books> books;

}
