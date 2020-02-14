package com.hefny.hady.bit68task.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hefny.hady.bit68task.R;
import com.hefny.hady.bit68task.adapters.CategoriesAdapter;
import com.hefny.hady.bit68task.adapters.OnItemListener;
import com.hefny.hady.bit68task.ui.categories_view_pager.CategoriesPagerAdapter;
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
    private TabLayout tabLayout;
    private ViewPager viewPager;

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
        tabLayout = view.findViewById(R.id.tabs_categories);
        viewPager = view.findViewById(R.id.view_pager_categories);
        viewModel = viewModelProviderFactory.create(SharedViewModel.class);
        Log.d(TAG, "onViewCreated: categoryViewModel hashCode: " + viewModel.hashCode());
        initRecyclerView();
        observeCategories();
        initViewPager();
    }

    private void initViewPager() {
        CategoriesPagerAdapter adapter = new CategoriesPagerAdapter(getChildFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
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