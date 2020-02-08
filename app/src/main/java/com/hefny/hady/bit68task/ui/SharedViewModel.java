package com.hefny.hady.bit68task.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hefny.hady.bit68task.api.CategoriesApi;
import com.hefny.hady.bit68task.models.Category;
import com.hefny.hady.bit68task.utils.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class SharedViewModel extends ViewModel {
    private static final String TAG = "SharedViewModel";
    private MutableLiveData<Resource<List<Category>>> categoriesMutableLiveData = new MutableLiveData<>();
    private CategoriesApi categoriesApi;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public SharedViewModel(CategoriesApi categoriesApi) {
        this.categoriesApi = categoriesApi;
        Log.d(TAG, "SharedViewModel: viewmodel is ready");
        observeCategories();
    }

    public LiveData<Resource<List<Category>>> getCategoriesLiveData() {
        return categoriesMutableLiveData;
    }

    private void observeCategories() {
        categoriesMutableLiveData.setValue(Resource.loading(null));
        categoriesApi.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Category>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Category> categories) {
                        Log.d(TAG, "onSuccess: " + categories.toString());
                        categoriesMutableLiveData.setValue(Resource.success(categories));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                        categoriesMutableLiveData.setValue(Resource.error(e.toString(), null));
                    }
                });
    }

    public void retry() {
        observeCategories();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}