package com.hefny.hady.bit68task.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hefny.hady.bit68task.R;
import com.hefny.hady.bit68task.adapters.CategoriesAdapter;
import com.hefny.hady.bit68task.adapters.OnItemListener;
import com.hefny.hady.bit68task.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class CategoriesFragment extends DaggerFragment implements OnItemListener {

    // variables
    @Inject
    CategoriesAdapter categoriesAdapter;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private SharedViewModel viewModel;
    private static final String TAG = "CategoriesFragment";

    // ui components
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview_categories);
        viewModel = viewModelProviderFactory.create(SharedViewModel.class);
        Log.d(TAG, "onViewCreated: categoryViewModel hashCode: " + viewModel.hashCode());
        initRecyclerView();
        observeCategories();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(categoriesAdapter);
        categoriesAdapter.setOnItemListener(this);
    }

    private void observeCategories() {
        viewModel.getCategoriesLiveData().observe(getViewLifecycleOwner(), listResource -> {
            if (listResource != null) {
                switch (listResource.status) {
                    case LOADING: {
                        Log.d(TAG, "onChanged: LOADING");
                        break;
                    }
                    case ERROR: {
                        Log.d(TAG, "onChanged: ERROR: " + listResource.message);
                        break;
                    }
                    case SUCCESS: {
                        Log.d(TAG, "onChanged: SUCCESS: " + listResource.data.toString());
                        categoriesAdapter.setCategoryList(listResource.data);
                        categoriesAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onItemClick: position: " + position);
        navToProductsFragment(position);
    }

    // utilizing android navigation component library with safe args to pass data between destinations
    private void navToProductsFragment(int categoryPosition) {
        CategoriesFragmentDirections.ActionCategoriesFragmentToProductsFragment action =
                CategoriesFragmentDirections.actionCategoriesFragmentToProductsFragment(categoryPosition);
        Navigation.findNavController(recyclerView).navigate(action);
    }
}