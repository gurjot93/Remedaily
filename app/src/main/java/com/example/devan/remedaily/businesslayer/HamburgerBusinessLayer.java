package com.example.devan.remedaily.businesslayer;

import android.support.annotation.NonNull;

import com.example.devan.remedaily.datalayer.AppDatabase;
import com.example.devan.remedaily.datalayer.User;
import java.util.List;

public class HamburgerBusinessLayer {

   public static StringBuilder ShowUserInfo(AppDatabase appData) {
        StringBuilder sb = new StringBuilder();
        List<User> Users = ShowUsers(appData);
        for (User User : Users) {
            sb.append(String.format(
                    "%s %s\n", User.firstName, User.lastName));
        }
        if(sb.length()==0){
            sb.append("User");
        }
        return sb;
    }
    private static List<User>  ShowActiveUser(AppDatabase db) {
        List<User>  med1 = db.userModel().loadAllUsers();/*Fetch the data from the DB*/
        return med1;
    }
    private static List<User>  ShowUsers(@NonNull final AppDatabase db) {
        return ShowActiveUser(db);
    }
}
