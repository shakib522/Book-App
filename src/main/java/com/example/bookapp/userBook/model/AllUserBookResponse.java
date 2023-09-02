package com.example.bookapp.userBook.model;


import com.example.bookapp.userBook.entity.UserBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllUserBookResponse {
    private Integer totalPages;
    private Long totalElements;
    private Integer currentPage;
    private List<FindUserBookFromProfileResponse> userBookList;
}
