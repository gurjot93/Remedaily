package com.example.devan.remedaily.View;
/*
 * Activity to Display Medicine Details for the Current Medicine
 * Created by: Gurjot Singh (B00811724)
 * Created date: December 3, 2018
 * Version: 1
 * */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.devan.remedaily.R;

public class NotificationDisplayMedicine extends AppCompatActivity {
    /*
    * Textview for the Title Page, MedicineName, MedicineDetails
    * */
    TextView title;
    TextView medName;
    TextView medDosage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.display_current_medicine);
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*
        * Fetching details for medicine details from the notification
        * */
        Bundle extras = getIntent().getExtras();
        title=findViewById(R.id.dayTv);
        medName=findViewById(R.id.medicineNameTv);
        medDosage=findViewById(R.id.medicineDosageTv);
        /*
        * Displaying the details
        * */

        title.setText("Medicine Details");
        medName.setText(extras.getString("name"));
        medDosage.setText(extras.getString("description"));
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
}