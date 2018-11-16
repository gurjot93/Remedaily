package com.example.devan.remedaily.Models;

import java.util.Date;

public class Medicine {
    private final int medicineImage;
    private final String medicineName;
    private final String medicineDosage;
    private final Date dateTimeRegistered;

    public Medicine(int medicineImage, String medicineName, String medicineDosage, Date dateTimeRegistered){
        this.medicineImage = medicineImage;
        this.medicineName = medicineName;
        this.medicineDosage = medicineDosage;
        this.dateTimeRegistered = dateTimeRegistered;
    }

    public int getMedicineImage(){
        return this.medicineImage;
    }

    public String getMedicineName(){
        return this.medicineName;
    }

    public String getMedicineDosage(){
        return this.medicineDosage;
    }

    public Date getDateTimeRegistered(){
        return this.dateTimeRegistered;
    }
}
