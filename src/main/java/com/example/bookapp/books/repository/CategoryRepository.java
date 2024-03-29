package com.example.bookapp.books.repository;

import com.example.bookapp.books.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value="SELECT category_name from bookApp.category where category_name=?1",nativeQuery = true)
    String findCategoryName(String name);
    @Query(value="SELECT category_id,category_name from bookApp.category",nativeQuery = true)
    List<Category> findAllCategory();

    @Query(value = "SELECT * from bookApp.category where category_name=?1",nativeQuery = true)
    Optional<Category> findCategoryByName(String categoryName);

    @Query(value = "SELECT category_id from bookApp.category where category_name=?1",nativeQuery = true)
    Optional<Long> findCategoryIdByName(String categoryName);
}
