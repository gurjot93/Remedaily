package com.example.devan.remedaily.datalayer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    public String firstName;
    public String lastName;
    public String age;
    public boolean userPresent;
}
