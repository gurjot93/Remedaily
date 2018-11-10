package com.example.devan.remedaily;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimePickerFragment extends DialogFragment {
    // source : http://www.zoftino.com/android-timepicker-example

    private TimeEntry selectedTime;
    private Button saveButton;
    private TimeListAdapter timeListAdapter;
    ArrayList<TimeEntry> selectedTimes;

    public void setSelectedTimes(ArrayList<TimeEntry> selectedTimes) {
        this.selectedTimes = selectedTimes;
    }

    public void setTimeListAdapter(TimeListAdapter timeListAdapter) {
        this.timeListAdapter = timeListAdapter;
    }

    public void setSaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), timeSetListener, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }


    private TimePickerDialog.OnTimeSetListener timeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    selectedTime = new TimeEntry(hourOfDay, minute);
                    selectedTimes.add(selectedTime);
                    saveButton.setEnabled(true);

                    Toast.makeText(getActivity(), "selected time is "
                                    + hourOfDay +
                                    " : " + minute
                            , Toast.LENGTH_SHORT).show();
                    timeListAdapter.notifyDataSetChanged();

                }
            };


}
