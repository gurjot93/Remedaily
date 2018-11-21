/*Creator Devanshu Srivastava, dated: 11/5/2018*/

package com.example.devan.remedaily.businesslayer;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.example.devan.remedaily.datalayer.AppDatabase;
import com.example.devan.remedaily.datalayer.User;


public class UserDetailsBusinessLayer {

/*    public StringBuilder ShowUserInfo() {
        StringBuilder sb = new StringBuilder();
        List<User> youngUsers = DatabaseInitializer.showUsers(appData);

        for (User youngUser : youngUsers) {
            sb.append(String.format(
                    "%s, %s , %s\n", youngUser.firstName, youngUser.lastName, youngUser.age));
        }
        return sb;
    }*/

    //region Insert into DB.


    public static void InsertRecordsAsync(@NonNull final AppDatabase db, String FirstName, String LastName, String Age) throws Exception {

        InsertRecords task = new InsertRecords(db, FirstName, LastName, Age);
        task.execute();
    }


    public static boolean InsertSyncUser(@NonNull final AppDatabase db, final String FirstName, final String LastName, final String Age) throws Exception {
        if(!FirstName.isEmpty()&&!LastName.isEmpty()&&!Age.isEmpty()){
            return InsertWithTestDataUser(db, FirstName, LastName, Age);
        }
        else
            return false;
    }

    private static boolean InsertWithTestDataUser(AppDatabase db, final String FirstName,
                                                  final String LastName, final String Age) throws Exception {
        db.userModel().deleteUser();
        return AddUser(db, FirstName, LastName, Age);
    }

    private static boolean AddUser(final AppDatabase db, final String FirstName, final String LastName, final String Age) throws Exception {
        User user = new User();
        user.firstName = FirstName;
        user.lastName = LastName;
        user.age = Age;
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
        private final String FirstName, LastName, Age;

        InsertRecords(AppDatabase db, String FirstName, String LastName, String Age) {
            mDb = db;
            this.FirstName = FirstName;
            this.LastName = LastName;
            this.Age = Age;
        }

        @Override
        protected String doInBackground(final String... params) {
            try {
                InsertSyncUser(mDb,FirstName,LastName,Age);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    //endregion

}

