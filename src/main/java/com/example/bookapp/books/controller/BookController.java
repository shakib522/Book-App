package com.example.bookapp.books.controller;


import com.example.bookapp.books.entity.Books;
import com.example.bookapp.books.entity.Category;
import com.example.bookapp.books.model.BookRatingsRequest;
import com.example.bookapp.books.model.BookRequest;
import com.example.bookapp.books.model.GetAllBookResponse;
import com.example.bookapp.books.model.GetTotalRatingsOfABook;
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
@CrossOrigin(origins = "*")
public class BookController {
    private final BookService bookService;


    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok().body("Welcome to Bookmark");
    }

    @GetMapping("/books")
    public GetAllBookResponse getAllBooks(
            @RequestParam final Integer pageNumber,
            @RequestParam final Integer pageSize
    ) {
        return bookService.getAllBooks(PageRequest.of(pageNumber, pageSize));
    }

    @PostMapping("/books/add-new-book")
    public ResponseEntity<DefaultMessage> requestForBook(
            @RequestBody BookRequest newBook
    ) throws DefaultException {
        return ResponseEntity.ok().body(bookService.requestForBook(newBook));
    }


    @PostMapping("/admin/category")
    public ResponseEntity<DefaultMessage> addCategory(@RequestBody Category category) throws DefaultException {
        return ResponseEntity.ok().body(bookService.addNewCategory(category));
    }

    @PostMapping("/admin/books")
    public ResponseEntity<DefaultMessage> addNewBook(
            @RequestBody BookRequest books
    ) throws DefaultException {
        return ResponseEntity.ok().body(bookService.addNewBook(books));
    }

    @GetMapping("/books/category")
    public ResponseEntity<List<Category>> getAllCategory() {
        return ResponseEntity.ok().body(bookService.getAllCategory());
    }

    @DeleteMapping("/admin/category")
    public ResponseEntity<DefaultMessage> deleteCategory(
            @RequestParam final String categoryName
    ) throws DefaultException {
        return ResponseEntity.ok().body(bookService.deleteCategory(categoryName));
    }

    @PutMapping("/admin/category")
    public ResponseEntity<DefaultMessage> editCategory(
            @RequestParam final String categoryName,
            @RequestBody Category category
    ) throws DefaultException {
        return ResponseEntity.ok().body(bookService.editCategory(categoryName,category));
    }


    @GetMapping("/books/search")
    public ResponseEntity<List<Books>> searchBook(
            @RequestParam final String title
    ) {
        return ResponseEntity.ok().body(bookService.searchBook(title));
    }

    @PostMapping("/books/rating")
    public ResponseEntity<DefaultMessage> giveRatingToBook(
            @RequestBody BookRatingsRequest bookRatingsRequest
    ) throws DefaultException {
        return ResponseEntity.ok().body(bookService.giveRatingToBook(bookRatingsRequest));
    }

    @GetMapping("/books/rating")
    public ResponseEntity<GetTotalRatingsOfABook> getTotalRatingsOfABook(
            @RequestParam final Long bookId
    ) {
        List<Double> ratings = bookService.getTotalRatingsOfABook(bookId);
        Double totalRating = 0.0;
        for (Double rating : ratings) {
            totalRating += rating;
        }
        Double averageRating = totalRating / ratings.size();
        return ResponseEntity.ok().body(GetTotalRatingsOfABook.builder()
                .totalRatings(averageRating)
                .build());
    }

    @GetMapping("/admin/books/pending")
    public ResponseEntity<List<Books>> getPendingBooks(){
        return ResponseEntity.ok().body(bookService.getPendingBooks());
    }

    @PutMapping("/admin/books/pending")
    public ResponseEntity<DefaultMessage> editPendingBooks(
            @RequestBody Books books,
            @RequestParam final Long bookId
    ) throws DefaultException {
        return ResponseEntity.ok().body(bookService.editPendingBooks(books,bookId));
    }
}
