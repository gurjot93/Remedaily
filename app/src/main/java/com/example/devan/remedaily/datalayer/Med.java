package com.example.devan.remedaily.datalayer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Med {

    @PrimaryKey
    @NonNull
    public String id;
    public String medName;
    public String expiryDate;
    public String createDate;
}
