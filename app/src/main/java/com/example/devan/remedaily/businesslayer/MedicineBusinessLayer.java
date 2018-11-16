package com.example.devan.remedaily.businesslayer;

import com.example.devan.remedaily.Models.Medicine;
import com.example.devan.remedaily.datalayer.MedicineDataLayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MedicineBusinessLayer {

    public ArrayList<Medicine> getUpcomingMedicineList() throws ParseException {

        // set current arraylist of medicines to null
        ArrayList<Medicine> medicineArrayList = null;

        // now we need to get the upcoming medicine list from the database
        MedicineDataLayer medicineDataObject = new MedicineDataLayer();
        ArrayList<Object[]> medicineDAO = medicineDataObject.getCurrentMedicineList();

        // iterate it
        for(int i=0; i<medicineDAO.size(); i++){
            medicineArrayList.add(new Medicine(Integer.parseInt(medicineDAO.get(i)[0].toString()),medicineDAO.get(i)[1].toString(),medicineDAO.get(i)[2].toString(), new SimpleDateFormat("dd/MM/yyyy").parse(medicineDAO.get(i)[4].toString())));
        }

        return medicineArrayList;
    }

}
