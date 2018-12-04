/*
    Page Name : Home.java
    Author : Deep Singh (B00792279)
    Purpose : Business Layer for the home page. It feeds the medicine information from the
    datalayer/MedicineDataLayer.java file

 */


package com.example.devan.remedaily.businesslayer;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;

import com.example.devan.remedaily.Models.Medicine;
import com.example.devan.remedaily.Models.NonDailyMedicine;
import com.example.devan.remedaily.datalayer.Med;
import com.example.devan.remedaily.datalayer.MedicineDataLayer;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
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

    public ArrayList<Med> getUpcomingMedicineList(Context context) throws ParseException {

        ArrayList<Med> UpcomingMedicineList = new ArrayList<>();
        // this will show the medicines in coming week
        // get the non daily medicine list
        Map<Date,ArrayList<Med>> NonDailyMedicine = getNonDailyMedicineCalendarWise(context);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        ArrayList<String> MedicineName = new ArrayList<>();
        ArrayList<String> MedicineDosage = new ArrayList<>();
        for(Date DateObj : NonDailyMedicine.keySet()){
            Date CurrentDate = new Date();
            long diff = Math.round((DateObj.getTime() - CurrentDate.getTime()) / (double) 86400000);
            if(diff >= 0){
                // check if the medicines are upcoming in the coming week
                if(diff <= 7){
                    for(Med MedObj : NonDailyMedicine.get(DateObj)){
                        if(MedicineName.contains(MedObj.medName) == false){
                            if(MedicineDosage.contains(MedObj.dosage) == false){
                                UpcomingMedicineList.add(MedObj);
                                MedicineName.add(MedObj.medName);
                                MedicineDosage.add(MedObj.dosage);
                            }
                        }
                    }
                }
            }
        }

        // check the daily medicine part too
        List<Med> DailyMedicineList = getDailyMedicineCalendarWise(context);

        for(Med MedObj: DailyMedicineList){
            Date CurrentDate = new Date();
            Date StartDate = simpleDateFormat.parse(MedObj.startDate);
            Date EndDate = simpleDateFormat.parse(MedObj.endDate);
            long diffStart = Math.round((StartDate.getTime() - CurrentDate.getTime()) / (double) 86400000);
            long diffEnd = Math.round((EndDate.getTime() - CurrentDate.getTime()) / (double) 86400000);

            if(diffStart < 7){
                if(diffEnd > 0){
                    UpcomingMedicineList.add(MedObj);
                }
            }
        }
        return UpcomingMedicineList;
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

    public List<Med> getDailyMedicineCalendarWise(Context context) throws ParseException {
        return medicineDataObject.getAllDailyMedicines(context);
    }

    public Map<Date, ArrayList<Med>> getNonDailyMedicineCalendarWise(Context context) throws ParseException {
        ArrayList<NonDailyMedicine> medicineArrayList = new ArrayList<>();

        // store the arraylist of medicine details
        List<Med> getAllMedicines = medicineDataObject.getAllNonDailyMedicines(context);

        // prepare the output
        Map<Date, ArrayList<Med>> OutputMap = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        for (Med medObj : getAllMedicines) {

            // source : https://stackoverflow.com/a/24409106
            Date StartDate = simpleDateFormat.parse(medObj.startDate);
            Date EndDate = simpleDateFormat.parse(medObj.endDate);

            long diff = Math.round((EndDate.getTime() - StartDate.getTime()) / (double) 86400000);

            // iterate through all the days
            for (int days = 0; days <= diff; days++) {

                // add the days
                Calendar c = Calendar.getInstance();
                c.setTime(StartDate);
                c.add(Calendar.DATE, days);

                // get the day of the week, since we are taking Monday = 0, but android takes Sunday = 1
                // so we have to deduct 2
                int DayToSelect = Math.abs(c.get(Calendar.DAY_OF_WEEK)) - 2;

                // if it's < 0; then it means that Sunday was chosen (sunday = 1, Sunday - 2 = -1)
                if (DayToSelect == -1) {
                    DayToSelect = 6; // it's sunday
                }

                if (medObj.timeObject.get(DayToSelect).size() > 0) {
                    if (OutputMap.get(c.getTime()) == null) {
                        ArrayList<Med> MedicinesToInsert = new ArrayList<>();
                        MedicinesToInsert.add(medObj);
                        OutputMap.put(c.getTime(), MedicinesToInsert);
                    } else {
                        ArrayList<Med> MedicinesToInsert = OutputMap.get(c.getTime());
                        MedicinesToInsert.add(medObj);
                        OutputMap.remove(c.getTime());
                        OutputMap.put(c.getTime(), MedicinesToInsert);
                    }
                }
            }
        }

        // sort all the keys
        // Source : https://stackoverflow.com/a/7860836
        return new TreeMap<>(OutputMap);
    }

    public List<Med> getAllMedicine(Context context) {
        List<Med> medicines = medicineDataObject.getAllMedicines(context);

        return medicines;
    }

}
