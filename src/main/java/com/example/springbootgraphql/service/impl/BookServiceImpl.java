package com.example.springbootgraphql.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootgraphql.model.dto.Book;
import com.example.springbootgraphql.repository.BookRepository;
import com.example.springbootgraphql.service.BookService;

import graphql.schema.DataFetchingFieldSelectionSet;
import graphql.schema.SelectedField;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll(final DataFetchingFieldSelectionSet selectionSet) {
        final Collection<String> fields = selectionSet.getImmediateFields().stream()
                .map(SelectedField::getQualifiedName)
                .collect(Collectors.toList());

        return bookRepository.findAll(fields);
    }

}
