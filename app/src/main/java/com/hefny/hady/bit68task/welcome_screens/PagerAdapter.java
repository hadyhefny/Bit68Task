package com.hefny.hady.bit68task.welcome_screens;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private static final int FRAGMENT_SIZE = 2;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        return SlidingFragment.createSlidingFragment(position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return FRAGMENT_SIZE;
//    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return SlidingFragment.createSlidingFragment(position);
    }

    @Override
    public int getCount() {
        return FRAGMENT_SIZE;
    }
}