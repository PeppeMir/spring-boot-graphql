package com.example.springbootgraphql.repository;

import java.util.Collection;
import java.util.List;

import com.example.springbootgraphql.model.dto.Book;

public interface BookRepository {

    List<Book> findAll(Collection<String> fields);
    
}
