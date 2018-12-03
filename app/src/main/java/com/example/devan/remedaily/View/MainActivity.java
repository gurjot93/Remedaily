package com.example.devan.remedaily.View;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.devan.remedaily.R;

public class MainActivity extends AppCompatActivity {
    private TextView txtView;
    public Button userDetailsBtn,editUserDetailsBtn,calenderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView= findViewById(R.id.txtView);

        /*running the notifications code for reference
        DisplayNotification displayNotification = new DisplayNotification(this);
        displayNotification.createNotification("Hello","Welcome to Remedaily!"); */
        userDetailsBtn=findViewById(R.id.userDetailsBtn);

        //calenderBtn=findViewById(R.id.calenderBtn);


        editUserDetailsBtn=findViewById(R.id.editUserDetailsBtn);

        userDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UserDetails.class);
                startActivity(intent);
            }
        });


       /* calenderBtn.setOnClickListener(new View.OnClickListener() {

        editUserDetailsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,EditUserDetails.class);
                startActivity(intent);
            }
        });*/
    }

}

