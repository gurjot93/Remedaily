/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.devan.remedaily.db.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.devan.remedaily.db.AppDatabase;
import com.example.devan.remedaily.db.Med;

import java.util.Calendar;
import java.util.Date;

public class DatabaseInitializer {

    // Simulate a blocking operation delaying each Loan insertion with a delay:
    private static final int DELAY_MILLIS = 500;

    public static void populateAsync(final AppDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static Med addMed(final AppDatabase db, final String id, final String medname,
                              final String createdate, final String expirydata) {
        Med med = new Med();
        med.id = id;
        med.medName=medname;
        med.createDate = createdate;
        med.expiryDate= expirydata;
        db.medModel().insertMeds(med);
        return med;
    }

    private static void populateWithTestData(AppDatabase db) {
        db.medModel().deleteAll();

        Med med1 = addMed(db, "1", "Paracetamol", "January, 2018", "February, 2019");
        Med med2 = addMed(db, "2", "Sporlac", "December, 2018", "March, 2019");
        Med med3 = addMed(db, "3", "Azithromicin", "March, 2018", "April, 2019");

//        try {
//            // Loans are added with a delay, to have time for the UI to react to changes.
//
//            Date today = getTodayPlusDays(0);
//            Date yesterday = getTodayPlusDays(-1);
//            Date twoDaysAgo = getTodayPlusDays(-2);
//            Date lastWeek = getTodayPlusDays(-7);
//            Date twoWeeksAgo = getTodayPlusDays(-14);
//
//            addLoan(db, "1", user1, book1, twoWeeksAgo, lastWeek);
//            Thread.sleep(DELAY_MILLIS);
//            addLoan(db, "2", user2, book1, lastWeek, yesterday);
//            Thread.sleep(DELAY_MILLIS);
//            addLoan(db, "3", user2, book2, lastWeek, today);
//            Thread.sleep(DELAY_MILLIS);
//            addLoan(db, "4", user2, book3, lastWeek, twoDaysAgo);
//            Thread.sleep(DELAY_MILLIS);
//            addLoan(db, "5", user2, book4, lastWeek, today);
//            Log.d("DB", "Added loans");
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
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
