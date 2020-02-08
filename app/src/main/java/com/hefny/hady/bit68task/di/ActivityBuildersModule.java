package com.hefny.hady.bit68task.di;

import com.hefny.hady.bit68task.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {FragmentBuildersModule.class, ViewModelsModule.class}
    )
    abstract MainActivity contributeActivity();
}
