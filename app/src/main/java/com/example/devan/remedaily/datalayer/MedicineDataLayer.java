/*
    Page Name : Home.java
    Author : Deep Singh (B00792279)
    Purpose : Data Layer for the home page. It feeds the medicine information from the
    datalayer/Medicine.java file

 */

package com.example.devan.remedaily.datalayer;

import java.util.ArrayList;

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

        // return msised medicine details
        return medicineDAOObject.getMissedMedicineDetails();
    }
}
