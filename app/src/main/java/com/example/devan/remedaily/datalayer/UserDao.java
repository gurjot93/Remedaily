package com.example.devan.remedaily.datalayer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.devan.remedaily.datalayer.User;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao

public interface UserDao {
    @Query("select * from user")
    List<User> loadAllUsers();

    @Query("select userPresent from user")
    boolean userPresent();

    @Insert(onConflict = IGNORE)
    Long insertUser(User user);

    @Query("DELETE FROM user")
    int deleteUser();
}
