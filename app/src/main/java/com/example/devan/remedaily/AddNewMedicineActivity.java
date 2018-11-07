package com.example.devan.remedaily;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class AddNewMedicineActivity extends AppCompatActivity {

    private TimePickerFragment timePickerFragment;
    private List<SelectedTime> selectedTimes = new ArrayList<>();
    private Button[] weekDayButtons = new Button[7];

    private final String[] weekDaysArr = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_medicine_activity);


        for(int weekDayIndex = 0; weekDayIndex < weekDaysArr.length; ++weekDayIndex) {
            int weekDayId = getResources().getIdentifier(weekDaysArr[weekDayIndex], "id",
                    getApplicationContext().getPackageName());

            final Button currentButton = findViewById(weekDayId);
            weekDayButtons[weekDayIndex] = currentButton;
            final int initialColor = ((ColorDrawable) currentButton.getBackground().mutate()).getColor();
            currentButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            currentButton.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorAccent));
                            return false;
                        case MotionEvent.ACTION_UP:
                            currentButton.setBackgroundColor(initialColor);
                            return false;
                    }
                    return false;
                }
            });
        }

        // source : https://android--code.blogspot.com/2015/08/android-switch-button-listener.html
        Switch sameScheduleSwitchButton = (Switch) findViewById(R.id.sameScheduleToAllDaysSwitch);
        sameScheduleSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    // Disable Week days buttons, as all will have the same schedule
                    for (Button weekDayButton : weekDayButtons) {
                        weekDayButton.setEnabled(false);
                    }
                }
                else {
                    //Enable Week days buttons, as each one will have it's own schedule
                    for (Button weekDayButton : weekDayButtons) {
                        weekDayButton.setEnabled(true);
                    }

                }
            }
        });

    }

    public void showTimePickerDialog() {
        timePickerFragment = new TimePickerFragment();
        timePickerFragment.setSelectedTimes(selectedTimes);
        timePickerFragment.show(getSupportFragmentManager(), "time picker");
    }
}
