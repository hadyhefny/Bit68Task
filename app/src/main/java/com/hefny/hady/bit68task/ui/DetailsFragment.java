package com.hefny.hady.bit68task.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hefny.hady.bit68task.R;

import dagger.android.support.DaggerFragment;


public class DetailsFragment extends DaggerFragment {

    // variables
    private static final String TAG = "DetailsFragment";

    // ui components
    private TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.text_product_details_name);
        name.setText(getNameFromProductsFragment());
    }

    private String getNameFromProductsFragment() {
        String name = "";
        if (getArguments() != null) {
            name = DetailsFragmentArgs.fromBundle(getArguments()).getProductName();
        }
        return name;
    }
}