package com.example.devan.remedaily.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devan.remedaily.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help extends AppCompatActivity {
    public ExpandableListView expandableListView;
    public ExpandableListAdapter expandableListAdapter;
    public List<String> listDataHeader;
    public HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        expandableListView=(ExpandableListView)findViewById(R.id.helpListView);
        initData();
        expandableListAdapter=new ExpandableListAdaptor(this,listDataHeader,listHash);
        expandableListView.setAdapter(expandableListAdapter);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void initData() {
        listDataHeader=new ArrayList<>();
        listHash=new HashMap<>();

        listDataHeader.add(getResources().getString(R.string.help1_label));
        listDataHeader.add(getResources().getString(R.string.help2_label));
        listDataHeader.add(getResources().getString(R.string.help3_label));
        listDataHeader.add(getResources().getString(R.string.help4_label));
        listDataHeader.add(getResources().getString(R.string.help5_label));
        listDataHeader.add(getResources().getString(R.string.help6_label));
        listDataHeader.add(getResources().getString(R.string.help7_label));

        List<String> first=new ArrayList<>();
        first.add(getResources().getString(R.string.help1_desc));

        List<String> second=new ArrayList<>();
        second.add(getResources().getString(R.string.help2_desc));

        List<String> third=new ArrayList<>();
        third.add(getResources().getString(R.string.help3_desc));

        List<String> fourth=new ArrayList<>();
        fourth.add(getResources().getString(R.string.help4_desc));

        List<String> fifth=new ArrayList<>();
        fifth.add(getResources().getString(R.string.help5_desc));

        List<String> sixth=new ArrayList<>();
        sixth.add(getResources().getString(R.string.help6_desc));

        List<String> seventh=new ArrayList<>();
        seventh.add(getResources().getString(R.string.help7_desc));

        listHash.put(listDataHeader.get(0),first);
        listHash.put(listDataHeader.get(1),second);
        listHash.put(listDataHeader.get(2),third);
        listHash.put(listDataHeader.get(3),fourth);
        listHash.put(listDataHeader.get(4),fifth);
        listHash.put(listDataHeader.get(5),sixth);
        listHash.put(listDataHeader.get(6),seventh);

    }

}
