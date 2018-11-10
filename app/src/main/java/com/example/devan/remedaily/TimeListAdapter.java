package com.example.devan.remedaily;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class TimeListAdapter extends ArrayAdapter<TimeEntry> {

    private ArrayList<TimeEntry> timeEntriesList;

    public TimeListAdapter(Context context, int resource, ArrayList<TimeEntry> timeEntries) {
        super(context, resource, timeEntries);
        this.timeEntriesList = timeEntries;
    }

    @SuppressLint("ClickableViewAccessibility")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        final int finalPosition = position;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.schedule_entry, null);

            ImageButton removeTimeButton = view.findViewById(R.id.removeTimeButton);
            removeTimeButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            if(null != timeEntriesList.get(finalPosition)) {
                                timeEntriesList.remove(finalPosition);
                                TimeListAdapter.this.notifyDataSetChanged();
                            }
                            return false;
                        case MotionEvent.ACTION_UP:
                            return false;
                    }
                    return false;
                }
            });
        }

        TimeEntry timeEntry = timeEntriesList.get(position);

        if(null != timeEntry) {
            TextView time = view.findViewById(R.id.timeTextView);
            time.setText(timeEntry.getHour() + ":" + timeEntry.getMinute());
        }
        return view;
    }
}
