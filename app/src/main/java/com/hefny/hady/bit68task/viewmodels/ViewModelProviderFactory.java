package com.hefny.hady.bit68task.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Generic factory class to create ViewModels using Dagger 2 injections, working around the fact
 * that we cannot inject view models directly. Based on Android Architecture Components example:
 * https://github.com/googlesamples/android-architecture-components/blob/b1a194c1ae267258cd002e2e1c102df7180be473/GithubBrowserSample/app/src/main/java/com/android/example/github/viewmodel/GithubViewModelFactory.java
 */
public class ViewModelProviderFactory implements ViewModelProvider.Factory {

    private static final String TAG = "ViewModelProviderFactor";

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelProviderFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Provider<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) { // if the viewmodel has not been created

            // loop through the allowable keys (aka allowed classes with the @ViewModelKey)
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {

                // if it's allowed, set the Provider<ViewModel>
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }

        // if this is not one of the allowed keys, throw exception
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }

        // return the Provider
        try {
            return (T) creator.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
