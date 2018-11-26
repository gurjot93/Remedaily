/*
    Page Name : Home.java
    Author : Deep Singh (B00792279)
    Purpose : Data Layer for the home page. It feeds the medicine information from the
    datalayer/Medicine.java file

 */

package com.example.devan.remedaily.datalayer;

import com.example.devan.remedaily.Models.Medicine;

import java.util.ArrayList;
import java.util.Date;

public class MedicineDataLayer {

    private final MedicineDAO medicineDAOObject;

    public MedicineDataLayer() {
        medicineDAOObject = new MedicineDAO();
    }

    public ArrayList<Object[]> getCurrentMedicineList() {

        // return the medicine details
        return medicineDAOObject.getUpcomingMedicineDetails();

    }

    public ArrayList<Object[]> getMissedMedicineList() {

        // return missed medicine details
        return medicineDAOObject.getMissedMedicineDetails();
    }

    public ArrayList<String> getAllMedicineDates(){
        return medicineDAOObject.getMedicineDates();
    }

    public ArrayList<Medicine> getMedicineFromDateTime(String DateObj){
        return medicineDAOObject.getMedicineDataStubByDateTime(DateObj);
    }

    public ArrayList<Medicine> getDailyMedicineList(){
        return medicineDAOObject.getDailyMedicineListDataStub();
    }
}
