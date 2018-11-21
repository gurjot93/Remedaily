package com.example.devan.remedaily.View;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.devan.remedaily.R;

public class Calender extends AppCompatActivity {

    ListView listView;

    int[] IMAGES={R.drawable.pill_icon,R.drawable.pill_icon,R.drawable.pill_icon,R.drawable.pill_icon};
    String[] NAMES={"Inhaler","Vitamin B12","Tylenol","Advil"};
    String[] DAYS={"Daily","Mondays","Tuesdays","Wednesdays"};
    String[] DOSAGE={"1 Dose,1 Inhalation","1 Dose,1 Inhalation","1 Dose,1 Inhalation","1 Dose,1 Inhalation"};
    String[] TIMES={"Daily, 9:00 AM","Daily, 9:00 AM","Daily, 9:00 AM","Daily, 9:00 AM"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        ImageView imageView=(ImageView)findViewById(R.id.imageView);



        listView=(ListView)findViewById(R.id.mainListView);
        CustomAdaptor customAdaptor=new CustomAdaptor();
        listView.setAdapter(customAdaptor);


    }

    public class CustomAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view=getLayoutInflater().inflate(R.layout.medicine_list,null);
            ImageView imageView=(ImageView)view.findViewById(R.id.imageView);
            TextView medicineNameTv=(TextView)view.findViewById(R.id.medicineNameTv);
            TextView medicineDosageTv=(TextView)view.findViewById(R.id.medicineDosageTv);
            TextView medicineTimeTv=(TextView)view.findViewById(R.id.medicineTimeTv);
            TextView daysTv=(TextView)view.findViewById(R.id.dayTv);

            imageView.setImageResource(IMAGES[i]);
            medicineNameTv.setText(NAMES[i]);
            medicineDosageTv.setText(DOSAGE[i]);
            medicineTimeTv.setText(TIMES[i]);
            daysTv.setText(DAYS[i]);

            Bitmap bitmap=BitmapFactory.decodeResource(getResources(),IMAGES[i]);
            RoundedBitmapDrawable roundedBitmapDrawable=RoundedBitmapDrawableFactory.create(getResources(),bitmap);
            roundedBitmapDrawable.setCircular(true);
            imageView.setImageDrawable(roundedBitmapDrawable);

            return view;
        }
    }
}