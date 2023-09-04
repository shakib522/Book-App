package com.example.bookapp.author.service;


import com.example.bookapp.author.entity.Authors;
import com.example.bookapp.author.repository.AuthorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorsServiceImpl implements AuthorsService{

    private final AuthorsRepository authorsRepository;

    @Override
    public List<Authors> searchAuthors(String name) {
        return authorsRepository.searchAuthors("%"+name+"%");
    }
}
