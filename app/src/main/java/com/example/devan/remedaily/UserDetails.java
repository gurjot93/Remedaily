package com.example.devan.remedaily;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserDetails extends AppCompatActivity {

    public EditText firstNameTv,lastNameTv,ageTv;
    public Button saveBtn;
    public TextView firstNameError,lastNameError,ageError;
    public String firstName,lastName,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        firstNameTv=findViewById(R.id.editTextFirstName);
        lastNameTv=findViewById(R.id.editTextLastName);
        ageTv=findViewById(R.id.editTextAge);
        saveBtn=findViewById(R.id.saveBtn);
        firstNameError=findViewById(R.id.firstNameValidateLbl);
        lastNameError=findViewById(R.id.lastNameValidateLbl);
        ageError=findViewById(R.id.ageValidateLbl);

        //A method to validate the user input

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validations();

            }
        });
    }

    public  void Validations(){

        firstName=firstNameTv.getText().toString();
        if(firstName.matches("")) {
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

        lastName=lastNameTv.getText().toString();
        if(lastName.matches("")) {
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

        age=ageTv.getText().toString();
        if(age.matches("")) {
            ageError.setText("\u274C"+"Age is required"+"\u274C");
            ageError.setVisibility(View.VISIBLE);
        }
        else {
            if (age.matches("[1-9][0-9]{0,2}")) {
                ageError.setVisibility(View.INVISIBLE);
            }
            else {
                ageError.setText("\u274C"+"Please enter a valid age!!!"+"\u274C");
                ageError.setVisibility(View.VISIBLE);
            }
        }
    }

}

