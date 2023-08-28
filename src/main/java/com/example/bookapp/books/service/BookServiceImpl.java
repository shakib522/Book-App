package com.example.bookapp.books.service;

import com.example.bookapp.books.entity.Books;
import com.example.bookapp.books.entity.Category;
import com.example.bookapp.books.model.BookRequest;
import com.example.bookapp.books.repository.BookRepository;
import com.example.bookapp.books.repository.CategoryRepository;
import com.example.bookapp.error.DefaultException;
import com.example.bookapp.error.DefaultMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<String> getAllAuthors(String name) {
        return bookRepository.findAllAuthors(name);
    }

    @Override
    public DefaultMessage addBook(BookRequest newBook) {
        Books books= Books.builder()
                .title(newBook.getTitle())
                .description(newBook.getDescription())
                .image(newBook.getImage())
                .publisher(newBook.getPublisher())
                .ratingsCount(newBook.getRatingsCount())
                .quantity(newBook.getQuantity())
                .bookAuthors(newBook.getBookAuthors())
                .build();

        bookRepository.save(books);
        return new DefaultMessage("Success", "Book added successfully", 200);
    }

    @Override
    public DefaultMessage addNewCategory(Category category) throws DefaultException {

        String categoryName = categoryRepository.findCategoryName(category.getCategory_name());
        if(categoryName != null){
            throw new DefaultException("Category already exists",409);
        }
        categoryRepository.save(category);
        return DefaultMessage
                .builder()
                .message("New Category Added successfully")
                .status("Success")
                .statusCode(200)
                .build();
    }
}
