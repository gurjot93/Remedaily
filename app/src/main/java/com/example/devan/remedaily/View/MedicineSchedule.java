package com.example.devan.remedaily.View;

import java.sql.Ref;
import java.util.ArrayList;

public class MedicineSchedule {
    static private ArrayList<ArrayList<String>> weekSchedule;

    static private String name;
    static private String dosage;
    static private String startDate;
    static private String endDate;
    static private String path;
    static private boolean isDaily;

    public MedicineSchedule() {
        weekSchedule = new ArrayList<ArrayList<String>>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setIsDaily(boolean isDaily) {
        this.isDaily = isDaily;
    }

    public void setDrugBoxImagePath(String path) {
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public String getDosage() {
        return this.dosage;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public boolean getIsDaily() {
        return this.isDaily;
    }

    public String getDrugBoxImagePath() {
        return this.path;
    }

    public ArrayList<ArrayList<String>> getWeekSchedule() {
        return this.weekSchedule;
    }
}
