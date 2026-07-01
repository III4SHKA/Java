package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
public interface UserApi {
    @GET("users/1")
    Call<UserDto> getUser();
}
