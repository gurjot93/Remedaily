package com.example.devan.remedaily.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.devan.remedaily.R;
import com.example.devan.remedaily.View.UserDetails;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView txtView;
    public Button userDetailsBtn,calenderBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView= findViewById(R.id.txtView);

        userDetailsBtn=findViewById(R.id.userDetailsBtn);
        calenderBtn=findViewById(R.id.calenderBtn);

        userDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UserDetails.class);
                startActivity(intent);
            }
        });

        calenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,calender.class);
                startActivity(intent);
            }
        });
    }

}

