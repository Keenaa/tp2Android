package com.example.meryl.tp2;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GitHubService {

    String END_POINT = "https://api.github.com";

    @GET("/users/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("/users/{username}/repos")
    Call<ArrayList<Repositories>> getUsersRepositories(@Path("username") String username);


}

