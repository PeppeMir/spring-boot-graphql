package com.example.springbootgraphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springbootgraphql.model.dto.Book;
import com.example.springbootgraphql.service.BookService;

import graphql.schema.DataFetchingEnvironment;

@Controller
@RequestMapping("/api/v1.0/")
public class BookController {

    @Autowired
    private BookService bookService;

    @QueryMapping(name = "allBooks")
    public List<Book> getAll(final DataFetchingEnvironment env) {
        return bookService.getAll(env.getSelectionSet());
    }
}
