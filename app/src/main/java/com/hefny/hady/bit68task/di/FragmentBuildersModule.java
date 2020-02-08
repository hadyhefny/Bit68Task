package com.hefny.hady.bit68task.di;

import com.hefny.hady.bit68task.ui.CategoriesFragment;
import com.hefny.hady.bit68task.ui.DetailsFragment;
import com.hefny.hady.bit68task.ui.ProductsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract CategoriesFragment contributeCategoriesFragment();

    @ContributesAndroidInjector
    abstract ProductsFragment contributeProductsFragment();

    @ContributesAndroidInjector
    abstract DetailsFragment contributeDetailsFragment();
}
