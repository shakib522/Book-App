package com.example.bookapp.userBook.service;


import com.example.bookapp.auth.entity.User;
import com.example.bookapp.auth.repository.AuthenticationRepository;
import com.example.bookapp.books.entity.Books;
import com.example.bookapp.books.repository.BookRepository;
import com.example.bookapp.error.DefaultException;
import com.example.bookapp.error.DefaultMessage;
import com.example.bookapp.userBook.entity.UserBook;
import com.example.bookapp.userBook.model.AddBookToProfileRequest;
import com.example.bookapp.userBook.repository.UserBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserBookServiceImpl implements UserBookService {
    private final UserBookRepository userBookRepository;
    private final AuthenticationRepository authRepository;
    private final BookRepository bookRepository;

    @Override
    public DefaultMessage addBookToProfile(AddBookToProfileRequest request) throws DefaultException {
        Optional<User> user = authRepository.findUserById(request.getUser_id());
        if (user.isEmpty()) {
            throw new DefaultException("User not found", 404);
        }

        Optional<Books> books = bookRepository.findBookById(request.getBook_id());
        if (books.isEmpty()) {
            throw new DefaultException("Book not found", 404);
        }
        Optional<UserBook> userBookCheck = userBookRepository.findDuplicateEntry(request.getBook_id(),request.getUser_id());
        if(userBookCheck.isPresent()){
            throw new DefaultException("Book already added to profile", 409);
        }
        var userBook = UserBook.builder()
                .book(books.get())
                .user(user.get())
                .type(request.getType())
                .book_condition(request.getBook_condition())
                .price(request.getPrice())
                .build();
        userBookRepository.save(userBook);
        return new DefaultMessage("Success", "Book added to profile", 200);
    }

    @Override
    public DefaultMessage deleteBookFromProfile(Long user_book_id) throws DefaultException {

        Optional<UserBook> userBook = userBookRepository.findUserBookById(user_book_id);

        if (userBook.isEmpty()) {
            throw new DefaultException("Book not found in profile", 404);
        }

        userBookRepository.deleteById(user_book_id);

        return DefaultMessage.builder()
                .statusCode(200)
                .status("Success")
                .message("Book deleted from profile")
                .build();
    }

    @Override
    public List<UserBook> getAllBookFromProfile(Long userId) {

        Optional<List<UserBook>> userBookList =userBookRepository.findAllBookOfUser(userId);
        return userBookList.orElse(Collections.emptyList());

    }
}
