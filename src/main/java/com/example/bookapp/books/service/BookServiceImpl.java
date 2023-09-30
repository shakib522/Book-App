package com.example.bookapp.books.service;

import com.example.bookapp.auth.entity.User;
import com.example.bookapp.auth.repository.AuthenticationRepository;
import com.example.bookapp.author.entity.Authors;
import com.example.bookapp.author.repository.AuthorsRepository;
import com.example.bookapp.books.entity.Books;
import com.example.bookapp.books.entity.Category;
import com.example.bookapp.books.entity.Ratings;
import com.example.bookapp.books.model.BookRatingsRequest;
import com.example.bookapp.books.model.BookRequest;
import com.example.bookapp.books.model.GetAllBookResponse;
import com.example.bookapp.books.repository.BookRepository;
import com.example.bookapp.books.repository.CategoryRepository;
import com.example.bookapp.books.repository.RatingsRepository;
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
    private final RatingsRepository ratingsRepository;
    private final AuthorsRepository authorsRepository;
    private final AuthenticationRepository authenticationRepository;

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
    public DefaultMessage requestForBook(BookRequest newBook) throws DefaultException {

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
                .category(category.get())
                .build();

        newBook.getBookAuthors().forEach(authors -> {
            Optional<Authors> authorsOptional = authorsRepository.findAuthorByName(authors.getAuthor_name());
            if (authorsOptional.isEmpty()) {
                authorsRepository.save(authors);
                books.addAuthor(authors);
            } else {
                books.addAuthor(authorsOptional.get());
            }
        });
        bookRepository.save(books);
        return new DefaultMessage("Success", "Book request send successfully", 200);
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
    public DefaultMessage addNewBook(BookRequest bookRequest) throws DefaultException {

        Optional<Category> category = categoryRepository.findById(bookRequest.getCategoryId());
        if (category.isEmpty()) {
            throw new DefaultException("Category not found", 404);
        }
        Books books = Books.builder()
                .title(bookRequest.getTitle())
                .description(bookRequest.getDescription())
                .image(bookRequest.getImage())
                .publisher(bookRequest.getPublisher())
                .ratingsCount(bookRequest.getRatingsCount())
                .quantity(bookRequest.getQuantity())
                .approved(true)
                .category(category.get())
                .build();

        bookRequest.getBookAuthors().forEach(authors -> {
            Optional<Authors> authorsOptional = authorsRepository.findAuthorByName(authors.getAuthor_name());
            if (authorsOptional.isEmpty()) {
                authorsRepository.save(authors);
                books.addAuthor(authors);
            } else {
                books.addAuthor(authorsOptional.get());
            }
        });
        bookRepository.save(books);
        return DefaultMessage.builder()
                .statusCode(200)
                .message("Book added successfully")
                .status("Success")
                .build();
    }

    @Override
    public DefaultMessage giveRatingToBook(BookRatingsRequest bookRatingsRequest) throws DefaultException {
        Optional<User> user = authenticationRepository.findUserById(bookRatingsRequest.getUser_id());

        if (user.isEmpty()) {
            throw new DefaultException("User not found", 404);
        }

        Optional<Books> books = bookRepository.findBookById(bookRatingsRequest.getBook_id());
        if (books.isEmpty()) {
            throw new DefaultException("Book not found", 404);
        }

        Optional<Ratings> ratingsOptional = ratingsRepository.findDuplicateEntry(bookRatingsRequest.getBook_id(), bookRatingsRequest.getUser_id());
        if(ratingsOptional.isPresent()){
            throw new DefaultException("You have already rated this book",409);
        }
        Ratings ratings = Ratings.builder()
                .rating(bookRatingsRequest.getRating())
                .user(user.get())
                .book(books.get())
                .build();
        ratingsRepository.save(ratings);
        return DefaultMessage.builder()
                .statusCode(200)
                .message("Rating added successfully")
                .status("Success")
                .build();
    }

    @Override
    public List<Double> getTotalRatingsOfABook(Long bookId) {

        return ratingsRepository.getRatingOfABook(bookId);
    }

    @Override
    public DefaultMessage deleteCategory(String categoryName) throws DefaultException {


        Optional<Category> categoryOptional = categoryRepository.findCategoryByName(categoryName);
        if (categoryOptional.isEmpty()) {
            throw new DefaultException("Category not found", 404);
        }
        categoryRepository.delete(categoryOptional.get());
        return DefaultMessage.builder()
                .message("Category deleted successfully")
                .statusCode(200)
                .status("Success")
                .build();
    }

    @Override
    public DefaultMessage editCategory(String categoryName, Category category) throws DefaultException {
        Optional<Category> categoryOptional = categoryRepository.findCategoryByName(categoryName);
        if (categoryOptional.isEmpty()) {
            throw new DefaultException("Category not found", 404);
        }
        categoryOptional.get().setCategory_name(category.getCategory_name());
        categoryRepository.save(categoryOptional.get());
        return DefaultMessage.builder()
                .message("Category edited successfully")
                .statusCode(200)
                .status("Success")
                .build();
    }

    @Override
    public List<Books> getPendingBooks() {
        return bookRepository.getPendingBooks();
    }

    @Override
    public DefaultMessage editPendingBooks(Books newBooks, Long bookId) throws DefaultException {
        Optional<Books> books = bookRepository.findById(bookId);
        if (books.isEmpty()) {
            throw new DefaultException("Book not found", 404);
        }
        Books book = books.get();
        book.setImage(newBooks.getImage());
        book.setQuantity(newBooks.getQuantity());
        book.setRatingsCount(newBooks.getRatingsCount());
        book.setPublisher(newBooks.getPublisher());
        book.setDescription(newBooks.getDescription());
        book.setTitle(newBooks.getTitle());
        book.setApproved(newBooks.isApproved());
        bookRepository.save(book);
        return DefaultMessage.builder()
                .message("Book approved successfully")
                .status("Success")
                .statusCode(200)
                .build();
    }

    @Override
    public List<Books> getAllBookByCategoryId(Long categoryId) throws DefaultException {
        if(categoryRepository.findById(categoryId).isEmpty()){
            throw new DefaultException("Category not found",404);
        }
        if (bookRepository.getAllBookByCategoryId(categoryId).isEmpty()){
            throw new DefaultException("No books found",404);
        }
        return bookRepository.getAllBookByCategoryId(categoryId).get();
    }

    @Override
    public List<Authors> getAuthorsOfABook(Long bookId) throws DefaultException{
        Optional<Books> books=bookRepository.findBookById(bookId);
        if(books.isPresent()){
            return books.get().getBookAuthors();
        }else{
            throw new DefaultException("Book not found",404);
        }
    }
}
