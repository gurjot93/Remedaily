package com.example.devan.remedaily.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao

public interface MedDao{
    @Query("select * from med")
    List<Med> loadAllMeds();

    @Query("select * from med where id = :id")
    Med loadMedById(int id);

    @Query("select * from med where medName = :medname")
    Med loadMedByName(String medname);

    @Insert(onConflict = IGNORE)
    void insertMeds(Med med);

    @Query("DELETE FROM med")
    void deleteAll();
}