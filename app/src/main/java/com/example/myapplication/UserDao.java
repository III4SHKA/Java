package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity obj);

    @Query("SELECT * FROM users ORDER BY id ASC")
    List<UserEntity> getAllUsers();

    @Query("SELECT COUNT(*) FROM users")
    int getUsersCount();
}
