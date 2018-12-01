package com.example.devan.remedaily.View;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devan.remedaily.AlarmReceiver;
import com.example.devan.remedaily.R;
import com.example.devan.remedaily.businesslayer.AddNewMedBusinessLayer;
import com.example.devan.remedaily.datalayer.AppDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddNewMedicineActivity extends AppCompatActivity {

    public static Button[] weekDayButtons = new Button[7];
    public AppDatabase appData;
    static int countDays =0;

    private final String[] weekDaysArr = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
            "Saturday", "Sunday"};
    public ArrayList<String> timeEntriesStrings;

    private TimeListAdapter timeListAdapter;

    private HashMap<String, WeekDay> buttonIdStrToWeekDayMap = new HashMap<>();

    private WeekDay currentDay;
    private Button saveButton;
    private Button currentDayButton;
    private MedicineSchedule medicineSchedule;

    private TimePickerFragment timePickerFragment;
    private DatePickerFragment datePickerFragment;
    boolean isSettingStartDate;

    static final int REQUEST_TAKE_PHOTO = 1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_medicine_activity);
        appData = AppDatabase.getInMemoryDatabase(getApplicationContext());
        findViewById(R.id.addTimeButton).setEnabled(false);
        saveButton = findViewById(R.id.saveButton);
        //saveButton.setEnabled(false);

        medicineSchedule = new MedicineSchedule();

        // hide soft key keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //create back button
        // source : https://www.youtube.com/watch?v=s3pheMAmaPI
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // source : https://android--code.blogspot.com/2015/08/android-switch-button-listener.html
        final Switch sameScheduleSwitchButton = (Switch) findViewById(R.id.sameScheduleToAllDaysSwitch);
        sameScheduleSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentDay = new WeekDay("AllDays");
                if (isChecked) {
                    // Add Time button should be active only when week mode selected
                    // or specific day selected
                    Button addTimeBtn = (Button) findViewById(R.id.addTimeButton);
                    addTimeBtn.setEnabled(true);
                    addTimeBtn.setTextColor(getResources().getColor(R.color.colorBlack));
                    // Discard possibly existing schedules, as we are going to fill all the same
                    // for every wek day:
                    clearCurrentSchedule();
                    // Disable Week days buttons, as all will have the same schedule:
                    enableButtons(false);
                    AddNewMedicineActivity.this.currentDayButton = null;
                    setListViewAdapter(currentDay);
                } else {
                    // Add Time button should be active only when week mode selected
                    // or specific day selected
                    Button addTimeBtn = (Button) findViewById(R.id.addTimeButton);
                    addTimeBtn.setEnabled(false);
                    addTimeBtn.setTextColor(getResources().getColor(R.color.colorButtonText));
                    // Discard possibly existing schedules, as we are going to fill different times
                    // for different week days
                    clearCurrentSchedule();
                    //Enable Week days buttons, as each one will have it's own schedule
                    enableButtons(true);
                    setListViewAdapter(currentDay);
                }
            }
        });


        ((Button) findViewById(R.id.startDateButton)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return false;
                    case MotionEvent.ACTION_UP:
                        if (buttonNotInFocus(view, event)) {
                            return false;
                        }
                        AddNewMedicineActivity.this.isSettingStartDate = true;
                        setDatePickerFragment();
                        return false;
                }
                return false;
            }
        });


        ((Button) findViewById(R.id.endDateButton)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return false;
                    case MotionEvent.ACTION_UP:
                        if (buttonNotInFocus(view, event)) {
                            return false;
                        }
                        AddNewMedicineActivity.this.isSettingStartDate = false;
                        setDatePickerFragment();
                        return false;
                }
                return false;
            }
        });


        for (int weekDayIndex = 0; weekDayIndex < weekDaysArr.length; ++weekDayIndex) {
            int weekDayId = getResources().getIdentifier(weekDaysArr[weekDayIndex], "id",
                    getApplicationContext().getPackageName());

            buttonIdStrToWeekDayMap.put(Integer.toString(weekDayId), new WeekDay(weekDaysArr[weekDayIndex]));

            final Button currentDayButton = findViewById(weekDayId);
            weekDayButtons[weekDayIndex] = currentDayButton;
            currentDayButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            return false;
                        case MotionEvent.ACTION_UP:
                            if (buttonNotInFocus(view, event)) {
                                return false;
                            }
                            setCurrentDayButtonSelected(currentDayButton);
                            AddNewMedicineActivity.this.currentDayButton = currentDayButton;
                            // Add Time button should be active only when week mode selected
                            // or specific day selected
                            Button addTimeBtn = (Button) findViewById(R.id.addTimeButton);
                            addTimeBtn.setEnabled(true);
                            addTimeBtn.setTextColor(getResources().getColor(R.color.colorBlack));
                            int buttonId = currentDayButton.getId();
                            currentDay = buttonIdStrToWeekDayMap.get(Integer.toString(buttonId));
                            setListViewAdapter(currentDay);
                            return false;
                    }
                    return false;
                }
            });
        }


        Button addTimeButton = findViewById(R.id.addTimeButton);
        addTimeButton.setEnabled(false);
        addTimeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return false;
                    case MotionEvent.ACTION_UP:
                        if (buttonNotInFocus(view, event)) {
                            return false;
                        }
                        setTimePickerFragment(-1);
                        return false;
                }
                return false;
            }
        });


        saveButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                String medicineName = ((EditText) findViewById(R.id.newMedicineNameField)).getText().toString();
                String medicineDosage = ((EditText) findViewById(R.id.newMedicineDosageField)).getText().toString();
                boolean isDaily = sameScheduleSwitchButton.isChecked();
                boolean startDateIsSet = (null != medicineSchedule.getStartDate());
                boolean endDateIsSet = (null != medicineSchedule.getEndDate());

                // check if no time was scheduled. In this case SAVE button will not perform saving
                boolean administrationTimeIsScheduled = false;
                if (isDaily) {
                    administrationTimeIsScheduled = !currentDay.getTimeEntriesList().isEmpty();
                } else {
                    for (WeekDay weekDay : buttonIdStrToWeekDayMap.values()) {
                        if (!weekDay.getTimeEntriesList().isEmpty()) {
                            administrationTimeIsScheduled = true;
                            break;
                        }
                    }
                }

                boolean scheduleDataIsValidForSaving = (!medicineName.equals("")) &&
                        (!medicineDosage.equals("")) &&
                        startDateIsSet &&
                        endDateIsSet &&
                        administrationTimeIsScheduled;

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (!scheduleDataIsValidForSaving) {
                            saveButton.setBackgroundResource(R.drawable.button_error);
                            return false;
                        }
                        return false;
                    case MotionEvent.ACTION_UP:
                        if (buttonNotInFocus(view, event)) {
                            return false;
                        }
                        if (!scheduleDataIsValidForSaving) {
                            saveButton.setBackgroundResource(R.drawable.button);

                            Toast toast = getToastDialog();
                            if (medicineName.equals("")) {
                                toast.setText("Please set medicine name");
                                toast.show();
                                return false;
                            }
                            if (medicineDosage.equals("")) {
                                toast.setText("Please set medicine dosage");
                                toast.show();
                                return false;
                            }
                            if (!startDateIsSet) {
                                toast.setText("Please set start date");
                                toast.show();
                                return false;
                            }
                            if (!endDateIsSet) {
                                toast.setText("Please set end date");
                                toast.show();
                                return false;
                            }
                            if (!administrationTimeIsScheduled) {
                                toast.setText("Please schedule time for medicine");
                                toast.show();
                                return false;
                            }
                            return false;
                        }
                        // if we are in weekly mode, make each week day have the same, but
                        // independent time entries list
                        if (sameScheduleSwitchButton.isChecked()) {
                            for (WeekDay weekDay : buttonIdStrToWeekDayMap.values()) {
                                ArrayList<TimeEntry> timeEntriesList = weekDay.getTimeEntriesList();
                                for (TimeEntry timeEntry : currentDay.getTimeEntriesList())
                                    timeEntriesList.add(new TimeEntry(timeEntry.getHour(), timeEntry.getMinute()));
                            }
                        }

                        medicineSchedule.setName(medicineName);
                        medicineSchedule.setDosage(medicineDosage);

                        if(isDaily){
                            medicineSchedule.setIsDaily(1);
                        }else{
                            medicineSchedule.setIsDaily(0);
                        }

                        //not setting start and end dates here. They are set in date picker

                        for (int weekDayIndex = 0; weekDayIndex < weekDaysArr.length; ++weekDayIndex) {
                            int weekDayId = getResources().getIdentifier(weekDaysArr[weekDayIndex], "id",
                                    getApplicationContext().getPackageName());
                            timeEntriesStrings = new ArrayList<>();

                            for (TimeEntry timeEntry : buttonIdStrToWeekDayMap.get(Integer.toString(weekDayId)).getTimeEntriesList()) {
                                String time = String.format("%02d", timeEntry.getHour()) + ":" +
                                        String.format("%02d", timeEntry.getMinute());
                                timeEntriesStrings.add(time);
                            }
                            medicineSchedule.getWeekSchedule().add(timeEntriesStrings); //ArrayList<Arraylist<Strings>> builds here.
                        }
                        String savingMessage = "Saving";
                        SpannableString spannableString = new SpannableString(savingMessage);
                        spannableString.setSpan(new RelativeSizeSpan(2f), 0, spannableString.length(), 0);
                        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spannableString.length(), 0);

                        ProgressDialog progressWheelDialog = new ProgressDialog(AddNewMedicineActivity.this);
                        progressWheelDialog.setMessage(spannableString);
                        progressWheelDialog.show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                AddNewMedicineActivity.this.finish();
                            }
                        }, 1500);
                        // call here DB method to pass it reference to medicineSchedule object
                        try {

                            String medName = medicineSchedule.getName();
                            String medDosage = medicineSchedule.getDosage();
                            String medImagePath = medicineSchedule.getDrugBoxImagePath();
                            String medStartDate = medicineSchedule.getStartDate();
                            String medEndDate = medicineSchedule.getEndDate();
                            int tagDaily = 0;

                            if(sameScheduleSwitchButton.isChecked()){
                                tagDaily = 1;
                            }
                            AddNewMedBusinessLayer.AddMeds(appData, tagDaily, medName, medDosage, medImagePath, medStartDate, medEndDate, medicineSchedule.getWeekSchedule());

                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy'T'HH:mm");
//                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");


                            for (int i = 0; i < 7; i++) {
                                if (medicineSchedule.getWeekSchedule().get(i).size() > 0) {
                                    int git = medicineSchedule.getWeekSchedule().get(i).size();
                                    for (int j = 0; j < git; j++) {


                                        Date date = format.parse(medStartDate + "T" + medicineSchedule.getWeekSchedule().get(i).get(j));
//                                        Date aa = format.parse(medStartDate + "T" + medicineSchedule.getWeekSchedule().get(i).get(git-1));

//                                        long timeInMilliseconds = aa.getTime()- date.getTime();

                                        handleNotification(date.getTime(), 0);
                                    }
                                }
                            }
//                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return false;
                }
                return false;
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

    private void enableButtons(boolean doEnable) {
        for (Button weekDayButton : weekDayButtons) {
            weekDayButton.setEnabled(doEnable);
            if (doEnable) {
                weekDayButton.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        }
        // source : https://stackoverflow.com/questions/8863776/android-layout-wont-redraw-after-setvisibilityview-gone/8863908
        RelativeLayout weekDaysRelativeLayout = (RelativeLayout) findViewById(R.id.weekDaysRelativeLayout);
        if (doEnable) {
            weekDaysRelativeLayout.setVisibility(View.VISIBLE);
        } else {
            weekDaysRelativeLayout.setVisibility(View.GONE);
        }
        setCurrentDayButtonSelected(null);
    }

    private void clearCurrentSchedule() {
        for (WeekDay weekDay : buttonIdStrToWeekDayMap.values()) {
            weekDay.getTimeEntriesList().clear();
        }

        // clear current day - useful when switchin from week schedule to day schedule
        if (null != currentDay) {
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
        timePickerFragment.setAddNewMedicineActivityObj(this);
        timePickerFragment.show(getSupportFragmentManager(), "time picker");
    }

    public void setDatePickerFragment() {
        datePickerFragment = new DatePickerFragment();
        datePickerFragment.setAddNewMedicineActivityObj(this);
        datePickerFragment.show(getSupportFragmentManager(), "time picker");
    }

    public Button getCurrentDayButton() {
        return this.currentDayButton;
    }

    public WeekDay getCurrentDay() {
        return this.currentDay;
    }

    public MedicineSchedule getMedicineSchedule() {
        return medicineSchedule;
    }

    public TimeListAdapter getTimeListAdapter() {
        return this.timeListAdapter;
    }

    public boolean allDaysAreEmpty() {
        for (WeekDay weekDay : buttonIdStrToWeekDayMap.values()) {
            if (weekDay.getTimeEntriesList().size() > 0) {
                return false;
            }
        }
        return true;
    }

    private void setCurrentDayButtonSelected(Button currentDayButton) {
        for (Button weekDayButton : weekDayButtons) {
            if (weekDayButton == currentDayButton) {
                weekDayButton.setBackgroundResource(R.drawable.day_button_selected_round_corns);
            } else {
                weekDayButton.setBackgroundResource(R.drawable.button);
            }
        }
    }

    public void setDateToMedicineSchedule(String date) {
        Toast toastDialog = getToastDialog();
        TextView startDateSelected = (TextView) findViewById(R.id.startDateSelected);
        TextView endDateSelected = (TextView) findViewById(R.id.endDateSelected);

        if (isSettingStartDate) {
            if (null != medicineSchedule.getEndDate()) {
                if (date.compareTo(medicineSchedule.getEndDate()) < 0) {
                    medicineSchedule.setStartDate(date);
                    startDateSelected.setText(date);
                } else {
                    toastDialog.setText("Date not set - start day must occur before end date");
                    toastDialog.show();
                }
            } else {
                medicineSchedule.setStartDate(date);
                startDateSelected.setText(date);
            }
        } else {
            if (null != medicineSchedule.getStartDate()) {
                if (date.compareTo(medicineSchedule.getStartDate()) > 0) {
                    medicineSchedule.setEndDate(date);
                    endDateSelected.setText(date);
                } else {
                    toastDialog.setText("Date not set - end date must occur after start date");
                    toastDialog.show();
                }
            } else {
                medicineSchedule.setEndDate(date);
                endDateSelected.setText(date);
            }
        }
    }

    private Toast getToastDialog() {
        Toast toast = Toast.makeText(AddNewMedicineActivity.this, ""
                , Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 250);

        //set text size
        // source : https://stackoverflow.com/questions/5274354/how-can-we-increase-the-font-size-in-toast
        ViewGroup group = (ViewGroup) toast.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(25);

        return toast;
    }

    private boolean buttonNotInFocus(View view, MotionEvent event) {
        Rect rect = new Rect();
        // get the View's (Button's) Rect relative to its parent
        view.getHitRect(rect);
        // offset the touch coordinates with the values from r
        // to obtain meaningful coordinates
        final float x = event.getX() + rect.left;
        final float y = event.getY() + rect.top;
        if (!rect.contains((int) x, (int) y)) {
            return true;
        }
        return false;
    }

    public void takeDrugBoxImageShot(View view) {
        Intent cameraIntent = new Intent(getApplicationContext(), com.example.devan.remedaily.View.Camera.class);
        String currentPhotoPath = medicineSchedule.getDrugBoxImagePath();
        cameraIntent.putExtra("previousPhotoPath", currentPhotoPath == null ? "" : currentPhotoPath);
        startActivityForResult(cameraIntent, REQUEST_TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                String currentPhotoPath = data.getStringExtra("currentPhotoPath");
                medicineSchedule.setDrugBoxImagePath(currentPhotoPath);
                setDrugBoxImage(currentPhotoPath);
            } else {
                medicineSchedule.setDrugBoxImagePath(null);
            }
        }
    }

    public void setDrugBoxImage(String drugBoxImagePath) {
        this.medicineSchedule.setDrugBoxImagePath(drugBoxImagePath);

        try {
            Bitmap drugBoxBitmapImage = null;
            File drugBoxImageFile = new File(drugBoxImagePath);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            drugBoxBitmapImage = BitmapFactory.decodeStream(new FileInputStream(drugBoxImageFile),
                    null, options);
            ((CircleImageView) findViewById(R.id.drugBoxImage)).setImageBitmap(drugBoxBitmapImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleNotification(long intmill, long intmilldiff) {
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        final int _id = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, _id, alarmIntent,
                PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, intmill, pendingIntent);

    }

}

