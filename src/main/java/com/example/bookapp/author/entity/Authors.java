package com.example.bookapp.author.entity;

import com.example.bookapp.books.entity.Books;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonIgnore
    @ManyToMany(mappedBy = "bookAuthors",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List<Books> books;

}
