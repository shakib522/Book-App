package com.example.bookapp.author.controller;

import com.example.bookapp.author.entity.Authors;
import com.example.bookapp.author.service.AuthorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthorsController {

    private final AuthorsService authorsService;

    @GetMapping("/authors/{name}")
    public ResponseEntity<List<Authors>> searchAuthors(@PathVariable("name") String name){
        return ResponseEntity.ok().body(authorsService.searchAuthors(name));
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Authors>> getAllAuthors(){
        return ResponseEntity.ok().body(authorsService.getAllAuthors());
    }

}
