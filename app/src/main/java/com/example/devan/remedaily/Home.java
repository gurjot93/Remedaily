/*
    Page Name : Home.java
    Author : Deep Singh (B00792279)
    Purpose : Presentation layer for the home page. It feeds the medicine information from the
    businesslayer/MedicineBusinessLayer.java file

 */


package com.example.devan.remedaily;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.devan.remedaily.Models.Medicine;
import com.example.devan.remedaily.businesslayer.MedicineBusinessLayer;

import java.text.ParseException;
import java.util.ArrayList;

public class Home extends AppCompatActivity {

    public Button userDetailsBtn;
    private Context mContext;
    private LinearLayout lLinearLayout;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // set the context
        mContext = getApplicationContext();

        lLinearLayout = findViewById(R.id.upcomingMedicineList);
        mLinearLayout = findViewById(R.id.linearLayoutMissedMedication);


        try {
            populateMissedMedicine();
            populateUpcomingMedicine();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void populateMissedMedicine() throws ParseException {
        // get the upcoming medicine details
        MedicineBusinessLayer MedicineObj = new MedicineBusinessLayer();

        ArrayList<Medicine> MedicineArrayList = MedicineObj.getMissedMedicineList();

        if (MedicineArrayList != null) {
            if (MedicineArrayList.size() != 0) {
                showMedicineOnScreen(MedicineArrayList, mLinearLayout);
            } else {
                showNoMedicationAvailable(mLinearLayout, R.string.NoMissedMedicine);
            }
        } else {
            showNoMedicationAvailable(mLinearLayout, R.string.NoMissedMedicine);
        }
    }

    private void showNoMedicationAvailable(LinearLayout linearLayout, int TextID){
        TextView ChildTextView2 = new TextView(mContext);

        // set the padding
        ChildTextView2.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

        // set the text
        ChildTextView2.setText(TextID);

        // set the text size
        ChildTextView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);

        ChildTextView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        ChildTextView2.setTextColor(getColor(R.color.Color_Home_Card_Dosage));

        // now since we have all the textview parameters
        // now add the values to the linearLayout
        linearLayout.addView(ChildTextView2);
    }

    private void showMedicineOnScreen(ArrayList<Medicine> MedicineArrayList, LinearLayout linearLayout) {
        for (int i = 0; i < MedicineArrayList.size(); i++) {


            // adding cardview programatically
            // Source : https://android--code.blogspot.com/2015/12/android-how-to-create-cardview.html
            CardView CardViewObj = new CardView(mContext);

            // set the layout params
            LinearLayout.LayoutParams ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getDPI(100));

            // set the weight
            ParamsObj.weight = 1.0f;

            // set the parameters
            CardViewObj.setLayoutParams(ParamsObj);

            CardViewObj.setBackgroundColor(getColor(R.color.white));

            // add margins
            // source : https://stackoverflow.com/a/50542988
            ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) CardViewObj.getLayoutParams();
            cardViewMarginParams.setMargins(getDPI(5), getDPI(5), getDPI(5), getDPI(5));

            // create a linear layout
            LinearLayout Parent = new LinearLayout(mContext);

            // set the linear layout params
            ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            // set the weight
            ParamsObj.weight = 1.0f;

            // set the orientation
            Parent.setOrientation(LinearLayout.HORIZONTAL);

            // set the padding
            Parent.setPadding(getDPI(5), getDPI(5), getDPI(5), getDPI(5));

            // set the layout
            Parent.setLayoutParams(ParamsObj);

            // Create the imageview object
            ImageView ChildImageObj = new ImageView(mContext);

            // set the linear layout params
            ParamsObj = new LinearLayout.LayoutParams(getDPI(180), ViewGroup.LayoutParams.WRAP_CONTENT);

            // set the weight for the image
            ParamsObj.weight = 1.0f;

            ChildImageObj.setLayoutParams(ParamsObj);

            // set the padding
            ChildImageObj.setPadding(getDPI(20), getDPI(20), getDPI(10), getDPI(20));

            // set the image ID
            int ImageID = R.drawable.medicine_pill;

            // set the imageID
            ChildImageObj.setImageResource(ImageID);

            // add the image to the linear layout
            Parent.addView(ChildImageObj);

            LinearLayout ChildLinearLayout = new LinearLayout(mContext);

            // set the linear layout params
            ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            // set the weight
            ParamsObj.weight = 1.0f;

            // set the orientation
            ChildLinearLayout.setOrientation(ChildLinearLayout.VERTICAL);

            // add the params
            ChildLinearLayout.setLayoutParams(ParamsObj);


            // set the linear layout params
            ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            // set the weight
            ParamsObj.weight = 1.0f;

            // Start of ChildTextView1
            // create a child textview object
            TextView ChildTextView1 = new TextView(mContext);

            // set the padding
            ChildTextView1.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

            // set the text
            ChildTextView1.setText(MedicineArrayList.get(i).getMedicineName());

            // set the background color
            ChildTextView1.setBackgroundColor(getColor(R.color.white));

            // set the text size
            ChildTextView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

            ChildTextView1.setTextColor(getColor(R.color.black));

            // now since we have all the textview parameters
            // now add the values to the linearLayout
            ChildLinearLayout.addView(ChildTextView1);
            // End of ChildTextView1

            // Start of ChildTextView2
            // create a child textview object
            TextView ChildTextView2 = new TextView(mContext);

            // set the padding
            ChildTextView2.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

            // set the text
            ChildTextView2.setText(MedicineArrayList.get(i).getMedicineDosage());

            // set the text size
            ChildTextView2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

            ChildTextView2.setTextColor(getColor(R.color.Color_Home_Card_Dosage));

            // now since we have all the textview parameters
            // now add the values to the linearLayout
            ChildLinearLayout.addView(ChildTextView2);
            // End of ChildTextView1

            // Start of ChildTextView3
            // create a child textview object
            TextView ChildTextView3 = new TextView(mContext);

            // set the padding
            ChildTextView3.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

            // set the text
            ChildTextView3.setText(MedicineArrayList.get(i).getDateTimeRegistered().toString());

            // set the text size
            ChildTextView3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

            ChildTextView3.setTextColor(getColor(R.color.Color_Home_Card_Dosage));

            // now since we have all the textview parameters
            // now add the values to the linearLayout
            ChildLinearLayout.addView(ChildTextView3);
            // End of ChildTextView1


            // add to the parent
            Parent.addView(ChildLinearLayout);

            // add the linear layout to the cardview
            CardViewObj.addView(Parent);

            CardViewObj.requestLayout();

            linearLayout.addView(CardViewObj);
        }
    }

    private void populateUpcomingMedicine() throws ParseException {


        // get the upcoming medicine details
        MedicineBusinessLayer MedicineObj = new MedicineBusinessLayer();

        ArrayList<Medicine> MedicineArrayList = MedicineObj.getUpcomingMedicineList();

        if (MedicineArrayList != null) {
            if (MedicineArrayList.size() != 0) {
                showMedicineOnScreen(MedicineArrayList, lLinearLayout);
            } else {
                showNoMedicationAvailable(lLinearLayout, R.string.NoUpcomingMedicine);
            }
        } else {
            showNoMedicationAvailable(lLinearLayout, R.string.NoUpcomingMedicine);
        }
    }

    // Source : https://stackoverflow.com/a/5959914
    public int getDPI(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}