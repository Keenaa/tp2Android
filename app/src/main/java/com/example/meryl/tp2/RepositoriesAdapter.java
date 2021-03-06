package com.example.meryl.tp2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

public class RepositoriesAdapter  extends RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder> {
    List<Repositories> repositories;
    Context context; //Correction éviter les static

    public RepositoriesAdapter(List<Repositories> repositories, Context context) {
        this.repositories = repositories;
        this.context = context;
    }

    @Override
    public RepositoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_repositories, parent, false);
        return new RepositoriesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RepositoriesViewHolder holder, final int position) {
        holder.bind(repositories.get(position)); //Correction added bind method
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public static class RepositoriesViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView id;

        public RepositoriesViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.repositories_name);
            this.id = (TextView) itemView.findViewById(R.id.repositories_id);
        }

        public void bind(Repositories repository) {
            name.setText(repository.getName());
            id.setText(repository.getId());
        }
    }

}