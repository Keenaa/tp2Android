package com.example.meryl.tp2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RepositoriesFragment extends Fragment {
    GitHubService service;
    @BindView(R.id.refreshRepositories)
    SwipeRefreshLayout refreshRepositories;
    @BindView(R.id.list_repositories)
    RecyclerView listRepo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repositories, container, false);

        ButterKnife.bind(this, rootView);
        this.initView();

        return rootView;
    }

    public static RepositoriesFragment newInstance(User user) {
        RepositoriesFragment myFragment = new RepositoriesFragment();

        Bundle args = new Bundle();
        args.putParcelable("USER", user);
        myFragment.setArguments(args);

        return myFragment;
    }

    private void initView() {
        service = GithubWebService.get();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listRepo.setLayoutManager(layoutManager);
        refreshRepositories.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRepositories();
            }
        });
        getRepositories();

    }

    private void getRepositories (){
        Bundle args = getArguments();
        User user = args.getParcelable("USER");
        if (user != null) {
            service.getUsersRepositories(user.getLogin()).enqueue(new Callback<ArrayList<Repositories>>() {
                @Override
                public void onResponse(Call<ArrayList<Repositories>> call, Response<ArrayList<Repositories>> response) {
                    if (response.code() == 200) {
                        ArrayList<Repositories> repos = response.body();
                        listRepo.setAdapter(new RepositoriesAdapter(repos, getContext()));
                    }else
                        System.out.println("/!\\ Error /!\\ : " + response.code());
                    if (refreshRepositories.isRefreshing())
                        refreshRepositories.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<ArrayList<Repositories>> call, Throwable t) {
                    Log.e("retrofit", "failure" + t);
                    if (refreshRepositories.isRefreshing())
                        refreshRepositories.setRefreshing(false);
                }
            });
        }
    }

}
