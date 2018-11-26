/*
    Page Name : Home.java
    Author : Deep Singh (B00792279)
    Purpose : Business Layer for the home page. It feeds the medicine information from the
    datalayer/MedicineDataLayer.java file

 */


package com.example.devan.remedaily.businesslayer;

import com.example.devan.remedaily.Models.Medicine;
import com.example.devan.remedaily.Models.NonDailyMedicine;
import com.example.devan.remedaily.datalayer.MedicineDataLayer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class MedicineBusinessLayer {
    private MedicineDataLayer medicineDataObject = null;


    public MedicineBusinessLayer(){
        medicineDataObject = new MedicineDataLayer();
    }

    public ArrayList<Medicine> getUpcomingMedicineList() throws ParseException {

        // set current arraylist of medicines to null
        ArrayList<Medicine> medicineArrayList = new ArrayList<>();

        // now we need to get the upcoming medicine list from the database
        ArrayList<Object[]> medicineDAO = medicineDataObject.getCurrentMedicineList();

        // iterate it
        for (int i = 0; i < medicineDAO.size(); i++) {
            medicineArrayList.add(new Medicine(Integer.parseInt(medicineDAO.get(i)[0].toString()), medicineDAO.get(i)[1].toString(), medicineDAO.get(i)[2].toString(), medicineDAO.get(i)[4].toString()));
        }

        return medicineArrayList;
    }

    public ArrayList<Medicine> getMissedMedicineList() throws ParseException {

        // set current arraylist of medicines to null
        ArrayList<Medicine> medicineArrayList = new ArrayList<>();

        // now we need to get the upcoming medicine list from the database
        ArrayList<Object[]> medicineDAO = medicineDataObject.getMissedMedicineList();

        // iterate it
        for (int i = 0; i < medicineDAO.size(); i++) {
            medicineArrayList.add(new Medicine(Integer.parseInt(medicineDAO.get(i)[0].toString()), medicineDAO.get(i)[1].toString(), medicineDAO.get(i)[2].toString(), medicineDAO.get(i)[4].toString()));
        }

        return medicineArrayList;
    }

    public ArrayList<Medicine> getDailyMedicineCalendarWise(){
        return new MedicineDataLayer().getDailyMedicineList();
    }

    public ArrayList<NonDailyMedicine> getNonDailyMedicineCalendarWise() {
        ArrayList<NonDailyMedicine> medicineArrayList = new ArrayList<>();

        // store the arraylist of medicine details
        ArrayList<String> getAllDates = medicineDataObject.getAllMedicineDates();

        // now we have the dates
        // get all medicine list
        for (String DateObj : getAllDates
                ) {
            NonDailyMedicine NonDailyMedicineObj = new NonDailyMedicine();
            NonDailyMedicineObj.DateTime = DateObj.toString();
            NonDailyMedicineObj.MedicineArrayList = medicineDataObject.getMedicineFromDateTime(DateObj);
            medicineArrayList.add(NonDailyMedicineObj);
        }

        return medicineArrayList;
    }

}
