package com.example.bookapp.swapRequest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwapRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long swap_request_id;
    private Long from_user_id;
    private Long to_user_id;
    private Long book_id;
    private Long user_book_id;
    private String status;//accepted or rejected
}
