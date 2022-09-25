package com.example.springbootgraphql.service.impl;

import java.util.Random;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.springbootgraphql.service.DbInitializationService;

@Order(Ordered.LOWEST_PRECEDENCE)
@Service
@ConditionalOnProperty(name = "db.init", havingValue = "true", matchIfMissing = false)
public class MongoDbInitializationService implements DbInitializationService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private final Random random = new Random();

    @PostConstruct
    public void initialize() {
        Stream.iterate(0L, i -> i < 10L, i -> i + 1L)
                .forEach(i -> {
                    final Document doc = new Document();

                    doc.put("_id", i);
                    doc.put("title", "Book " + i);
                    doc.put("pages", random.nextInt(500));
                    // doc.put("author", null);

                    mongoTemplate.save(doc, "books");
                });
    }
}
