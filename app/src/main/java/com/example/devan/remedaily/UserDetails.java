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
    public TextView FirstNameError,LastNameError,AgeError;
    public String firstName,lastName,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        firstNameTv=findViewById(R.id.editTextFirstName);
        lastNameTv=findViewById(R.id.editTextLastName);
        ageTv=findViewById(R.id.editTextAge);
        saveBtn=findViewById(R.id.saveBtn);
        FirstNameError=findViewById(R.id.firstNameValidateLbl);
        LastNameError=findViewById(R.id.lastNameValidateLbl);
        AgeError=findViewById(R.id.ageValidateLbl);

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
            FirstNameError.setText("\u274C"+"First name is required"+"\u274C");
            FirstNameError.setVisibility(View.VISIBLE);
        }
        else {
            if (firstName.matches("[a-zA-Z]*")) {
                FirstNameError.setVisibility(View.INVISIBLE);
            }
            else {
                FirstNameError.setText("\u274C"+"Please enter a valid first name!!!"+"\u274C");
                FirstNameError.setVisibility(View.VISIBLE);
            }
        }

        lastName=lastNameTv.getText().toString();
        if(lastName.matches("")) {
            LastNameError.setText("\u274C"+"Last name is required"+"\u274C");
            LastNameError.setVisibility(View.VISIBLE);
        }
        else {
            if (lastName.matches("[a-zA-Z]*")) {
                LastNameError.setVisibility(View.INVISIBLE);
            }
            else {
                LastNameError.setText("\u274C"+"Please enter a valid last name!!!"+"\u274C");
                LastNameError.setVisibility(View.VISIBLE);
            }
        }

        age=ageTv.getText().toString();
        if(age.matches("")) {
            AgeError.setText("\u274C"+"Age is required"+"\u274C");
            AgeError.setVisibility(View.VISIBLE);
        }
        else {
            if (age.matches("[1-9][0-9]{0,2}")) {
                AgeError.setVisibility(View.INVISIBLE);
            }
            else {
                AgeError.setText("\u274C"+"Please enter a valid age!!!"+"\u274C");
                AgeError.setVisibility(View.VISIBLE);
            }
        }
    }

}

