package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String email;
    public String groupName;

    public UserEntity(String name, String email, String groupName) {
        this.name = name;
        this.email = email;
        this.groupName = groupName;
    }
}
