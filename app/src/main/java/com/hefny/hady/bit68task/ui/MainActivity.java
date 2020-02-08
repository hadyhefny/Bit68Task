package com.hefny.hady.bit68task.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.hefny.hady.bit68task.R;
import com.hefny.hady.bit68task.utils.ConnectivityUtil;
import com.hefny.hady.bit68task.utils.Resource;
import com.hefny.hady.bit68task.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements DialogInterface.OnClickListener {

    // variables
    private static final String TAG = "MainActivity";
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private SharedViewModel viewModel;

    // ui components
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);
        viewModel = viewModelProviderFactory.create(SharedViewModel.class);
        observeLoadingState();
        Log.d(TAG, "onCreate: CALLED");
    }

    private void observeLoadingState() {
        viewModel.getCategoriesLiveData().observe(this, listResource -> {
            if (listResource != null) {
                showProgressBar(listResource.status);
                if (listResource.status == Resource.Status.ERROR) {
                    showError();
                }
            }

        });
    }

    private void showProgressBar(Resource.Status status) {
        if (status == Resource.Status.LOADING) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void showError() {
        new MaterialAlertDialogBuilder(MainActivity.this)
                .setTitle("No Internet Connection")
                .setMessage("Check your internet connection")
                .setPositiveButton("RETRY", this)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        viewModel.retry();
    }

    public boolean checkInternet() {
        return ConnectivityUtil.isConnectedToInternet(this);
    }
}