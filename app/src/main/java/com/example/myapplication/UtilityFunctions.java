package com.example.myapplication;

import android.widget.EditText;

public class UtilityFunctions {
    public static boolean TextFieldIsEmpty(EditText obj)
    {
        String line = obj.toString();

        if(line.isEmpty())
        {
            obj.setError("Введіть текст");
            return true;
        }
        return false;
    }
}
