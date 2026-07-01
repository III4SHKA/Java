package com.example.myapplication;

import androidx.annotation.NonNull;

public class Student {
    private final String Name;
    private String Email;
    private String Group;

    public Student(String name, String email, String group){
        this.Name = name;
        this.Email = email;
        this.Group = group;
    }

    public String getNam()
    {
        return Name;
    }
    public String getEmail()
    {
        return Email;
    }
    public String getGroup()
    {
        return Group;
    }


    @NonNull
    @Override
    public String toString()
    {
        return "Name: " + Name + "Group : "+ Group + " Email: " + Email;
    }
}
