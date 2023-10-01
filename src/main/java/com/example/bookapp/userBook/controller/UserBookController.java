package com.example.bookapp.userBook.controller;


import com.example.bookapp.auth.entity.User;
import com.example.bookapp.error.DefaultException;
import com.example.bookapp.error.DefaultMessage;
import com.example.bookapp.userBook.model.AddBookToProfileRequest;
import com.example.bookapp.userBook.model.AllUserBookResponse;
import com.example.bookapp.userBook.model.FindUserBookFromProfileResponse;
import com.example.bookapp.userBook.model.GetUserBookDetailsResponse;
import com.example.bookapp.userBook.service.UserBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserBookController {

    private final UserBookService userBookService;

    @PostMapping("/userBook")
    public ResponseEntity<DefaultMessage> addBookToProfile(@RequestBody AddBookToProfileRequest request) throws DefaultException {
        return ResponseEntity.ok().body(userBookService.addBookToProfile(request));
    }

    @GetMapping("/userBook/{user_id}")
    public ResponseEntity<List<FindUserBookFromProfileResponse>> getAllBookFromAProfile(@PathVariable Long user_id) throws DefaultException {
        return ResponseEntity.ok().body(userBookService.getAllBookFromAProfile(user_id));
    }


    @DeleteMapping("/userBook/{user_book_id}")
    public ResponseEntity<DefaultMessage> deleteBookFromProfile(@PathVariable Long user_book_id) throws DefaultException {
        return ResponseEntity.ok().body(userBookService.deleteBookFromProfile(user_book_id));
    }

    @PutMapping("/userBook/{user_book_id}")
    public ResponseEntity<DefaultMessage> editBookFromProfile(
            @PathVariable Long user_book_id,
            @RequestBody AddBookToProfileRequest request
    ) throws DefaultException {
        return ResponseEntity.ok().body(userBookService.editBookFromProfile(user_book_id, request));
    }

    @GetMapping("/userBook")
    public ResponseEntity<AllUserBookResponse> getAllBookFromAllProfile(
            @RequestParam final Integer pageNumber,
            @RequestParam final Integer pageSize
    ) throws DefaultException {
        return ResponseEntity.ok().body(userBookService.getAllBookFromAllProfile(PageRequest.of(pageNumber, pageSize)));
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<User> getUserInfo(
            @PathVariable Long user_id
    ) throws DefaultException {
        return ResponseEntity.ok().body(userBookService.getUserInfo(user_id));
    }

    @GetMapping("/userBook/category/{category_name}")
    public ResponseEntity<List<FindUserBookFromProfileResponse>> getAllUserBookByCategoryName(
            @PathVariable String category_name
    ) throws DefaultException {
        return ResponseEntity.ok().body(userBookService.getAllUserBookByCategoryName(category_name));
    }

    @GetMapping("/userBook/info")
    public ResponseEntity<List<GetUserBookDetailsResponse>> getUserListOfABook(
            @RequestParam final Long bookId
    ){
        return ResponseEntity.ok().body(userBookService.getUserListOfABook(bookId));
    }
}
