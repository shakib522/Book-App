package com.example.bookapp.userBook.repository;

import com.example.bookapp.userBook.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserBookRepository extends JpaRepository<UserBook,Long> {

    @Query(value="SELECT * FROM user_book u WHERE u.book_id = ?1 and u.user_id = ?2",nativeQuery = true )
    Optional<UserBook> findDuplicateEntry(Long book_id,Long user_id);
}
