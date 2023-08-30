package com.example.bookapp.userBook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddBookToProfileRequest {
    private Long book_id;
    private String type;
    private String book_condition;
    private Integer price;
    private Long user_id;
}
