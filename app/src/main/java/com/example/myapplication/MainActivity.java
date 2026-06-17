package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private Button LoadUserbtn;
    private TextView Resulttxt;
    private EditText TitleInput;
    private EditText BodyInput;
    private Button SubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Resulttxt = findViewById(R.id.textView);
        LoadUserbtn = findViewById(R.id.button);

        LoadUserbtn.setOnClickListener(obj -> loadUser());

        TitleInput = findViewById(R.id.titleEditText);
        BodyInput = findViewById(R.id.bodyEditText);
        SubmitButton = findViewById(R.id.submitButton);

        SubmitButton.setOnClickListener(v -> submitPost());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    protected void loadUser()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        UserApi userApi = retrofit.create(UserApi.class);

        userApi.getUsers().enqueue(new Callback<List<UserDto>>() {
            int i = 0;
            @Override
            public void onResponse(Call<List<UserDto>> call, Response<List<UserDto>> response)
            {
                List<UserDto> users = response.body();

                StringBuilder result = new StringBuilder();

                for (UserDto user : users) {
                    result.append("Name: " + user.name + "email: " + user.email);
                    i+=1;
                }
                Resulttxt.setText(result.toString() + i);
            }
            @Override
            public void onFailure(Call<List<UserDto>> call, Throwable throwable)
            {
                Resulttxt.setText("LOAD ERROR");
            }
        });
    }

    protected void submitPost()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        UserApi userApi = retrofit.create(UserApi.class);

        PostDto post = new PostDto();
        post.title = TitleInput.getText().toString();
        post.body = BodyInput.getText().toString();

        userApi.createPost(post).enqueue(new Callback<PostDto>()
        {
            @Override
            public void onResponse(Call<PostDto> call, Response<PostDto> response)
            {
                PostDto createdPost = response.body();

                Resulttxt.setText(createdPost.id + createdPost.title + createdPost.body);
            }

            @Override
            public void onFailure(Call<PostDto> call, Throwable throwable)
            {
                Resulttxt.setText("POST ERROR");
            }
        });
    }
}