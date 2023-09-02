package com.example.bookapp.userBook.service;

import com.example.bookapp.error.DefaultException;
import com.example.bookapp.error.DefaultMessage;
import com.example.bookapp.userBook.entity.UserBook;
import com.example.bookapp.userBook.model.AddBookToProfileRequest;
import com.example.bookapp.userBook.model.AllUserBookResponse;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserBookService {
    DefaultMessage addBookToProfile(AddBookToProfileRequest request) throws DefaultException;

    DefaultMessage deleteBookFromProfile(Long user_book_id) throws DefaultException;

    List<UserBook> getAllBookFromProfile(Long userId) throws DefaultException;

    DefaultMessage editBookFromProfile(Long userBookId, AddBookToProfileRequest request) throws DefaultException;

     AllUserBookResponse getAllBookFromAllProfile(PageRequest pageRequest) throws DefaultException;
}