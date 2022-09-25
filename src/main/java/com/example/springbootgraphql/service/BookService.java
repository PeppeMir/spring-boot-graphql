package com.example.springbootgraphql.service;

import java.util.List;

import com.example.springbootgraphql.model.dto.Book;

import graphql.schema.DataFetchingFieldSelectionSet;

public interface BookService {

    List<Book> getAll(DataFetchingFieldSelectionSet dataFetchingFieldSelectionSet);
    
}
