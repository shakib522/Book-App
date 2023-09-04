package com.example.bookapp.books.service;

import com.example.bookapp.books.entity.Books;
import com.example.bookapp.books.entity.Category;
import com.example.bookapp.books.model.BookRequest;
import com.example.bookapp.books.model.GetAllBookResponse;
import com.example.bookapp.books.repository.BookRepository;
import com.example.bookapp.books.repository.CategoryRepository;
import com.example.bookapp.error.DefaultException;
import com.example.bookapp.error.DefaultMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public GetAllBookResponse getAllBooks(PageRequest pageRequest) {
        Page<Books> bookPages = bookRepository.getAllBooks(pageRequest);
        return GetAllBookResponse
                .builder()
                .totalElements(bookPages.getTotalElements())
                .booksResponseList(bookPages.getContent())
                .currentPage(bookPages.getNumber())
                .totalPages(bookPages.getTotalPages())
                .build();
    }


    @Override
    public DefaultMessage addBook(BookRequest newBook) throws DefaultException {

        Optional<Category> category = categoryRepository.findById(newBook.getCategoryId());
        if (category.isEmpty()) {
            throw new DefaultException("Category not found", 404);
        }
        Books books = Books.builder()
                .title(newBook.getTitle())
                .description(newBook.getDescription())
                .image(newBook.getImage())
                .publisher(newBook.getPublisher())
                .ratingsCount(newBook.getRatingsCount())
                .quantity(newBook.getQuantity())
                .bookAuthors(newBook.getBookAuthors())
                .category(category.get())
                .build();

        bookRepository.save(books);
        return new DefaultMessage("Success", "Book added successfully", 200);
    }

    @Override
    public DefaultMessage addNewCategory(Category category) throws DefaultException {

        String categoryName = categoryRepository.findCategoryName(category.getCategory_name());
        if (categoryName != null) {
            throw new DefaultException("Category already exists", 409);
        }
        categoryRepository.save(category);
        return DefaultMessage
                .builder()
                .message("New Category Added successfully")
                .status("Success")
                .statusCode(200)
                .build();
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAllCategory();
    }

    @Override
    public List<Books> searchBook(String title) {
        return bookRepository.searchBook("%" + title + "%");
    }

    @Override
    public DefaultMessage addNewBook(BookRequest bookRequest) {
        Optional<Category> category = categoryRepository.findById(bookRequest.getCategoryId());
        Books book = Books.builder()
                .title(bookRequest.getTitle())
                .description(bookRequest.getDescription())
                .image(bookRequest.getImage())
                .publisher(bookRequest.getPublisher())
                .ratingsCount(bookRequest.getRatingsCount())
                .quantity(bookRequest.getQuantity())
                .bookAuthors(bookRequest.getBookAuthors())
                .category(category.get())
                .build();
        bookRepository.save(book);
        return DefaultMessage.builder()
                .statusCode(200)
                .message("Book added successfully")
                .status("Success")
                .build();
    }
}
