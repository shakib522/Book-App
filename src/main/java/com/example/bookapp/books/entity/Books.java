package com.example.bookapp.books.entity;

import com.example.bookapp.author.entity.Authors;
import com.example.bookapp.userBook.entity.UserBook;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;
import java.util.ArrayList;
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
    private Long book_id;
    @Column(name = "title",unique = true)
    private String title;
    @Column(length = Length.LONG32)
    private String description;
    private String image;
    @Column(length = Length.LONG32)
    private String publisher;
    @Column(name = "ratings_count", length = Length.LONG16, columnDefinition = "Double")
    private Double ratingsCount;
    private Integer quantity;
    @Column(name = "approved")
    private boolean  approved=false;

    @ManyToMany(targetEntity = Authors.class,fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Authors> bookAuthors;

    public void addAuthor(Authors author) {
        if (bookAuthors == null) {
            bookAuthors = new ArrayList<>();
        }
        if (!bookAuthors.contains(author)) {
            bookAuthors.add(author);
        }
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "book")
    private List<UserBook> userBooks;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "book")
    private List<Ratings> ratings;

}
