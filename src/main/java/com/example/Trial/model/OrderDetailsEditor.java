package com.example.Trial.model;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderDetailsEditor extends PropertyEditorSupport {
	private ObjectMapper objectMapper;

    public OrderDetailsEditor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) {
            setValue(null);
        } else {
            Product prod = new Product();
            try {
                prod = objectMapper.readValue(text, Product.class);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            setValue(prod);
        }
    }
} 