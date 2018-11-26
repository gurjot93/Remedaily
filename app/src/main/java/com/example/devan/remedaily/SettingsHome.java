package com.example.devan.remedaily;
/*
 * Class to create the Settings Home Page
 * Created by: Gurjot Singh (B00811724)
 * Created date: November 18, 2018
 * Version: 1
 * */
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/*
* Referred from: http://hacksmile.com/how-to-create-android-settings-screen-using-preferencefragment/

 * */
public class SettingsHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // this will create fragments from android.app.Fragment,
        Fragment fragment = new SettingsHolder();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //handling null exceptions
        if (savedInstanceState == null) {
            transaction.add(R.id.settings_homepage, fragment, "settings_screen");
        }
        transaction.commit();

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

    //class to add the settings_page.xml to the activity
    public static class SettingsHolder extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Calling the settings UI
            addPreferencesFromResource(R.xml.settings_page);

        }
    }
}
