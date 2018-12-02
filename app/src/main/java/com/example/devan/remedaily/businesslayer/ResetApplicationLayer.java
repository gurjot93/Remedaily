package com.example.devan.remedaily.businesslayer;

/*
 * Controller to delete the data from the database- user and medicines
 * Created by: Gurjot Singh (B00811724)
 * Created date: November 29, 2018
 * Version: 1
 * */
import com.example.devan.remedaily.datalayer.AppDatabase;

public class ResetApplicationLayer {

    /*
    * Function will delete the user
    * */
    public static void resetUserData(final AppDatabase db)
    {
        db.userModel().deleteUser();

    }
    /*
    * Function will delete the medicines details
    * */
    public static void resetMedData(final AppDatabase db)
    {

        db.medModel().deleteAll();

    }
}
