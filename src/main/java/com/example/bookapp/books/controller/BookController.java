package com.example.bookapp.books.controller;


import com.example.bookapp.books.entity.Books;
import com.example.bookapp.books.entity.Category;
import com.example.bookapp.books.model.BookRatingsRequest;
import com.example.bookapp.books.model.BookRequest;
import com.example.bookapp.books.model.GetAllBookResponse;
import com.example.bookapp.books.service.BookService;
import com.example.bookapp.error.DefaultException;
import com.example.bookapp.error.DefaultMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;


    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok().body("Welcome to Bookmark");
    }

    @GetMapping("/books")
    public GetAllBookResponse getAllBooks(
            @RequestParam final Integer pageNumber,
            @RequestParam final Integer pageSize
    ){
        return bookService.getAllBooks(PageRequest.of(pageNumber,pageSize));
    }

    @PostMapping("/books/add-new-book")
    public ResponseEntity<DefaultMessage> requestForBook(
            @RequestBody BookRequest newBook
    ) throws DefaultException {
        return ResponseEntity.ok().body(bookService.addBook(newBook));
    }



    @PostMapping("/admin/category")
    public ResponseEntity<DefaultMessage> addCategory(@RequestBody Category category) throws DefaultException {
        return ResponseEntity.ok().body(bookService.addNewCategory(category));
    }

    @PostMapping("/admin/books")
    public ResponseEntity<DefaultMessage> addNewBook(
            @RequestBody BookRequest books
    ){
        return ResponseEntity.ok().body(bookService.addNewBook(books));
    }

    @GetMapping("/books/category")
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.ok().body(bookService.getAllCategory());
    }

    @GetMapping("/books/search")
    public ResponseEntity<List<Books>> searchBook(
            @RequestParam final String title
    ){
        return ResponseEntity.ok().body(bookService.searchBook(title));
    }

    @PostMapping("/books/rating")
    public ResponseEntity<DefaultMessage> giveRatingToBook(
            @RequestBody BookRatingsRequest bookRatingsRequest
    ) throws DefaultException {
        return ResponseEntity.ok().body(bookService.giveRatingToBook(bookRatingsRequest));
    }
}
