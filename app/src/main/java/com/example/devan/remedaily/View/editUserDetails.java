/*Creator Aditya Gadhvi*/
package com.example.devan.remedaily.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.devan.remedaily.R;
import com.example.devan.remedaily.businesslayer.UserDetailsBusinessLayer;
import com.example.devan.remedaily.datalayer.AppDatabase;

import java.util.List;

public class editUserDetails extends AppCompatActivity {

    public TextView firstNameTv,lastNameTv,ageTv;
    public EditText firstNameEd,lastNameEd,ageEd;
    public Button updateBtn, cancelBtn;
    public TextView firstNameError,lastNameError,ageError,showDB;
    public String firstName,lastName,age;
    public Context context;
    private boolean isValidate = true;
    public AppDatabase appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_details);
        appData = AppDatabase.getInMemoryDatabase(getApplicationContext());
        firstNameEd=findViewById(R.id.editTextFirstName);
        lastNameEd=findViewById(R.id.editTextLastName);
        ageEd=findViewById(R.id.editTextAge);
        updateBtn=findViewById(R.id.updateBtn);
        firstNameError=findViewById(R.id.firstNameValidateLbl);
        lastNameError=findViewById(R.id.lastNameValidateLbl);
        ageError=findViewById(R.id.ageValidateLbl);
        firstNameTv=findViewById(R.id.firstNameLbl);
        lastNameTv=findViewById(R.id.lastNameLbl);
        ageTv=findViewById(R.id.ageLbl);
        cancelBtn = findViewById(R.id.cancelBtn);
        context=this;

        //A method to validate the user input

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validations()){
                    try {
                        UserDetailsBusinessLayer.InsertRecordsAsync(appData,firstNameEd.getText().toString(),lastNameEd.getText().toString(),ageEd.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*TODO*/
            }
        });

        firstNameEd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    firstNameTv.setTextColor(ContextCompat.getColor(context, R.color.focus));
                }
                else {
                    firstNameTv.setTextColor(ContextCompat.getColor(context, R.color.notFocus));
                }
            }
        });

        lastNameEd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    lastNameTv.setTextColor(ContextCompat.getColor(context, R.color.focus));
                }
                else {
                    lastNameTv.setTextColor(ContextCompat.getColor(context, R.color.notFocus));
                }
            }
        });

        ageEd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ageTv.setTextColor(ContextCompat.getColor(context, R.color.focus));
                }
                else {
                    ageTv.setTextColor(ContextCompat.getColor(context, R.color.notFocus));
                }
            }
        });
    }

    public boolean Validations(){

        firstName=firstNameEd.getText().toString();
        if(firstName.matches("")) {
            isValidate =true;
            firstNameError.setText("\u274C"+"First name is required"+"\u274C");
            firstNameError.setVisibility(View.VISIBLE);
        }
        else {
            if (firstName.matches("[a-zA-Z]*")) {
                firstNameError.setVisibility(View.INVISIBLE);
            }
            else {
                firstNameError.setText("\u274C"+"Please enter a valid first name!!!"+"\u274C");
                firstNameError.setVisibility(View.VISIBLE);
            }
        }

        lastName=lastNameEd.getText().toString();
        if(lastName.matches("")) {
            isValidate =true;
            lastNameError.setText("\u274C"+"Last name is required"+"\u274C");
            lastNameError.setVisibility(View.VISIBLE);
        }
        else {
            if (lastName.matches("[a-zA-Z]*")) {
                lastNameError.setVisibility(View.INVISIBLE);
            }
            else {
                lastNameError.setText("\u274C"+"Please enter a valid last name!!!"+"\u274C");
                lastNameError.setVisibility(View.VISIBLE);
            }
        }

        age=ageEd.getText().toString();
        if(age.matches("")) {
            isValidate =true;
            ageError.setText("\u274C"+"Age is required"+"\u274C");
            ageError.setVisibility(View.VISIBLE);
        }
        else {

            if(Integer.parseInt(age)>0 && Integer.parseInt(age)<=125) {
                ageError.setVisibility(View.INVISIBLE);

            }
            else {
                ageError.setText("\u274C"+"Please enter a valid age!!!"+"\u274C");
                ageError.setVisibility(View.VISIBLE);
            }
        }
        return isValidate ;
    }
}

