package com.example.devan.remedaily.datalayer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.devan.remedaily.datalayer.Med;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao

public interface MedDao{
    @Query("select * from med")
    List<com.example.devan.remedaily.datalayer.Med> loadAllMeds();

    @Query("select * from med where id = :id")
    com.example.devan.remedaily.datalayer.Med loadMedById(int id);

    @Query("select * from med where medName = :medname")
    com.example.devan.remedaily.datalayer.Med loadMedByName(String medname);

    @Insert(onConflict = IGNORE)
    void insertMeds(com.example.devan.remedaily.datalayer.Med med);

    @Query("DELETE FROM med")
    void deleteAll();

    @Query("select * from med where startDate = :StartDate")
    List<com.example.devan.remedaily.datalayer.Med> loadMedByDate(String StartDate);
}