package com.hefny.hady.bit68task.ui.categories_view_pager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class CategoriesPagerAdapter extends FragmentStatePagerAdapter {

    private static final int FRAGMENT_SIZE = 2;

    public CategoriesPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return CategoriesSlidingImagesFragment.createSlidingFragment(position);
    }

    @Override
    public int getCount() {
        return FRAGMENT_SIZE;
    }
}