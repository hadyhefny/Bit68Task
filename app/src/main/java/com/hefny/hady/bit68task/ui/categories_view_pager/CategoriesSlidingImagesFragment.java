package com.hefny.hady.bit68task.ui.categories_view_pager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hefny.hady.bit68task.R;

public class CategoriesSlidingImagesFragment extends Fragment {

    private static final String ARG_COUNT = "param1";
    private int counter;
    private static final String TAG = "CategoriesSlidingImages";

    private int[] imagesArray = {
            R.drawable.delivery,
            R.drawable.food,
    };

    public static CategoriesSlidingImagesFragment createSlidingFragment(int counter) {
        CategoriesSlidingImagesFragment fragment = new CategoriesSlidingImagesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            counter = getArguments().getInt(ARG_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories_sliding_images, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.image_view_categories_sliding_fragment);
        imageView.setImageResource(imagesArray[counter]);
        Log.d(TAG, "onViewCreated: ");
    }
}