package com.example.devan.remedaily;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Collections;

public class DatePickerFragment extends DialogFragment {
    // source : http://www.zoftino.com/android-timepicker-example

    AddNewMedicineActivity addNewMedicineActivityObj;

    public void setAddNewMedicineActivityObj(AddNewMedicineActivity addNewMedicineActivityObj) {
        this.addNewMedicineActivityObj = addNewMedicineActivityObj;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(c.YEAR);
        int month = c.get(c.MONTH);
        int day = c.get(c.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }


    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    MedicineSchedule medicineSchedule = addNewMedicineActivityObj.getMedicineSchedule();
                        String date = String.format("%02d",month) + "/" +
                                String.format("%02d",day) + "/" + String.format("%04d",year);
                        addNewMedicineActivityObj.setDateToMedicineSchedule(date);
                }
            };
}
