package com.hefny.hady.bit68task.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;

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
    private boolean retry = false;

    // ui components
    private ProgressBar progressBar;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);
        viewModel = viewModelProviderFactory.create(SharedViewModel.class);
        observeLoadingState();
        Log.d(TAG, "onCreate: CALLED");
        Log.d(TAG, "onCreate: savedInstance: " + savedInstanceState);
        if (savedInstanceState == null) {
            if (!checkInternet()) {
                showError();
            }
        }
    }

    private void observeLoadingState() {
        Log.d(TAG, "observeLoadingState: ");
        viewModel.getCategoriesLiveData().observe(this, listResource -> {
            if (listResource != null) {
                Log.d(TAG, "observeLoadingState: listResourceStatus: " + listResource.status);
                showProgressBar(listResource.status);
                if (listResource.status == Resource.Status.ERROR) {
                    Log.d(TAG, "observeLoadingState: observe error");
                    if (retry) {
                        showError();
                        retry = false;
                    }
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
        Log.d(TAG, "showError: ");
        dialog = new MaterialAlertDialogBuilder(MainActivity.this)
                .setTitle("No Internet Connection")
                .setMessage("Check your internet connection")
                .setPositiveButton("RETRY", this)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        retry = true;
        viewModel.retry();
    }

    public boolean checkInternet() {
        Log.d(TAG, "checkInternet: ");
        return ConnectivityUtil.isConnectedToInternet(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}