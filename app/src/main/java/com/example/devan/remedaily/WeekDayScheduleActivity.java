package com.example.devan.remedaily;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class WeekDayScheduleActivity extends AppCompatActivity{

    private TimePickerFragment timePickerFragment;
    private List<SelectedTime> selectedTimes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_day_schedule_activity);
        showTimePickerDialog();
    }


    public void showTimePickerDialog() {
        Button[] weekDayButtons= AddNewMedicineActivity.weekDayButtons;
        timePickerFragment = new TimePickerFragment();
        timePickerFragment.setSelectedTimes(selectedTimes);
        timePickerFragment.show(getSupportFragmentManager(), "time picker");
    }
}
