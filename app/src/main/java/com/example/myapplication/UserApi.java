package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface UserApi {
    @GET("users/1")
    Call<UserDto> getUser();

    @GET("users")
    Call<List<UserDto>> getUsers();

    @POST("posts")
    Call<PostDto> createPost(@Body PostDto post);
}

