package com.example.devan.remedaily.View;

/*
    Page Name : Calendar.java
    Author : Deep Singh (B00792279)
    Purpose : Backend java file calendar resource file
 */


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devan.remedaily.Models.Medicine;
import com.example.devan.remedaily.Models.NonDailyMedicine;
import com.example.devan.remedaily.R;
import com.example.devan.remedaily.businesslayer.MedicineBusinessLayer;
import com.example.devan.remedaily.datalayer.Med;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class Calender extends AppCompatActivity {

    private Context mContext;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);


        mContext = getApplicationContext();
        scrollView = findViewById(R.id.calendarViewResult);

        // view the back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            showCalendarView();
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
    private void showCalendarView() throws ParseException {
        // show the calendar view

        // there are 2 different types of medicines
        // first one is the daily type of medicine
        // the second one is the non daily type
        // in non daily type of medicine, we will get the datetime along with the medicine list


        LinearLayout lLayout = new LinearLayout(mContext);

        // set the layout params
        LinearLayout.LayoutParams ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getDPI(100));

        // set the weight
        ParamsObj.weight = 1.0f;

        lLayout.setLayoutParams(ParamsObj);
        lLayout.setOrientation(LinearLayout.VERTICAL);

        // we will first populate the daily medicine list
        MedicineBusinessLayer MedicineBusinessLayerObj = new MedicineBusinessLayer();
        List<Med> MedicineObj = MedicineBusinessLayerObj.getDailyMedicineCalendarWise(getApplicationContext());

        // check if we have the elements in the medicine object or not
        if(MedicineObj.size() > 0){

            // add the text
            TextView TextViewObj = new TextView(mContext);

            // set the padding
            TextViewObj.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

            // set the text
            TextViewObj.setText(R.string.txtdailyMedicineActivity);

            // set the background color
            TextViewObj.setBackgroundColor(getColor(R.color.white));

            // set the text size
            TextViewObj.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

            TextViewObj.setTextColor(getColor(R.color.black));

            lLayout.addView(TextViewObj);

            for (int i = 0; i < MedicineObj.size(); i++) {
                // adding cardview programatically
                // Source : https://android--code.blogspot.com/2015/12/android-how-to-create-cardview.html
                CardView CardViewObj = new CardView(mContext);

                // set the layout params
                ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getDPI(100));

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
                ChildTextView1.setText(MedicineObj.get(i).medName);

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
                ChildTextView2.setText(MedicineObj.get(i).dosage);

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
                String time = "";
                for(String _time: MedicineObj.get(i).timeObject.get(0)){
                    time += _time + " ";
                }
                ChildTextView3.setText(time.trim());

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

                lLayout.addView(CardViewObj);

            }
        }

        // display the medicines via datetime
        Map<Date, ArrayList<Med>> NonDailyMedicines = MedicineBusinessLayerObj.getNonDailyMedicineCalendarWise(getApplicationContext());

        // check if we have the elements in the medicine object or not
        if(NonDailyMedicines.size() > 0){


            for (Date DateKeys: NonDailyMedicines.keySet()) {
                // add the text
                TextView TextViewObj = new TextView(mContext);

                // set the padding
                TextViewObj.setPadding(getDPI(5), getDPI(5), getDPI(0), getDPI(0));

                // set the text
                TextViewObj.setText(new SimpleDateFormat("EEEE dd - MM - yyyy").format(DateKeys));

                // set the background color
                TextViewObj.setBackgroundColor(getColor(R.color.white));

                // set the text size
                TextViewObj.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

                TextViewObj.setTextColor(getColor(R.color.black));

                lLayout.addView(TextViewObj);

                for(Med MedList:NonDailyMedicines.get(DateKeys)){
                    // adding cardview programatically
                    // Source : https://android--code.blogspot.com/2015/12/android-how-to-create-cardview.html
                    CardView CardViewObj = new CardView(mContext);

                    // set the layout params
                    ParamsObj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getDPI(100));

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
                    ChildTextView1.setText(MedList.medName);

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
                    ChildTextView2.setText(MedList.dosage);

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
                    // get the start date
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    // source : https://stackoverflow.com/a/24409106
                    Date StartDate = simpleDateFormat.parse(MedList.startDate);

                    Calendar StartTimeCalendar = Calendar.getInstance();
                    StartTimeCalendar.setTime(DateKeys);
                    int DayToSelect = Math.abs(StartTimeCalendar.get(Calendar.DAY_OF_WEEK)) - 2;

                    if(DayToSelect == -1){
                        DayToSelect = 6; // it's sunday
                    }

                    String Dates = "";
                    for(String DatesString : MedList.timeObject.get(Integer.parseInt(Long.toString(DayToSelect)))){
                        Dates += DatesString + " ";
                    }

                    ChildTextView3.setText(Dates.trim());

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

                    lLayout.addView(CardViewObj);
                }


            }
        }
        scrollView.addView(lLayout);

    }
    // Source : https://stackoverflow.com/a/5959914
    public int getDPI(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}