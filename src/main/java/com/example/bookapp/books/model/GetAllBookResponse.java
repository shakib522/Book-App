package com.example.bookapp.books.model;


import com.example.bookapp.books.entity.Books;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllBookResponse {
    private Integer totalPages;
    private Long totalElements;
    private Integer currentPage;
    private List<Books> booksResponseList;
}
