package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPersonActivity extends AppCompatActivity {
    private EditText InputName;
    private EditText InputEmail;
    private EditText InputGroup;
    private Button AddPerson;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_person);

        InputName = findViewById(R.id.InputName);
        InputEmail = findViewById(R.id.InputEmail);
        InputGroup = findViewById(R.id.InputGroup);
        AddPerson = findViewById(R.id.AddPerson);
        database = AppDatabase.getInstance(this);
        AddPerson.setOnClickListener(click -> saveUser());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addPersonMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void saveUser() {
        String name = InputName.getText().toString().trim();
        String email = InputEmail.getText().toString().trim();
        String group = InputGroup.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || group.isEmpty()) {
            Toast.makeText(this, "?", Toast.LENGTH_SHORT).show();
            return;
        }

        database.userDao().insert(new UserEntity(name, email, group));
        Toast.makeText(this, "Дані збережено", Toast.LENGTH_SHORT).show();
        finish();
    }
}
