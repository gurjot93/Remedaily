package com.example.devan.remedaily;

import java.util.ArrayList;

public class WeekDay {
    private String dayString;
    private ArrayList<TimeEntry> timeEntriesList;

    public WeekDay(String dayString) {
        this.dayString = dayString;
        timeEntriesList = new ArrayList<TimeEntry>();
    }

    public ArrayList<TimeEntry> getTimeEntriesList() {
        return timeEntriesList;
    }

    public String getDayString() {
        return dayString;
    }
}
