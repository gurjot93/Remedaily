package com.example.devan.remedaily;
/*
* OWNER: Devanshu Srivastava
* PURPOSE: broadcast reciever for showing the notifications on time.
* */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.devan.remedaily.View.DisplayNotification;
import com.example.devan.remedaily.datalayer.AppDatabase;
import com.example.devan.remedaily.datalayer.Med;



public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Med med = AppDatabase.getInMemoryDatabase(context).medModel().loadMedByName(intent.getStringExtra("medName").toString());
        /*
         *Calling the notification method to display the notification details
         */

        DisplayNotification displayNotification= new DisplayNotification(context);
        displayNotification.createNotification(med.medName,"Dosage: "+(med.dosage));

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

    }

}
