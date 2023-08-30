package com.example.bookapp.userBook.controller;


import com.example.bookapp.error.DefaultException;
import com.example.bookapp.error.DefaultMessage;
import com.example.bookapp.userBook.entity.UserBook;
import com.example.bookapp.userBook.model.AddBookToProfileRequest;
import com.example.bookapp.userBook.service.UserBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserBookController {

    private final UserBookService userBookService;

    @PostMapping("/userBook")
    public ResponseEntity<DefaultMessage> addBookToProfile(@RequestBody AddBookToProfileRequest request) throws DefaultException {
        return ResponseEntity.ok().body(userBookService.addBookToProfile(request));
    }

}
