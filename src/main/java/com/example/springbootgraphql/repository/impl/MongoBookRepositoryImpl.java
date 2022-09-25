package com.example.springbootgraphql.repository.impl;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.springbootgraphql.mapper.impl.MongoDbFieldMapper;
import com.example.springbootgraphql.model.dto.Book;
import com.example.springbootgraphql.repository.BookRepository;

@Repository
public class MongoBookRepositoryImpl implements BookRepository {

    final Logger logger = LoggerFactory.getLogger(MongoBookRepositoryImpl.class);

    private static final String COLLECTION_NAME = "books";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoDbFieldMapper fieldMapper;

    @Override
    public List<Book> findAll(final Collection<String> fields) {
        final Query query = new Query();

        if (!isEmpty(fields)) {
            query.fields().include(fields.toArray(String[]::new));
        }

        final List<Book> books = new ArrayList<>();

        mongoTemplate.executeQuery(query, COLLECTION_NAME,
                document -> books.add(fieldMapper.map(fields, document, Book.class)));

        return books;
    }
}
