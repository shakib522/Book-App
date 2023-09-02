package com.example.bookapp.books.repository;

import com.example.bookapp.books.entity.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Books,Integer> {

    @Query(value = "SELECT authors FROM bookApp.books b WHERE b.authors LIKE %?1%",nativeQuery = true)
    List<String> findAllAuthors(String name);


    @Query(value="SELECT * FROM books where book_id=?1",nativeQuery = true)
    Optional<Books> findBookById(Long book_id);

    @Query(value = "SELECT * FROM books",nativeQuery = true)
    Page<Books> getAllBooks(PageRequest pageRequest);

   // @Query(value = )
    //List<String> findAllBooksByAuthorName(String name);

}
