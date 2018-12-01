/*
Created by Devanshu Srivastava
Contains the business logic for Database
*/

package com.example.devan.remedaily.datalayer.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.example.devan.remedaily.datalayer.AppDatabase;
import com.example.devan.remedaily.datalayer.Med;
import com.example.devan.remedaily.datalayer.User;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DatabaseInitializer {

    public static void populateAsync(final AppDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    public static List<User>  showUsers(@NonNull final AppDatabase db) {
        return showActiveUser(db);
    }

    private static Med addMed(final AppDatabase db, final String id, final String medname,
                              final String createdate, final String expirydata) {
        Med med = new Med();
//        med.id = id;
        med.medName=medname;
//        med.createDate = createdate;
//        med.expiryDate= expirydata;
        db.medModel().insertMeds(med);
        return med;
    }



    private static void populateWithTestData(AppDatabase db) {
        db.medModel().deleteAll();
        Med med1 = addMed(db, "1", "Paracetamol", "January, 2018", "February, 2019");
        Med med2 = addMed(db, "2", "Sporlac", "December, 2018", "March, 2019");
        Med med3 = addMed(db, "3", "Azithromicin", "March, 2018", "April, 2019");
    }


    private static List<User>  showActiveUser(AppDatabase db) {
        List<User>  med1 = db.userModel().loadAllUsers();
        return med1;
    }

    private static Date getTodayPlusDays(int daysAgo) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysAgo);
        return calendar.getTime();
    }

    // Can be used in future.
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}
