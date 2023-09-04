package com.example.bookapp.books.repository;

import com.example.bookapp.books.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingsRepository extends JpaRepository<Ratings, Long> {

}
