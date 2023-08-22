package com.example.bookapp.books.service;

import com.example.bookapp.books.entity.Books;
import java.util.List;

public interface BookService {
    List<Books> getAllBooks();
    List<String> getAllAuthors(String name);
}
