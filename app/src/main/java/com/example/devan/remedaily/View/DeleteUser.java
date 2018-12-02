package com.example.devan.remedaily.View;

/*
 * Activity to ask User once again to delete the user profile and then deleting the data(Applying Neilson's Heuristics)
 * Created by: Gurjot Singh (B00811724)
 * Created date: December 1, 2018
 * Version: 1
 * */
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.devan.remedaily.R;
import com.example.devan.remedaily.businesslayer.ResetApplicationLayer;
import com.example.devan.remedaily.datalayer.AppDatabase;

public class DeleteUser extends AppCompatActivity {
    Button deleteUser;
    public AppDatabase appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_user);
        deleteUser=(Button) findViewById(R.id.deleteUser);
        /*
        * back button generation
        * */
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appData = AppDatabase.getInMemoryDatabase(getApplicationContext());

        //listener for the button deleteUser
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method to be called to delete the user

                /*
                 * Deleting the user data by calling the functions of the business layer
                 * */
                ResetApplicationLayer.resetUserData(appData);

                /*
                 * Referred from : https://developer.android.com/guide/topics/ui/dialogs
                 * */
                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteUser.this);
                builder.setCancelable(true);
                builder.setMessage("User Profile Deleted");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /*
                                 * Redirecting the user directly to the homepage
                                 * */
                                Intent intent = new Intent(getApplicationContext(),Home.class);
                                startActivity(intent);
                            }
                        });
                /*Creating the alert box*/
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
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
}
