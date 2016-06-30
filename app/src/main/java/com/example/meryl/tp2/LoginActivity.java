package com.example.meryl.tp2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity  extends AppCompatActivity {
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.send)
    AppCompatButton btnValid;

    SharedPreferences sharedPref;

    GitHubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        if(sharedPref != null) {
            System.out.println("HERE !!!!!");
        }
            sharedPref = getApplicationContext().getSharedPreferences("github_app", Context.MODE_PRIVATE);
            username.setText(sharedPref.getString(getString(R.string.input_username), ""));

        service = GithubWebService.get();
    }

    protected void loadUser(String name){
        service.getUser(name).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    User user = response.body();
                    Session.getInstance().setCurrentUser(user);
                    System.out.println(user.getName());
                    System.out.println(user.getLogin());
                    System.out.println(user.getAvatarUrl());
                    goToNextActivity(user);
                }else
                    System.out.println("Erreur:" + response.code());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("retrofit", "failure" + t);
            }
        });
    }

    @OnClick(R.id.send)
    public void load(View view) {
        String name = username.getText().toString();
        saveUser(name);
        loadUser(name);
    }

    public void saveUser(String name){
        sharedPref.edit().putString(getString(R.string.input_username), name).apply();
    }

    protected void goToNextActivity(User user){
        System.out.println("Passage2");
        Intent intent = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
        Bundle bundleUser = new Bundle();
        bundleUser.putParcelable(getString(R.string.user), user);
        intent.putExtra(getString(R.string.user), bundleUser);
        startActivity(intent);
    }
}
