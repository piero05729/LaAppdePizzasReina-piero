package com.example.back_pizza.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE;

    public static LocalDate parseIso(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return LocalDate.parse(value, ISO);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha invalida: " + value, e);
        }
    }

    public static String formatIso(LocalDate date) {
        return date != null ? date.format(ISO) : null;
    }
}
