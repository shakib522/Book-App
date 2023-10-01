package com.example.bookapp.books.repository;

import com.example.bookapp.books.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Long> {

    @Query(value = "SELECT rating FROM bookApp.ratings r WHERE r.book_id=?1",nativeQuery = true)
    List<Double> getRatingOfABook(Long book_id);

    @Query(value = "SELECT * FROM bookApp.ratings r WHERE r.book_id=?1 and r.user_id=?2",nativeQuery = true)
    Optional<Ratings> findDuplicateEntry(Long bookId, Long userId);
}
