package com.example.meryl.tp2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FollowersFragment extends Fragment {

    public static final String USER = "USER";

    public static FollowersFragment newInstance(User user) {
        FollowersFragment myFragment = new FollowersFragment();

        Bundle args = new Bundle();
        args.putParcelable(USER, user);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        TextView textView = (TextView) view.findViewById(R.id.info_text);
        User user = getArguments().getParcelable(USER);
        String text;
        if (user != null) {
            text = getString(R.string.nb_followers) + user.getFollowers();
            textView.setText(text);
        }
        return view;
    }
}