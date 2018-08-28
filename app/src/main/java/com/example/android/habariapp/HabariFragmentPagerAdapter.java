package com.example.android.habariapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class HabariFragmentPagerAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private Context mContext;

    public HabariFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PoliticsFragment();
        } else if (position == 1){
            return new SportsFragment();
        } else if (position == 2){
            return new BusinessFragment();
        } else {
            return new EnvironmentFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_politics);
        } else if (position == 1) {
            return mContext.getString(R.string.category_sports);
        } else if (position == 2) {
            return mContext.getString(R.string.category_business);
        } else {
            return mContext.getString(R.string.category_environment);
        }
    }
}
