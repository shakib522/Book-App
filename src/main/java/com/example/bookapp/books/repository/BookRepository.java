package com.example.bookapp.books.repository;

import com.example.bookapp.books.entity.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Books,Long> {

    @Query(value="SELECT * FROM bookApp.books b where b.book_id=?1 and b.approved=true",nativeQuery = true)
    Optional<Books> findBookById(Long book_id);

    @Query(value = "SELECT * FROM bookApp.books b where b.approved=true",nativeQuery = true)
    Page<Books> getAllBooks(PageRequest pageRequest);

    @Query(value = "SELECT * FROM bookApp.books b WHERE b.title LIKE ?1 and b.approved=true",nativeQuery = true)
    List<Books> searchBook(String title);

    @Query(value = "SELECT * FROM bookApp.books b WHERE b.approved=false",nativeQuery = true)
    List<Books> getPendingBooks();


    @Query(value = "SELECT * FROM bookApp.books b WHERE b.title=?1",nativeQuery = true)
    Optional<Books> findBookByTitle(String title);
}
