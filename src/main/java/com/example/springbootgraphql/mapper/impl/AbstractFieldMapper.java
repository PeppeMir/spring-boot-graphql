package com.example.springbootgraphql.mapper.impl;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.example.springbootgraphql.mapper.FieldMapper;

public abstract class AbstractFieldMapper<S> implements FieldMapper<S> {

    private final Logger logger = LoggerFactory.getLogger(AbstractFieldMapper.class);

    @Override
    public <T> T map(final Collection<String> fieldNames, final S sourceEntity, final Class<T> targetClass) {
        final T targetEntity;
        try {
            targetEntity = targetClass.getDeclaredConstructor().newInstance();
        } catch (final Exception e) {
            // FIXME: add error management
            return null;
        }

        fieldNames.stream()
                .filter(Objects::nonNull)
                .forEach(fieldName -> {
                    try {
                        final Field field = ReflectionUtils.findField(targetClass, fieldName);
                        final Object value = extractValue(fieldName, sourceEntity);

                        if (field != null && value != null) {
                            if (!field.getType().isAssignableFrom(value.getClass())) {
                                logger.error(
                                        "Ignoring setting value for field '{}': types '{}' and '{}' are incopatible.",
                                        fieldName, field.getType(), value.getClass());
                            } else {
                                ReflectionUtils.makeAccessible(field);
                                ReflectionUtils.setField(field, targetEntity, value);
                            }
                        } else {
                            logger.error(
                                    "Ignoring setting value for field '{}': field not found on either target class '{}' or source entity '{}'.",
                                    fieldName, targetClass, sourceEntity.getClass());
                        }
                    } catch (final Exception e) {
                        logger.error(
                                "Error while setting value for field '{}'.", fieldName, e);
                    }
                });

        return targetEntity;
    }

    protected abstract Object extractValue(final String fieldName, final S sourceEntity);
}
