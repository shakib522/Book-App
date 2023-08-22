package com.example.bookapp.books.repository;

import com.example.bookapp.books.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books,Integer> {

    @Query(value = "SELECT authors FROM bookApp.books b WHERE b.authors LIKE %?1%",nativeQuery = true)
    List<String> findAllAuthors(String name);

   // @Query(value = )
    //List<String> findAllBooksByAuthorName(String name);

}
