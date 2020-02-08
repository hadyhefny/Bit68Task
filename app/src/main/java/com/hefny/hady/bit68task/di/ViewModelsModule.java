package com.hefny.hady.bit68task.di;

import androidx.lifecycle.ViewModel;

import com.hefny.hady.bit68task.ui.SharedViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel.class)
    public abstract ViewModel bindCategoryViewModel(SharedViewModel viewModel);

}