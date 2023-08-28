package com.example.bookapp.books.model;

import com.example.bookapp.author.entity.Authors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {
    private String title;
    private String description;
    private String image;
    private String publisher;
    private Double ratingsCount;
    private Integer quantity;
    private List<Authors> bookAuthors;
    private Long categoryId;
}
