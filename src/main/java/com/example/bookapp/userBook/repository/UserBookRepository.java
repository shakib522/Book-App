package com.example.bookapp.userBook.repository;

import com.example.bookapp.userBook.entity.UserBook;
import com.example.bookapp.userBook.model.FindUserBookFromProfileResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserBookRepository extends JpaRepository<UserBook,Long>{

    @Query(value="SELECT * FROM bookApp.user_book u WHERE u.book_id = ?1 and u.user_id = ?2",nativeQuery = true )
    Optional<UserBook> findDuplicateEntry(Long book_id,Long user_id);

    @Query(value="SELECT * FROM bookApp.user_book u WHERE u.user_book_id = ?1",nativeQuery = true )
    Optional<UserBook> findUserBookById(Long user_book_id);

    @Query(value="SELECT * FROM bookApp.user_book u join bookApp.books b on u.book_id=b.book_id where u.user_id=?1",nativeQuery = true )
    Optional<List<FindUserBookFromProfileResponse>> findAllBookOfAUser(Long user_id);

    @Query(value="SELECT * FROM bookApp.user_book u join bookApp.books b on u.book_id=b.book_id",nativeQuery = true )
    Page<FindUserBookFromProfileResponse> findAllUserBook(PageRequest pageRequest);

    @Query(value="SELECT * FROM bookApp.user_book u join bookApp.books b on u.book_id=b.book_id where b.category_id=?1",nativeQuery = true )
    List<FindUserBookFromProfileResponse> findAllUserBookByCategoryId(Long categoryId);

}
