package com.example.bookapp.books.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRatingsRequest {
    private Long book_id;
    private Double rating;
    private Long user_id;
}
