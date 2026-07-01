package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    private TextView txtDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_students);

        txtDetails = findViewById(R.id.txtDetails);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String group = getIntent().getStringExtra("group");

        String details = "Name: " + name
                + "\nEmail: " + email
                + "\nGroup: " + group;
        txtDetails.setText(details);
    }
}
