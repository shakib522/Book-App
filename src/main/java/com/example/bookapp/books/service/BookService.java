package com.example.bookapp.books.service;

import com.example.bookapp.books.entity.Books;
import com.example.bookapp.books.entity.Category;
import com.example.bookapp.books.model.BookRequest;
import com.example.bookapp.error.DefaultException;
import com.example.bookapp.error.DefaultMessage;

import java.util.List;

public interface BookService {
    List<Books> getAllBooks();
    List<String> getAllAuthors(String name);
    DefaultMessage addBook(BookRequest newBook) throws DefaultException;
    DefaultMessage addNewCategory(Category category) throws DefaultException;
    List<Category> getAllCategory();
}
