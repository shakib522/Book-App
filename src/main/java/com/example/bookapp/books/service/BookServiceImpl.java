package com.example.bookapp.books.service;

import com.example.bookapp.books.entity.Books;
import com.example.bookapp.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    @Override
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }
}
