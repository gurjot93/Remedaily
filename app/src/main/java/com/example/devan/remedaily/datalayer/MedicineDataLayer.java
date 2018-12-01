/*
    Page Name : Home.java
    Author : Deep Singh (B00792279)
    Purpose : Data Layer for the home page. It feeds the medicine information from the
    datalayer/Medicine.java file

 */

package com.example.devan.remedaily.datalayer;

import android.content.Context;

import com.example.devan.remedaily.Models.Medicine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public ArrayList<String> getAllMedicineDates(Context context){
        return medicineDAOObject.getMedicineDates(context);
    }

    public ArrayList<Med> getMedicineFromDateTime(String DateObj){
        return medicineDAOObject.getMedicineDataStubByDateTime(DateObj);
    }

    public ArrayList<Medicine> getDailyMedicineList(){
        return medicineDAOObject.getDailyMedicineListDataStub();
    }

    public List<Med> getAllMedicines(Context context){
        return medicineDAOObject.getAllMedicines(context);
    }

    public List<Med> getAllNonDailyMedicines(Context context){
        return AppDatabase.getInMemoryDatabase(context).medModel().loadMedByTag(0);
    }

    public List<Med> getAllDailyMedicines(Context context){
        return AppDatabase.getInMemoryDatabase(context).medModel().loadMedByTag(1);
    }
}
