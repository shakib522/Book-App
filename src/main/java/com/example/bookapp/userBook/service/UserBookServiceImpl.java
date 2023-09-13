package com.example.bookapp.userBook.service;


import com.example.bookapp.auth.entity.User;
import com.example.bookapp.auth.repository.AuthenticationRepository;
import com.example.bookapp.books.entity.Books;
import com.example.bookapp.books.repository.BookRepository;
import com.example.bookapp.error.DefaultException;
import com.example.bookapp.error.DefaultMessage;
import com.example.bookapp.userBook.entity.UserBook;
import com.example.bookapp.userBook.model.AddBookToProfileRequest;
import com.example.bookapp.userBook.model.AllUserBookResponse;
import com.example.bookapp.userBook.model.FindUserBookFromProfileResponse;
import com.example.bookapp.userBook.repository.UserBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        Optional<UserBook> userBookCheck = userBookRepository.findDuplicateEntry(request.getBook_id(), request.getUser_id());
        if (userBookCheck.isPresent()) {
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
    public List<FindUserBookFromProfileResponse> getAllBookFromAProfile(Long userId) {

        Optional<List<FindUserBookFromProfileResponse>> userBookList = userBookRepository.findAllBookOfAUser(userId);
        return userBookList.orElse(Collections.emptyList());

    }

    @Override
    public DefaultMessage editBookFromProfile
            (Long userBookId, AddBookToProfileRequest request) throws DefaultException {

        Optional<UserBook> userBook = userBookRepository.findUserBookById(userBookId);

        if (userBook.isEmpty()) {
            throw new DefaultException("Book not found in profile", 404);
        }

        UserBook userBookFromDB = userBook.get();
        userBookFromDB.setBook_condition(request.getBook_condition());
        userBookFromDB.setPrice(request.getPrice());
        userBookFromDB.setType(request.getType());
        userBookRepository.save(userBookFromDB);

        return DefaultMessage.builder()
                .status("Success")
                .statusCode(200)
                .message("Book edited")
                .build();
    }

    @Override
    public AllUserBookResponse getAllBookFromAllProfile(PageRequest pageRequest) throws DefaultException {
        Page<FindUserBookFromProfileResponse> userBookPage = userBookRepository.findAllUserBook(pageRequest);
        return AllUserBookResponse.builder()
                .userBookList(userBookPage.getContent())
                .totalPages(userBookPage.getTotalPages())
                .totalElements(userBookPage.getTotalElements())
                .currentPage(userBookPage.getNumber())
                .build();
    }

    @Override
    public User getUserInfo(Long userId) throws DefaultException {
        Optional<User> user = authRepository.findUserById(userId);
        if(user.isEmpty()){
            throw new DefaultException("User not found",404);
        }
        return user.get();
    }
}
