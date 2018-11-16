package com.example.devan.remedaily.datalayer;

import java.util.ArrayList;

public class MedicineDataLayer {

    public ArrayList<Object[]> getCurrentMedicineList(){

        // get medicine details (currently it's from a data stub)
        MedicineDAO medicineDAOObject = new MedicineDAO();

        // return the medicine details
        return medicineDAOObject.getUpcomingMedicineDetails();

    }
}
