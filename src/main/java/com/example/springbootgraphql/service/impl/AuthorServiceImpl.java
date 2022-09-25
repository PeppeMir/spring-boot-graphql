package com.example.springbootgraphql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootgraphql.model.dto.Author;
import com.example.springbootgraphql.repository.AuthorRepository;
import com.example.springbootgraphql.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        // authorRepository.find();

        return null;
    }
    
}
