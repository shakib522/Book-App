package com.example.bookapp.books.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.JdbcTypeCode;

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
    private Integer index;
    @Column(name = "title")
    private String Title;
    @Column(length = Length.LONG32)
    private String description;
    @Column(length = Length.LONG32)
    private String authors;
    private String image;
    @Column(name = "preview_link",length = Length.LONG32)
    private String previewLink;
    @Column(length = Length.LONG32)
    private String publisher;
    @Column(name = "published_date")
    private String publishedDate;
    @Column(name = "info_link",length = Length.LONG32)
    private String infoLink;
    private String categories;
    @Column(name = "ratings_count",length = Length.LONG16,columnDefinition = "Double")
    private Double ratingsCount;
    private Integer quantity;
}
