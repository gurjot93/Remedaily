package com.example.devan.remedaily.View;
/*
 * Class to create the Notifications
 * Created by: Gurjot Singh (B00811724)
 * Created date: November 26, 2018
 * Version: 1
 * */
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import com.example.devan.remedaily.R;

/*Import for the notification ringtone*/
import static android.provider.Settings.System.DEFAULT_NOTIFICATION_URI;

/*
* The Class DisplayNotification will create the notifications for the Remedaily application. The function createNotification will help display
* the notification to the screen using two inputs- Title and Description
* Referred from: https://stackoverflow.com/questions/7442670/android-how-to-show-notification-on-screen
* */
public class DisplayNotification extends ContextWrapper {

    /*
    * Constructor to get the context
    * */
    public DisplayNotification(Context base) {
        super(base);
    }

    /*
    * Function to create Notifications.
    * Input Required for this function- Message Details and the Description to be displayed on the notification
    * */
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createNotification(String messageDetails, String descriptionDetails) {

        /*
        * Notifications for Android Oreo and above versions (they require channel creations)
        * Channel Creations Referred from: https://stackoverflow.com/questions/45668079/notificationchannel-issue-in-android-o
        * */
        //EnableFlashLight e = new EnableFlashLight(this);
        //e.enableFlash();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // The id of the channel
            String id = "my_channel_01";

            // The user-visible name of the channel.
            CharSequence name = "Remedaily";

            //This will provide the ringtone and vibration
            int importance = NotificationManager.IMPORTANCE_HIGH;

            //Creating the channel
            NotificationChannel mChannel = null;

            mChannel = new NotificationChannel(id, name, importance);

            /* Configuring the notification channel */
            mChannel.setDescription("The Channel Remedaily uses notifications acccess for ring and vibrate.");
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.setSound(mChannel.getSound(), mChannel.getAudioAttributes());
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

            /*Notification manager to fetch the context and the notification service*/
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(mChannel);

            // The id of the Notification channel
            String channelId = "my_channel_01";

            // Creating a notification and setting the notification channel.
            Notification notification = new Notification.Builder(getApplicationContext())
                    .setContentTitle("Medicine: "+messageDetails)
                    .setContentText(descriptionDetails+". Click to view details")
                    .setSmallIcon(R.drawable.hands).setSound(DEFAULT_NOTIFICATION_URI)
                    .setChannelId(channelId)
                    .setAutoCancel(true)
                    .setSound(mChannel.getSound(), mChannel.getAudioAttributes())
                    .build();

            /* Notification manager to fetch the context and the notification service.
             * Referred from: https://stackoverflow.com/questions/13716723/open-application-after-clicking-on-notification
             * */
            notification.contentIntent=  PendingIntent.getActivity(this, 0,
                    new Intent(this, NotificationDisplayMedicine.class).putExtra("name",messageDetails).putExtra("description",descriptionDetails), PendingIntent.FLAG_CANCEL_CURRENT);

            // Issuing the notification.
            mNotificationManager.notify(001, notification);

        }

        /*
         * Notifications for Android Nougat and below versions (they do not require channel creations)
         * */
        else {
            //Creating the notification
            Notification notification =
                    new NotificationCompat.Builder(this.getApplicationContext())
                            .setSmallIcon(R.drawable.hands)
                            .setContentTitle("Medicine: "+messageDetails)
                            .setContentText(descriptionDetails+". Click to view details")
                            .setAutoCancel(true)
                            .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                            .setSound(DEFAULT_NOTIFICATION_URI)
                            .build();

            /*Intent for a new class by clicking the notification */
            //Intent notificationIntent = new Intent(this, NotificationDisplayMedicine.class);
            notification.contentIntent=  PendingIntent.getActivity(this, 0,
                    new Intent(this, NotificationDisplayMedicine.class).putExtra("name",messageDetails).putExtra("description",descriptionDetails), PendingIntent.FLAG_CANCEL_CURRENT);

            /* Notification manager to fetch the context and the notification service.
            *  Referred from: https://stackoverflow.com/questions/13716723/open-application-after-clicking-on-notification
            * */
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            /*Issuing the notification*/
            mNotificationManager.notify(001, notification);
        }
        /*Call to Create a Flashlight Notification along with the normal notification*/
        EnableFlashLight enableFlashLight = new EnableFlashLight(this);
        enableFlashLight.enableFlash();
    }
}
