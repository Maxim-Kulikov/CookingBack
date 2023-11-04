package com.example.cooking.util;

import com.example.cooking.presentation.dto.Query;

public class DataValidator {

    public static boolean isObjectOrFieldNull(Query query) {
        return query == null || query.getQuery() == null || query.getQuery().isBlank();
    }

    public static boolean isNullOrEmpty(String field) {
        return field == null || field.isBlank();
    }
}
