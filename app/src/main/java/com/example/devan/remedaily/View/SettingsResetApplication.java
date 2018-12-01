package com.example.devan.remedaily.View;

/*
 * Activity to ask User once again to reset the data(Applying Neilson's Heuristics)
 * Created by: Gurjot Singh (B00811724)
 * Created date: November 18, 2018
 * Version: 1
 * */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.devan.remedaily.R;

public class SettingsResetApplication extends AppCompatActivity {
    Button resetButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_application);
        resetButton=(Button) findViewById(R.id.reset_button);
        //listner for the button resetButton
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(
                    View v) {
                //method to be called to reset the application
                System.out.print("Reset Button");
            }
        });
    }
}
