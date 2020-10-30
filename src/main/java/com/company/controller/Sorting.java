package com.company.controller;

import java.util.Arrays;

public enum Sorting {

    BY_START_TIME("session.start_time"),
    BY_TITLE("film.title"),
    BY_SEATS_COUNT("session.seats_counter");

    public static final Sorting DEFAULT_SORTING = BY_START_TIME;

    private final String value;

    Sorting(String value) {
        this.value = value;
    }

    public static String getSorting(String sorting_value){
        if (sorting_value == null || "".equals(sorting_value)){
            return Sorting.DEFAULT_SORTING.getValue();
        }
        return Arrays.stream(Sorting.values())
                .filter((s) -> s.getValue().equals(sorting_value))
                .findFirst()
                .orElse(Sorting.DEFAULT_SORTING)
                .getValue();
    }

    public String getValue() {
        return value;
    }
}
