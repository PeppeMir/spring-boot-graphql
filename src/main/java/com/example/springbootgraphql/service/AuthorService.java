package com.example.springbootgraphql.service;

import java.util.List;

import com.example.springbootgraphql.model.dto.Author;

public interface AuthorService {

    List<Author> getAll();
    
}
