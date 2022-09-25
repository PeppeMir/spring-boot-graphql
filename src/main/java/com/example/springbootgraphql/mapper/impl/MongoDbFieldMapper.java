package com.example.springbootgraphql.mapper.impl;

import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class MongoDbFieldMapper extends AbstractFieldMapper<Document> {

    @Override
    protected Object extractValue(String fieldName, Document sourceEntity) {
        return sourceEntity.get("id".equals(fieldName) ? "_id" : fieldName);
    }
}
