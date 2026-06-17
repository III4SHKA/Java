package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(UserEntity obj);

    @Query("SELECT * FROM users")
    List<UserEntity> getAllUsers();
}
