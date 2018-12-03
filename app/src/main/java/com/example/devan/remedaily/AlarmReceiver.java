package com.example.devan.remedaily;
/*
* OWNER: Devanshu Srivastava
* PURPOSE: broadcast reciever for showing the notifications on time.
* */
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.devan.remedaily.View.EnableFlashLight;
import com.example.devan.remedaily.datalayer.AppDatabase;
import com.example.devan.remedaily.datalayer.Med;



public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Med med = AppDatabase.getInMemoryDatabase(context).medModel().loadMedByName(intent.getStringExtra("medName").toString());
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "Devanshu")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(med.medName)
                .setContentText("Advised to take the medication, click for more information.")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Advised to take the medication, click for more information."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Devanshu";//getString(R.string.channel_name);
            String description = "Seri";//getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Devanshu", name, importance);
            channel.setDescription(description);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setSound(channel.getSound(), channel.getAudioAttributes());
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(0, mBuilder.build());
        /*Call to Create a Flashlight Notification along with the normal notification*/
        EnableFlashLight enableFlashLight = new EnableFlashLight(context);
        enableFlashLight.enableFlash();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

    }

}
