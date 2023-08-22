package com.example.bookapp.userBook;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;
import org.hibernate.sql.results.graph.entity.LoadingEntityEntry;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_book")
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_book_id;
    @Column(length = Length.LONG16)
    private String type;
    @Column(length = Length.LONG16)
    private String book_condition;
    private Integer price;
    private Long user_id;
    private Long book_id;
}
