package com.example.devan.remedaily;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TimeListAdapter extends ArrayAdapter<TimeEntry> {

    private ArrayList<TimeEntry> timeEntriesList;

    public TimeListAdapter(Context context, int resource, ArrayList<TimeEntry> timeEntries) {
        super(context, resource, timeEntries);
        this.timeEntriesList = timeEntries;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.schedule_entry, null);
        }

        TimeEntry timeEntry = timeEntriesList.get(position);

        if(null != timeEntry) {
            TextView time = view.findViewById(R.id.timeTextView);
            time.setText(timeEntry.getHour() + ":" + timeEntry.getMinute());
        }
        return view;
    }
}
