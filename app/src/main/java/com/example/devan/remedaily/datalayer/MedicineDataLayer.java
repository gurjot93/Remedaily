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
