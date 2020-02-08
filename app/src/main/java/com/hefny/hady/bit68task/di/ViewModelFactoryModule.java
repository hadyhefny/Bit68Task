package com.hefny.hady.bit68task.di;

import androidx.lifecycle.ViewModelProvider;

import com.hefny.hady.bit68task.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);
}