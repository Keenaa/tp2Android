package com.example.meryl.tp2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewsFragment extends Fragment {
    public static final String USER = "USER";
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_views, container, false);

        this.initView();

        return this.rootView;
    }

    public static ViewsFragment newInstance(User user) {
        ViewsFragment myFragment = new ViewsFragment();

        Bundle args = new Bundle();
        args.putParcelable(USER, user);
        myFragment.setArguments(args);

        return myFragment;
    }

    private void initView() {
        ViewPager views = (ViewPager) rootView.findViewById(R.id.views);
        if (views != null) {
            User user = getArguments().getParcelable(USER);
            views.setAdapter(new ViewsAdapter(getChildFragmentManager(),
                    getActivity(), user));
        }
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        if (tabLayout != null)
            tabLayout.setupWithViewPager(views);
    }
}
