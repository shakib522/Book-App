package com.example.bookapp.books.entity;

import com.example.bookapp.author.entity.Authors;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long book_id;
    @Column(name = "title")
    private String title;
    @Column(length = Length.LONG32)
    private String description;
    private String image;
    @Column(length = Length.LONG32)
    private String publisher;
    @Column(name = "ratings_count", length = Length.LONG16, columnDefinition = "Double")
    private Double ratingsCount;
    private Integer quantity;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Authors> bookAuthors;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
}
