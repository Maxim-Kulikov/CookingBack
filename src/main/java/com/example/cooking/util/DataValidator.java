package com.example.cooking.util;

import com.example.cooking.presentation.dto.Query;

import java.util.List;

public class DataValidator {
    public static List<String> split(String query) {
        return List.of(query.trim().split("[\\s]+"));
    }

    public static boolean isObjectOrFieldNull(Query query) {
        return query == null || query.getQuery() == null || query.getQuery().isBlank();
    }

    public static boolean isNullOrEmpty(String field) {
        return field == null || field.isBlank();
    }
}
