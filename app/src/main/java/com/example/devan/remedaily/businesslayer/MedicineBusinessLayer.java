/*
    Page Name : Home.java
    Author : Deep Singh (B00792279)
    Purpose : Business Layer for the home page. It feeds the medicine information from the
    datalayer/MedicineDataLayer.java file

 */


package com.example.devan.remedaily.businesslayer;

import com.example.devan.remedaily.Models.Medicine;
import com.example.devan.remedaily.datalayer.MedicineDataLayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MedicineBusinessLayer {

    public ArrayList<Medicine> getUpcomingMedicineList() throws ParseException {

        // set current arraylist of medicines to null
        ArrayList<Medicine> medicineArrayList = new ArrayList<>();

        // now we need to get the upcoming medicine list from the database
        MedicineDataLayer medicineDataObject = new MedicineDataLayer();
        ArrayList<Object[]> medicineDAO = medicineDataObject.getCurrentMedicineList();

        // iterate it
        for(int i=0; i<medicineDAO.size(); i++){
            medicineArrayList.add(new Medicine(Integer.parseInt(medicineDAO.get(i)[0].toString()),medicineDAO.get(i)[1].toString(),medicineDAO.get(i)[2].toString(), medicineDAO.get(i)[4].toString()));
        }

        return medicineArrayList;
    }

    public ArrayList<Medicine> getMissedMedicineList()throws ParseException {

        // set current arraylist of medicines to null
        ArrayList<Medicine> medicineArrayList = new ArrayList<>();

        // now we need to get the upcoming medicine list from the database
        MedicineDataLayer medicineDataObject = new MedicineDataLayer();
        ArrayList<Object[]> medicineDAO = medicineDataObject.getMissedMedicineList();

        // iterate it
        for(int i=0; i<medicineDAO.size(); i++){
            medicineArrayList.add(new Medicine(Integer.parseInt(medicineDAO.get(i)[0].toString()),medicineDAO.get(i)[1].toString(),medicineDAO.get(i)[2].toString(), medicineDAO.get(i)[4].toString()));
        }

        return medicineArrayList;
    }

}
