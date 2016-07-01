package com.example.meryl.tp2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfilFragment extends Fragment {
    public static final String USER = "USER";
    @BindView(R.id.pseudo)
    TextView pseudo;
    @BindView(R.id.user_image)
    ImageView userimage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, rootView);
        this.initView();
        return rootView;
    }

    public static ProfilFragment newInstance(User user) {
        ProfilFragment myFragment = new ProfilFragment();

        Bundle args = new Bundle();
        args.putParcelable(USER, user);
        myFragment.setArguments(args);

        return myFragment;
    }

    private void initView() {
        Bundle args = getArguments();
        User user = args.getParcelable(USER);
        if (user != null) {
            System.out.println(user.getName());
            pseudo.setText(user.getName());
            Glide.with(getContext()).load(user.getAvatarUrl()).into(userimage);
        }
    }
}