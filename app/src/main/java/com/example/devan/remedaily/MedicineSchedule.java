package com.example.devan.remedaily;

import java.sql.Ref;
import java.util.ArrayList;

public class MedicineSchedule {
    static private int NUMBER_OF_DAYS_IN_ONE_WEEK = 7;

    private String name;
    private String dosage;
    private String drugBoxImagePath;
    private String startDate;
    private String endDate;
    private boolean isDaily;
    private String[] weekSchedule;

    public MedicineSchedule() {
        name = null;
        dosage = null;
        startDate = null;
        endDate = null;
        isDaily = false;
        weekSchedule = new String[NUMBER_OF_DAYS_IN_ONE_WEEK];
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setDrugBoxImagePath(String drugBoxImagePath) { this.drugBoxImagePath = drugBoxImagePath; }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setIsDaily(boolean isDaily) {
        this.isDaily = isDaily;
    }

    public String getName() {
        return this.name;
    }

    public String getDosage() {
        return this.dosage;
    }

    public String getDrugBoxImagePath() { return this.drugBoxImagePath; }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public boolean getIsDaily() {
        return  this.isDaily;
    }

    public String[] getWeekSchedule() {
        return this.weekSchedule;
    }
}
