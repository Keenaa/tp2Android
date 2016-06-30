package com.example.meryl.tp2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ViewsAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[];
    private User user;

    public ViewsAdapter(FragmentManager fm, Context context, User user) {
        super(fm);
        //this.context = context;
        tabTitles = context.getResources().getStringArray(R.array.association_tabs);
        this.user = user;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0: return ViewRepoFragment.newInstance(user);
            case 1: return FollowersFragment.newInstance(user);
            case 2: return FollowingFragment.newInstance(user);
            default : return ViewRepoFragment.newInstance(user);
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}