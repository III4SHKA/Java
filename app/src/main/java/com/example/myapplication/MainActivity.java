package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Student> students;
    private RecyclerView studentsRecycleView;
    private Button addButton;
    private StudentAdapter adapter;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        studentsRecycleView = findViewById(R.id.studentsRecyclerView);
        addButton = findViewById(R.id.addButton);
        studentsRecycleView.setLayoutManager(new LinearLayoutManager(this));
        database = AppDatabase.getInstance(this);
        students = new ArrayList<>();
        adapter = new StudentAdapter(this, students);
        studentsRecycleView.setAdapter(adapter);

        seedDefaultUsersIfNeeded();
        loadStudentsFromDatabase();

        addButton.setOnClickListener(click -> {
            Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (obj, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            obj.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStudentsFromDatabase();
    }

    private void seedDefaultUsersIfNeeded() {
        if (database.userDao().getUsersCount() > 0) {
            return;
        }

        database.userDao().insert(new UserEntity("Mark", "mark@gmail.com", "pv512"));
        database.userDao().insert(new UserEntity("Alice", "alice@gmail.com", "pv512"));
        database.userDao().insert(new UserEntity("Bob", "mbob@gmail.com", "pv512"));
        database.userDao().insert(new UserEntity("Qw", "qw@gmail.com", "pv512"));
    }

    private void loadStudentsFromDatabase() {
        List<UserEntity> users = database.userDao().getAllUsers();
        students.clear();
        for (UserEntity user : users) {
            students.add(new Student(user.name, user.email, user.groupName));
        }
        adapter.notifyDataSetChanged();
    }
}
