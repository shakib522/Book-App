package com.example.bookapp.author.service;

import com.example.bookapp.author.entity.Authors;

import java.util.List;

public interface AuthorsService {
    List<Authors> searchAuthors(String name);
}
