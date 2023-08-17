package com.example.bookapp.books.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "Title")
    private String Title;
    private String description;
    private String authors;
    private String image;
    private String previewLink;
    private String publisher;
    private String publishedDate;
    private String infoLink;
    private String categories;
    @Column(name = "ratingsCount")
    private Double ratingsCount;
}
