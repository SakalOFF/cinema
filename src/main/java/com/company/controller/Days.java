package com.company.controller;

import java.util.Calendar;

public enum  Days {

    MONDAY(1, "monday"), TUESDAY(2, "tuesday"), WEDNESDAY(3, "wednesday"),
    THURSDAY(4, "thursday"), FRIDAY(5, "friday"), SATURDAY(6, "saturday"),
    SUNDAY(7, "sunday");

    private final int id;
    private final String name;

    Days(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static int getTodayWeekDayId(){
        Calendar calendar = Calendar.getInstance();
        switch (calendar.get(Calendar.DAY_OF_WEEK)){
            case Calendar.MONDAY:
                return 1;
            case Calendar.TUESDAY:
                return 2;
            case Calendar.WEDNESDAY:
                return 3;
            case Calendar.THURSDAY:
                return 4;
            case Calendar.FRIDAY:
                return 5;
            case Calendar.SATURDAY:
                return 6;
            default:
                return 7;
        }
    }
}
