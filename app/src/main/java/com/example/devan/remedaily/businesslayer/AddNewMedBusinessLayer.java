/*Creator : Devanshu Srivastava*/

package com.example.devan.remedaily.businesslayer;

import android.support.annotation.NonNull;

import com.example.devan.remedaily.datalayer.AppDatabase;
import com.example.devan.remedaily.datalayer.Med;

import java.util.ArrayList;
import java.util.List;

public class AddNewMedBusinessLayer {

    //region Insert Medicine
    public static void AddMeds(@NonNull final AppDatabase db, int tagDaily ,String medName,
                               String dosage,String imagePath,String startDate,String endDate,ArrayList<ArrayList<String>> arrTimeItem) throws Exception {
        InsertMeds(db,tagDaily,medName,dosage,imagePath,startDate,endDate,arrTimeItem);
    }


    private static boolean InsertMeds(@NonNull final AppDatabase db,int tagDaily ,String medName,
                                      String dosage,String imagePath,String startDate,
                                      String endDate,ArrayList<ArrayList<String>> arrTimeItem) throws Exception {
        Med med = new Med();
        med.tagDaily =tagDaily;
        med.medName=medName;
        med.dosage=dosage;
        med.imagePath=imagePath;
        med.startDate=startDate;
        med.endDate=endDate;
        med.timeObject = arrTimeItem; //arraylist of arraylist<strings>
        db.medModel().insertMeds(med);
        return true;

    }
    //endregion


}
