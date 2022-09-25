package com.example.springbootgraphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springbootgraphql.model.dto.Author;
import com.example.springbootgraphql.service.AuthorService;

@Controller
@RequestMapping("/api/v1.0/")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;

    @QueryMapping(name = "allAuthors")
    public List<Author> getAll() {
        return authorService.getAll();
    }
}
