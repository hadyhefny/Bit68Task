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
import com.hefny.hady.bit68task.adapters.OnItemListener;
import com.hefny.hady.bit68task.adapters.ProductsAdapter;
import com.hefny.hady.bit68task.models.ProductsItem;
import com.hefny.hady.bit68task.utils.Resource;
import com.hefny.hady.bit68task.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProductsFragment extends DaggerFragment implements OnItemListener {
    // variables
    @Inject
    ProductsAdapter productsAdapter;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private SharedViewModel viewModel;
    private List<ProductsItem> productsList = new ArrayList<>();
    private static final String TAG = "ProductsFragment";

    // ui components
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview_products);
        viewModel = viewModelProviderFactory.create(SharedViewModel.class);
        initRecyclerView();
        observeProducts();

        if (!((MainActivity) getActivity()).checkInternet()) {
            ((MainActivity) getActivity()).showError();
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(productsAdapter);
        productsAdapter.setOnItemListener(this);
    }

    private void observeProducts() {
        viewModel.getCategoriesLiveData().observe(getViewLifecycleOwner(), listResource -> {
            if (listResource != null) {
                if (listResource.status == Resource.Status.SUCCESS) {
                    Log.d(TAG, "onChanged: SUCCESS: " + listResource.data.get(getPositionFromCategoriesFragment()).getProducts());
                    productsList.clear();
                    productsList.addAll(listResource.data.get(getPositionFromCategoriesFragment()).getProducts());
                    productsAdapter.setProductsItemList(productsList);
                    productsAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private Integer getPositionFromCategoriesFragment() {
        int position = -1;
        if (getArguments() != null) {
            position = ProductsFragmentArgs.fromBundle(getArguments()).getCategoryPosition();
        }
        return position;
    }

    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onItemClick: " + position);
        navToDetailsFragment(productsList.get(position).getName());
    }

    // utilizing android navigation component library with safe args to pass data between destinations
    private void navToDetailsFragment(String productName) {
        ProductsFragmentDirections.ActionProductsFragmentToDetailsFragment action =
                ProductsFragmentDirections.actionProductsFragmentToDetailsFragment(productName);
        Navigation.findNavController(recyclerView).navigate(action);
    }

}