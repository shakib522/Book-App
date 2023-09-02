package com.example.bookapp.books.controller;


import com.example.bookapp.books.entity.Category;
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
    public ResponseEntity<DefaultMessage> addBook(
            @RequestBody BookRequest newBook
    ) throws DefaultException {
        return ResponseEntity.ok().body(bookService.addBook(newBook));
    }

    @GetMapping("/authors/{name}")
    public ResponseEntity<List<String>> getAllAuthors(@PathVariable("name") String name){
        return ResponseEntity.ok().body(bookService.getAllAuthors(name));
    }

    @PostMapping("/admin/category")
    public ResponseEntity<DefaultMessage> addCategory(@RequestBody Category category) throws DefaultException {
        return ResponseEntity.ok().body(bookService.addNewCategory(category));
    }

    @GetMapping("/books/category")
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.ok().body(bookService.getAllCategory());
    }

    public ResponseEntity<?> searchBook(
            @RequestParam final String title
    ){
        return null;
    }
}
