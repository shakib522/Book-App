package com.example.bookapp.books.service;

import com.example.bookapp.books.entity.Books;
import com.example.bookapp.books.entity.Category;
import com.example.bookapp.books.model.BookRatingsRequest;
import com.example.bookapp.books.model.BookRequest;
import com.example.bookapp.books.model.GetAllBookResponse;
import com.example.bookapp.error.DefaultException;
import com.example.bookapp.error.DefaultMessage;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BookService {
    GetAllBookResponse getAllBooks(PageRequest pageRequest);
    DefaultMessage addBook(BookRequest newBook) throws DefaultException;
    DefaultMessage addNewCategory(Category category) throws DefaultException;
    List<Category> getAllCategory();
    List<Books> searchBook(String title);
    DefaultMessage addNewBook(BookRequest bookRequest);
    DefaultMessage giveRatingToBook(BookRatingsRequest bookRatingsRequest) throws DefaultException;
    List<Double> getTotalRatingsOfABook(Long bookId);

    DefaultMessage deleteCategory(String categoryName) throws DefaultException;

    DefaultMessage editCategory(String categoryName,Category category) throws DefaultException;
}
