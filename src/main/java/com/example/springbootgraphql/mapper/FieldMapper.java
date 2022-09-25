package com.example.springbootgraphql.mapper;

import java.util.Collection;

public interface FieldMapper<S> {

    <T> T map(Collection<String> fieldNames, S sourceEntity, Class<T> targetClass);
}
