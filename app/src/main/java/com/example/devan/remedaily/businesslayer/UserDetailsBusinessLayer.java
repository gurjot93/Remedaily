/*Creator Devanshu Srivastava, dated: 11/5/2018
* CONTROLLER
* */

package com.example.devan.remedaily.businesslayer;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.example.devan.remedaily.datalayer.AppDatabase;
import com.example.devan.remedaily.datalayer.User;


public class UserDetailsBusinessLayer {

    //region is user available in db
    public  static boolean IsUserPresent(AppDatabase db){
        boolean flag =db.userModel().userPresent();
        return flag;
    }
    //endregion

    //region Insert into DB.
    public static void InsertRecordsAsync(@NonNull final AppDatabase db, String FirstName, String LastName, String Age,String EmailID) throws Exception {

        InsertRecords task = new InsertRecords(db, FirstName, LastName, Age,EmailID);
        task.execute();
    }


    public static boolean InsertSyncUser(@NonNull final AppDatabase db, final String FirstName,
                                         final String LastName, final String Age,final String EmailID) throws Exception {
        if(!FirstName.isEmpty()&&!LastName.isEmpty()&&!Age.isEmpty()&&!EmailID.isEmpty()){
            return InsertWithTestDataUser(db, FirstName, LastName, Age,EmailID);
        }
        else
            return false;
    }

    private static boolean InsertWithTestDataUser(AppDatabase db, final String FirstName,
                                                  final String LastName, final String Age,final String EmailID) throws Exception {
        db.userModel().deleteUser();
        return AddUser(db, FirstName, LastName, Age,EmailID);
    }

    private static boolean AddUser(final AppDatabase db, final String FirstName, final String LastName, final String Age,final String EmailID) throws Exception {
        User user = new User();
        user.firstName = FirstName;
        user.lastName = LastName;
        user.age = Age;
        user.emailID= EmailID;
        user.userPresent = true;
        if (1 == db.userModel().insertUser(user)) {
            return true;
        } else {
            return false;
        }
    }

    //endregion

    //region Async DB calls.

    private static class InsertRecords extends AsyncTask<String, String, String> {

        private final AppDatabase mDb;
        private final String FirstName, LastName, Age,EmailID;

        InsertRecords(AppDatabase db, String FirstName, String LastName, String Age,String EmailID) {
            mDb = db;
            this.FirstName = FirstName;
            this.LastName = LastName;
            this.Age = Age;
            this.EmailID= EmailID;
        }

        @Override
        protected String doInBackground(final String... params) {
            try {
                InsertSyncUser(mDb,FirstName,LastName,Age,EmailID);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    //endregion

}

