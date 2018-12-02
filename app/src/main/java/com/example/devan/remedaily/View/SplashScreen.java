package com.example.devan.remedaily.View;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.devan.remedaily.R;

public class SplashScreen extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1500);
                    Intent intent = new  Intent(getApplicationContext(),Home.class);
                    startActivity(intent);
                    SplashScreen.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        /*running the notification */
//        DisplayNotification displaynotification = new DisplayNotification(this);
//        displaynotification.createNotification("Hello There!","Welcome to Remedaily!, Lets Get Started");

    }
}
