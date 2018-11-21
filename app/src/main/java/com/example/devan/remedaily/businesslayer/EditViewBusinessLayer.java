/*Creator: Devanshu Srivastava*/
package com.example.devan.remedaily.businesslayer;

import android.support.annotation.NonNull;

import com.example.devan.remedaily.datalayer.AppDatabase;
import com.example.devan.remedaily.datalayer.User;

import java.lang.reflect.Array;
import java.util.List;

public class EditViewBusinessLayer {

    public static User ShowEditUserInfo(AppDatabase appData) {
        List<User> Users = ShowUsers(appData);
        return Users.get(0);
    }
    private static List<User> ShowActiveUser(AppDatabase db) {
        List<User>  users = db.userModel().loadAllUsers();/*Fetch the data from the DB*/
        return users;
    }
    private static List<User>  ShowUsers(@NonNull final AppDatabase db) {
        return ShowActiveUser(db);
    }
}
