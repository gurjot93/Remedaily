package com.example.devan.remedaily;

public class TimeEntry {
    private int hourOfDay;
    private int minute;

    public TimeEntry(int hourOfDay, int minute) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
    }
    public int getHour() {return hourOfDay;}
    public int getMinute() {return minute;}

}
