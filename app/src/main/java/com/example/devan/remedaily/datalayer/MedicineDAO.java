/*
    Page Name : Home.java
    Author : Deep Singh (B00792279)
    Purpose : DataStub layer
 */

package com.example.devan.remedaily.datalayer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MedicineDAO {

    // data stub for medicine DAO
    public ArrayList<Object[]> getUpcomingMedicineDetails(){

        // create dummy dao
        ArrayList<Object[]> arrayDAO = new ArrayList<>();

        // add dummy values
        Object[] dummyStringValue = new Object[5];
        dummyStringValue[0] = 0;
        dummyStringValue[1] = "Medicine001";
        dummyStringValue[2] = "D1";
        dummyStringValue[3] = "U1";
        dummyStringValue[4] =  Calendar.getInstance().getTime().toString();
        arrayDAO.add(dummyStringValue);

        dummyStringValue = new Object[5];
        dummyStringValue[0] = 0;
        dummyStringValue[1] = "Medicine002";
        dummyStringValue[2] = "D2";
        dummyStringValue[3] = "U2";
        dummyStringValue[4] =  Calendar.getInstance().getTime().toString();
        arrayDAO.add(dummyStringValue);

        dummyStringValue = new Object[5];
        dummyStringValue[0] = 0;
        dummyStringValue[1] = "Medicine002";
        dummyStringValue[2] = "D2";
        dummyStringValue[3] = "U2";
        dummyStringValue[4] =  Calendar.getInstance().getTime().toString();
        arrayDAO.add(dummyStringValue);

        dummyStringValue = new Object[5];
        dummyStringValue[0] = 0;
        dummyStringValue[1] = "Medicine002";
        dummyStringValue[2] = "D2";
        dummyStringValue[3] = "U2";
        dummyStringValue[4] =  Calendar.getInstance().getTime().toString();
        arrayDAO.add(dummyStringValue);

        dummyStringValue = new Object[5];
        dummyStringValue[0] = 0;
        dummyStringValue[1] = "Medicine002";
        dummyStringValue[2] = "D2";
        dummyStringValue[3] = "U2";
        dummyStringValue[4] =  Calendar.getInstance().getTime().toString();
        arrayDAO.add(dummyStringValue);

        return arrayDAO;
    }

    // data stub for missed medicine DAO
    public ArrayList<Object[]> getMissedMedicineDetails(){

        // create dummy dao
        ArrayList<Object[]> arrayDAO = new ArrayList<>();

        // add dummy values
        Object[] dummyStringValue = new Object[5];
        dummyStringValue[0] = 0;
        dummyStringValue[1] = "Medicine003";
        dummyStringValue[2] = "D1";
        dummyStringValue[3] = "U1";
        dummyStringValue[4] =  Calendar.getInstance().getTime().toString();
        arrayDAO.add(dummyStringValue);

        return arrayDAO;
    }

}
