/*Creator Aditya Gadhvi*/
package com.example.devan.remedaily.View;

//Importing all the necessary packages
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devan.remedaily.R;
import com.example.devan.remedaily.businesslayer.UserDetailsBusinessLayer;
import com.example.devan.remedaily.datalayer.AppDatabase;


public class UserDetails extends AppCompatActivity {

    //Declaring the Views and variables.

    public TextView firstNameTv,lastNameTv,ageTv,emailTv;
    public EditText firstNameEd,lastNameEd,ageEd,emailEd;
    public Button saveBtn, cancelBtn;
    public TextView firstNameError,lastNameError,ageError,emailError,showDB;
    public String firstName,lastName,age,email;
    public Context context;
    private boolean firstNameValidate = true,lastNameValidate = true,ageValidate = true,emailValidate=true;
    public AppDatabase appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initializing the different views and context
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        appData = AppDatabase.getInMemoryDatabase(getApplicationContext());
        firstNameEd=findViewById(R.id.editTextFirstName);
        lastNameEd=findViewById(R.id.editTextLastName);
        ageEd=findViewById(R.id.editTextAge);
        emailEd=findViewById(R.id.editTextEmail);
        saveBtn=findViewById(R.id.saveBtn);
        firstNameError=findViewById(R.id.firstNameValidateLbl);
        lastNameError=findViewById(R.id.lastNameValidateLbl);
        ageError=findViewById(R.id.ageValidateLbl);
        emailError=findViewById(R.id.emailValidateLbl);
        firstNameTv=findViewById(R.id.firstNameLbl);
        lastNameTv=findViewById(R.id.lastNameLbl);
        ageTv=findViewById(R.id.ageLbl);
        emailTv=findViewById(R.id.emailLbl);
        cancelBtn = findViewById(R.id.cancelBtn);
        context=this;
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Click functionality of the save button
        saveBtn.setOnClickListener(new View.OnClickListener(){
                @Override
            public void onClick(View v) {

                    Validations();


                        //The data will only be inserted if all of the flags are true. Otherwise it will execute the else part and data will not be inserted.

                if(firstNameValidate && lastNameValidate && ageValidate && emailValidate){ //The data will only be inserted if all of the flags are true. Otherwise it will execute the else part and data will not be inserted.
                    try {
                        UserDetailsBusinessLayer.InsertRecordsAsync(appData,firstNameEd.getText().toString(),lastNameEd.getText().toString(),ageEd.getText().toString());
                        Intent intent = new  Intent(getApplicationContext(),Home.class);
                        startActivity(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }});


        //Click functionality of the cancel button
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new  Intent(getApplicationContext(),Home.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Focus methods to determine whether the user clicked on the edittext or not.

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

        emailEd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    emailTv.setTextColor(ContextCompat.getColor(context, R.color.focus));

                }
                else {
                    emailTv.setTextColor(ContextCompat.getColor(context, R.color.notFocus));
                }
            }
        });



}


    // source : https://stackoverflow.com/questions/10108774/how-to-implement-the-android-actionbar-back-button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





    //A method to validate the user input. Different flags are used in this method. These flags will become true if the input is true, otherwise they will become false.
    public void Validations(){

        firstName=firstNameEd.getText().toString();
        if(firstName.matches("")) {
            firstNameError.setText("\u274C"+"First name is required"+"\u274C");
            firstNameError.setVisibility(View.VISIBLE);
            firstNameValidate =false;
        }
        else {
            if (firstName.matches("[a-zA-Z]*")) {
                firstNameError.setVisibility(View.INVISIBLE);
                firstNameValidate =true;

            }
            else {
                firstNameError.setText("\u274C"+"Please enter a valid first name!!!"+"\u274C");
                firstNameError.setVisibility(View.VISIBLE);
                firstNameValidate =false;

            }
        }

        lastName=lastNameEd.getText().toString();
        if(lastName.matches("")) {
            lastNameError.setText("\u274C"+"Last name is required"+"\u274C");
            lastNameError.setVisibility(View.VISIBLE);
            lastNameValidate =false;
        }
        else {
            if (lastName.matches("[a-zA-Z]*")) {
                lastNameError.setVisibility(View.INVISIBLE);
                saveBtn.setEnabled(true);
                lastNameValidate =true;
            }
            else {
                lastNameError.setText("\u274C"+"Please enter a valid last name!!!"+"\u274C");
                lastNameError.setVisibility(View.VISIBLE);
                lastNameValidate =false;
            }
        }

        age=ageEd.getText().toString();
        if(age.matches("")) {
            ageError.setText("\u274C"+"Age is required"+"\u274C");
            ageError.setVisibility(View.VISIBLE);
            ageValidate =false;
        }
        else {

            if(Integer.parseInt(age)>0 && Integer.parseInt(age)<=125) {
                ageError.setVisibility(View.INVISIBLE);
                ageValidate =true;
            }
            else {
                ageError.setText("\u274C"+"Please enter a valid age!!!"+"\u274C");
                ageError.setVisibility(View.VISIBLE);
                ageValidate =false;

            }
        }

        email=emailEd.getText().toString();
        if(email.matches("")) {
            emailError.setText("\u274C"+"Email id is required"+"\u274C");
            emailError.setVisibility(View.VISIBLE);
            emailValidate =false;
        }
        else {

            if(email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                emailError.setVisibility(View.INVISIBLE);
                emailValidate =true;
            }
            else {
                emailError.setText("\u274C"+"Please enter a valid email id!!!"+"\u274C");
                emailError.setVisibility(View.VISIBLE);
                emailValidate =false;

            }
        }

    }
}

