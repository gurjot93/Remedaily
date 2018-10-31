package com.example.devan.remedaily;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.devan.remedaily.db.AppDatabase;
import com.example.devan.remedaily.db.utils.DatabaseInitializer;
import com.example.devan.remedaily.db.Med;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private AppDatabase mDb;
    private TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView= findViewById(R.id.txtView);
        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());
        populateDb();
        fetchData();

    }
    private void populateDb() {
        DatabaseInitializer.populateSync(mDb);
    }
    private void fetchData() {
        // Note: this kind of logic should not be in an activity.
        StringBuilder sb = new StringBuilder();
        List<Med> youngUsers = mDb.medModel().loadAllMeds();
        for (Med youngUser : youngUsers) {
            sb.append(String.format(
                    "%s, %s , %s\n", youngUser.medName, youngUser.createDate, youngUser.expiryDate));
        }
        txtView.setText(sb);
    }

}
