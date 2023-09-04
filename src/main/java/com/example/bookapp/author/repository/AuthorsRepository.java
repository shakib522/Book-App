package com.example.bookapp.author.repository;

import com.example.bookapp.author.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorsRepository extends JpaRepository<Authors,Long> {

    @Query(value = "SELECT * FROM bookApp.authors a where a.author_name LIKE ?1",nativeQuery = true)
    List<Authors> searchAuthors(String name);
}
