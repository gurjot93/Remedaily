package com.example.devan.remedaily.View;

/*
* Fetching listners for settings page
* Created by: Gurjot Singh (B00811724)
* Created date: November 18, 2018
* Version: 1
* */
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/*
* Referred from http://hacksmile.com/how-to-create-android-settings-screen-using-preferencefragment/
* */
public class SettingsDetailsFetch {
    private static SharedPreferences sharedPreferences;

    // method that will instantiate sharedPreferecdes to fetch details from the settings page
    private static void getSharedPreferencesInstance(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /*
    * This will return boolean value for checkbox - SpashScreen turn on/off using the key: spashScreen stored in settings xml
    * */
    public static boolean splashScreenOnOff(Context context) {
        getSharedPreferencesInstance(context);
        return sharedPreferences.getBoolean("splashScreen", true);

    }

    /*
     * This will return boolean value for switch - Notifications turn on/off using the key: notificationsChange stored in settings xml
     * */
    public static boolean notificationsOnOff(Context context) {
        getSharedPreferencesInstance(context);
        return sharedPreferences.getBoolean("notificationsChange", true);
    }

}
