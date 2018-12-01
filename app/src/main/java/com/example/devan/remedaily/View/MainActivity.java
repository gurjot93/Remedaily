package com.example.devan.remedaily.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.devan.remedaily.R;

public class MainActivity extends AppCompatActivity {
    private TextView txtView;
    public Button userDetailsBtn,editUserDetailsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView= findViewById(R.id.txtView);

        userDetailsBtn=findViewById(R.id.userDetailsBtn);
<<<<<<< HEAD
        //calenderBtn=findViewById(R.id.calenderBtn);

=======
        editUserDetailsBtn=findViewById(R.id.editUserDetailsBtn);
>>>>>>> a858b35c0291c9fa9d2edd8bf1e9881920db56fc
        userDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UserDetails.class);
                startActivity(intent);
            }
        });

<<<<<<< HEAD
       /* calenderBtn.setOnClickListener(new View.OnClickListener() {
=======
        editUserDetailsBtn.setOnClickListener(new View.OnClickListener() {
>>>>>>> a858b35c0291c9fa9d2edd8bf1e9881920db56fc
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,EditUserDetails.class);
                startActivity(intent);
            }
        });*/
    }

}

