/*
    Page Name : Home.java
    Author : Deep Singh (B00792279)
    Purpose : Business Layer for the home page. It feeds the medicine information from the
    datalayer/MedicineDataLayer.java file

 */


package com.example.devan.remedaily.businesslayer;

import android.content.Context;
import android.icu.util.Output;

import com.example.devan.remedaily.Models.Medicine;
import com.example.devan.remedaily.Models.NonDailyMedicine;
import com.example.devan.remedaily.datalayer.Med;
import com.example.devan.remedaily.datalayer.MedicineDataLayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MedicineBusinessLayer {
    private MedicineDataLayer medicineDataObject = null;


    public MedicineBusinessLayer() {
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

    public ArrayList<Medicine> getDailyMedicineCalendarWise() {
        return new MedicineDataLayer().getDailyMedicineList();
    }

    public Map<Date, ArrayList<Med>> getDailyMedicineCalendarWise(Context context) throws ParseException {
        ArrayList<NonDailyMedicine> medicineArrayList = new ArrayList<>();

        // store the arraylist of medicine details
        List<Med> getAllMedicines = medicineDataObject.getAllMedicines(context);

        // prepare the output
        Map<Date, ArrayList<Med>> OutputMap = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        for (Med medObj : getAllMedicines) {

            // source : https://stackoverflow.com/a/24409106
            Date StartDate = simpleDateFormat.parse(medObj.startDate);
            Date EndDate = simpleDateFormat.parse(medObj.endDate);

            //time is always 00:00:00 so rounding should help to ignore the missing hour when going from winter to summer time as well as the extra hour in the other direction
            long diff = Math.round((EndDate.getTime() - StartDate.getTime()) / (double) 86400000);


            // since there are 7 days in a week, so we will iterate it through 7 days
            for (int days = 0; days < 7; days++) {
                // get the arraylist of dates

                if (medObj.timeObject.get(days).size() > 0) {
                    // source https://stackoverflow.com/a/12087555
                    for (int dateIteration = days; dateIteration <= diff; dateIteration += 7) {
                        Calendar c = Calendar.getInstance();
                        c.setTime(StartDate);
                        c.add(Calendar.DATE, dateIteration);
                        if (OutputMap.get(c.getTime()) == null) {
                            ArrayList<Med> MedicinesToInsert = new ArrayList<>();
                            MedicinesToInsert.add(medObj);
                            OutputMap.put(c.getTime(),MedicinesToInsert);
                        } else {
                            ArrayList<Med> MedicinesToInsert = OutputMap.get(c.getTime());
                            MedicinesToInsert.add(medObj);
                            OutputMap.remove(c.getTime());
                            OutputMap.put(c.getTime(),MedicinesToInsert);
                        }
                    }
                }
            }
        }

        // sort all the keys
        // Source : https://stackoverflow.com/a/7860836
        return new TreeMap<>(OutputMap);
    }

    public Map<Date, ArrayList<Med>> getNonDailyMedicineCalendarWise(Context context) throws ParseException {
        ArrayList<NonDailyMedicine> medicineArrayList = new ArrayList<>();

        // store the arraylist of medicine details
        List<Med> getAllMedicines = medicineDataObject.getAllMedicines(context);

        // prepare the output
        Map<Date, ArrayList<Med>> OutputMap = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        for (Med medObj : getAllMedicines) {

            // source : https://stackoverflow.com/a/24409106
            Date StartDate = simpleDateFormat.parse(medObj.startDate);
            Date EndDate = simpleDateFormat.parse(medObj.endDate);

            //time is always 00:00:00 so rounding should help to ignore the missing hour when going from winter to summer time as well as the extra hour in the other direction
            long diff = Math.round((EndDate.getTime() - StartDate.getTime()) / (double) 86400000);


            // since there are 7 days in a week, so we will iterate it through 7 days
            for (int days = 0; days < 7; days++) {
                // get the arraylist of dates

                if (medObj.timeObject.get(days).size() > 0) {
                    Calendar StartTimeCalendar = Calendar.getInstance();
                    StartTimeCalendar.setTime(StartDate);
                    int DayToSelect = Math.abs(StartTimeCalendar.get(Calendar.DAY_OF_WEEK)) - 2;

                    if(DayToSelect == -1){
                        DayToSelect = 6; // it's sunday
                    }

                    // source https://stackoverflow.com/a/12087555
                    int StartDateIteration = days;


                    if(days - DayToSelect < 0){
                        StartDateIteration = days - DayToSelect + 7;
                    }

                    if(days > diff){
                        // only 1 medicine per week only
                        Calendar c = Calendar.getInstance();
                        c.setTime(StartDate);
                        c.add(Calendar.DATE, Math.abs(days - DayToSelect));
                        if (OutputMap.get(c.getTime()) == null) {
                            ArrayList<Med> MedicinesToInsert = new ArrayList<>();
                            MedicinesToInsert.add(medObj);
                            OutputMap.put(c.getTime(),MedicinesToInsert);
                        } else {
                            ArrayList<Med> MedicinesToInsert = OutputMap.get(c.getTime());
                            MedicinesToInsert.add(medObj);
                            OutputMap.remove(c.getTime());
                            OutputMap.put(c.getTime(),MedicinesToInsert);
                        }
                    }
                    for (int dateIteration = StartDateIteration; dateIteration <= diff; dateIteration += 7) {
                        Calendar c = Calendar.getInstance();
                        c.setTime(StartDate);
                        c.add(Calendar.DATE, dateIteration);
                        if (OutputMap.get(c.getTime()) == null) {
                            ArrayList<Med> MedicinesToInsert = new ArrayList<>();
                            MedicinesToInsert.add(medObj);
                            OutputMap.put(c.getTime(),MedicinesToInsert);
                        } else {
                            ArrayList<Med> MedicinesToInsert = OutputMap.get(c.getTime());
                            MedicinesToInsert.add(medObj);
                            OutputMap.remove(c.getTime());
                            OutputMap.put(c.getTime(),MedicinesToInsert);
                        }
                    }
                }
            }
        }

        // sort all the keys
        // Source : https://stackoverflow.com/a/7860836
        return new TreeMap<>(OutputMap);
    }

}
