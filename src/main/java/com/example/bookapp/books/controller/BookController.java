package com.example.bookapp.books.controller;


import com.example.bookapp.books.entity.Books;
import com.example.bookapp.auth.service.AuthService;
import com.example.bookapp.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public List<Books> getAllBooks(){
        return bookService.getAllBooks();
    }
}
