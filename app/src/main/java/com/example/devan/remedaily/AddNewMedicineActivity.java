package com.example.devan.remedaily;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.HashMap;

public class AddNewMedicineActivity extends AppCompatActivity {

    public static Button[] weekDayButtons = new Button[7];

    private final String[] weekDaysArr = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
                                          "Saturday", "Sunday"};

    private TimeListAdapter timeListAdapter;

    private HashMap<String, WeekDay> buttonIdStrToWeekDayMap = new HashMap<>();

    WeekDay currentDay;
    Button saveButton;

    private TimePickerFragment timePickerFragment;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_medicine_activity);

        findViewById(R.id.addTimeButton).setEnabled(false);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setEnabled(false);

        // hide soft key keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        // source : https://android--code.blogspot.com/2015/08/android-switch-button-listener.html
        final Switch sameScheduleSwitchButton = (Switch) findViewById(R.id.sameScheduleToAllDaysSwitch);
        sameScheduleSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentDay = new WeekDay("AllDays");
                if(isChecked) {
                    // Add Time button should be active only when week mode selected
                    // or specific day selected
                    findViewById(R.id.addTimeButton).setEnabled(true);
                    // Discard possibly existing schedules, as we are going to fill all the same
                    // for every wek day:
                    clearCurrentSchedule();
                    // Disable Week days buttons, as all will have the same schedule:
                    enableButtons(false);
                    setListViewAdapter(currentDay);
                }
                else {
                    // Add Time button should be active only when week mode selected
                    // or specific day selected
                    findViewById(R.id.addTimeButton).setEnabled(false);
                    // Discard possibly existing schedules, as we are going to fill different times
                    // for different week days
                    clearCurrentSchedule();
                    //Enable Week days buttons, as each one will have it's own schedule
                    enableButtons(true);
                    setListViewAdapter(currentDay);
                }
            }
        });


        for(int weekDayIndex = 0; weekDayIndex < weekDaysArr.length; ++weekDayIndex) {
            int weekDayId = getResources().getIdentifier(weekDaysArr[weekDayIndex], "id",
                    getApplicationContext().getPackageName());

            buttonIdStrToWeekDayMap.put(Integer.toString(weekDayId), new WeekDay(weekDaysArr[weekDayIndex]));

            final Button currentButton = findViewById(weekDayId);
            weekDayButtons[weekDayIndex] = currentButton;
            currentButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            // Add Time button should be active only when week mode selected
                            // or specific day selected
                            findViewById(R.id.addTimeButton).setEnabled(true);
                            int buttonId = currentButton.getId();
                            currentDay = buttonIdStrToWeekDayMap.get(Integer.toString(buttonId));
                            setListViewAdapter(currentDay);
                            return false;
                        case MotionEvent.ACTION_UP:
                            return false;
                    }
                    return false;
                }
            });
        }


        Button addTimeButton = findViewById(R.id.addTimeButton);
        addTimeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        setTimePickerFragment(-1);
                        return false;
                    case MotionEvent.ACTION_UP:
                        return false;
                }
                return false;
            }
        });


        saveButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // if we are in weekly mode, make each week day have the same, but
                        // independent time entries list
                        if(sameScheduleSwitchButton.isChecked()) {
                            for(WeekDay weekDay : buttonIdStrToWeekDayMap.values()) {
                                ArrayList<TimeEntry> timeEntriesList = weekDay.getTimeEntriesList();
                                for(TimeEntry timeEntry : currentDay.getTimeEntriesList())
                                timeEntriesList.add(new TimeEntry(timeEntry.getHour(),timeEntry.getMinute()));
                            }
                        }

                        String mediceneName = findViewById(R.id.newMedicineNameField).toString();


                        // here come stuff to be done on save - how do we save schedule and name?

                        return false;
                    case MotionEvent.ACTION_UP:
                        return false;
                }
                return false;
            }
        });

    }

    private void enableButtons(boolean doEnable) {
        for (Button weekDayButton : weekDayButtons) {
            weekDayButton.setEnabled(doEnable);
        }
        saveButton.setEnabled(false); // enabled in TimePickerFragment ont first time stemp creation
    }

    private void clearCurrentSchedule() {
        for(WeekDay weekDay : buttonIdStrToWeekDayMap.values()) {
            weekDay.getTimeEntriesList().clear();
        }

        // clear current day - useful when switchin from week schedule to day schedule
        if(null != currentDay) {
            currentDay.getTimeEntriesList().clear();
        }
    }

    private void setListViewAdapter(WeekDay currentDay) {
        timeListAdapter = new TimeListAdapter(AddNewMedicineActivity.this,
                R.layout.schedule_entry, currentDay.getTimeEntriesList());
        timeListAdapter.setAddNewMedicineActivityObj(this);

        ListView timeEntriesListView = findViewById(R.id.timeEntriesListView);
        timeEntriesListView.setAdapter(timeListAdapter);
    }

    public void setTimePickerFragment(final int position) {
        timePickerFragment = new TimePickerFragment();
        timePickerFragment.setPosition(position);
        timePickerFragment.setSelectedTimes(currentDay.getTimeEntriesList());
        timePickerFragment.setTimeListAdapter(timeListAdapter);
        timePickerFragment.setSaveButton(saveButton);
        timePickerFragment.show(getSupportFragmentManager(), "time picker");
    }
}
