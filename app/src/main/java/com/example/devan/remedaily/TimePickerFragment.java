package com.example.devan.remedaily;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class TimePickerFragment extends DialogFragment {
    // source : http://www.zoftino.com/android-timepicker-example

    private SelectedTime selectedTime;
    List<SelectedTime> selectedTimes;

    public void setSelectedTimes(List<SelectedTime> selectedTimes) {
        this.selectedTimes = selectedTimes;
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
                    selectedTime = new SelectedTime(hourOfDay, minute);
                    selectedTimes.add(selectedTime);
                    Toast.makeText(getActivity(), "selected time is "
                                    + hourOfDay +
                                    " : " + minute
                            , Toast.LENGTH_SHORT).show();
                }
            };


}
