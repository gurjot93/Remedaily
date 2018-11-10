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
import java.util.Collections;

public class TimePickerFragment extends DialogFragment {
    // source : http://www.zoftino.com/android-timepicker-example

    private TimeEntry selectedTime;
    int position;
    AddNewMedicineActivity addNewMedicineActivityObj;

    public void setPosition(final int position) {
        this.position = position;
    }

    public void setAddNewMedicineActivityObj(AddNewMedicineActivity addNewMedicineActivityObj) {
        this.addNewMedicineActivityObj = addNewMedicineActivityObj;
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
                    for(TimeEntry timeEntry : addNewMedicineActivityObj.getCurrentDay().getTimeEntriesList()) {
                        if(selectedTime.compareTo(timeEntry) == 0) {
                            Toast.makeText(getActivity(), "Not added - attempt to add existing time" +
                                    String.format("%02d",hourOfDay) + ":" +
                                            String.format("%02d",minute)
                                    , Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    if(position >= 0) {
                        addNewMedicineActivityObj.getCurrentDay().getTimeEntriesList().set(position, selectedTime);
                    }
                    else {
                        addNewMedicineActivityObj.getCurrentDay().getTimeEntriesList().add(selectedTime);
                    }
                    addNewMedicineActivityObj.getSaveButton().setEnabled(true);
                    addNewMedicineActivityObj.getCurrentButton().setTextColor(
                            addNewMedicineActivityObj.getResources().getColor(R.color.colorBlue));

                    Collections.sort(addNewMedicineActivityObj.getCurrentDay().getTimeEntriesList());
                    addNewMedicineActivityObj.getTimeListAdapter().notifyDataSetChanged();
                }
            };
}
