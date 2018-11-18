package com.example.devan.remedaily.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
@Dao

public interface UserDao {
    @Query("select * from user")
    List<User> loadAllUsers();

    @Query("select * from user where firstName = :firstName")
    User loadUsersbyName(String firstName);

    @Insert(onConflict = IGNORE)
    void insertUser(User user);

    @Query("DELETE FROM user")
    void deleteUser();
}
