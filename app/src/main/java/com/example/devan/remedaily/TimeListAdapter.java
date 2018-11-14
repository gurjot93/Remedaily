package com.example.devan.remedaily;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class TimeListAdapter extends ArrayAdapter<TimeEntry> {

    private ArrayList<TimeEntry> timeEntriesList;
    private AddNewMedicineActivity addNewMedicineActivityObj;

    public TimeListAdapter(Context context, int resource, ArrayList<TimeEntry> timeEntries) {
        super(context, resource, timeEntries);
        this.timeEntriesList = timeEntries;
    }

    public void setAddNewMedicineActivityObj(AddNewMedicineActivity addNewMedicineActivityObj) {
        this.addNewMedicineActivityObj = addNewMedicineActivityObj;
    }

    @SuppressLint("ClickableViewAccessibility")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.schedule_entry, null);

            ImageButton removeTimeButton = view.findViewById(R.id.removeTimeButton);
            // source : https://jmsliu.com/2444/click-button-in-listview-and-get-item-position.html
            removeTimeButton.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View parentRow = (View) view.getParent();
                    ListView listView = (ListView) parentRow.getParent();
                    final int finalPosition = listView.getPositionForView(parentRow);
                    if(null != timeEntriesList.get(finalPosition)) {
                        timeEntriesList.remove(finalPosition);
                        if((0 == timeEntriesList.size()) &&
                                (null != addNewMedicineActivityObj.getCurrentDayButton())) {
                            addNewMedicineActivityObj.getCurrentDayButton().setTextColor(
                                    addNewMedicineActivityObj.getResources().getColor(R.color.colorBlack));
                        }
                        Collections.sort(timeEntriesList);
                        TimeListAdapter.this.notifyDataSetChanged();
                    }

                }
            });

            ImageButton editTimeButton = view.findViewById(R.id.editTimeButton);
            editTimeButton.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View parentRow = (View) view.getParent();
                    ListView listView = (ListView) parentRow.getParent();
                    final int finalPosition = listView.getPositionForView(parentRow);
                    addNewMedicineActivityObj.setTimePickerFragment(finalPosition);
                }
            });
        }

        TimeEntry timeEntry = timeEntriesList.get(position);

        if(null != timeEntry) {
            TextView time = view.findViewById(R.id.timeTextView);
            time.setText(String.format("%02d",timeEntry.getHour()) + ":" +
                    String.format("%02d",timeEntry.getMinute()));
        }
        return view;
    }
}
