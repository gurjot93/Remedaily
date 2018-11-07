package com.example.devan.remedaily;

public class SelectedTime {
    private int hourOfDay;
    private int minute;
    String weekDay;

    public SelectedTime(int hourOfDay, int minute) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
    }
    public int getHour() {return hourOfDay;}
    public int getMinute() {return minute;}

}
