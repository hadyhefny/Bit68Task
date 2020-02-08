package com.hefny.hady.bit68task.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hefny.hady.bit68task.R;
import com.hefny.hady.bit68task.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private List<Category> categoryList = new ArrayList<>();
    private OnItemListener onItemListener;
    private static final String TAG = "CategoriesAdapter";

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_categories, parent, false);
        return new CategoriesViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemListener onItemListener;
        TextView name;
        ImageView imageView;

        public CategoriesViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            this.onItemListener = onItemListener;
            name = itemView.findViewById(R.id.text_category_name);
            imageView = itemView.findViewById(R.id.image_category);
            itemView.setOnClickListener(this);
        }

        public void bind(Category category) {
            name.setText(category.getName());

            RequestOptions requestOptions = RequestOptions
                    .placeholderOf(R.drawable.white_background)
                    .error(R.drawable.white_background);

            Glide.with(imageView)
                    .setDefaultRequestOptions(requestOptions)
                    .load(category.getCategoryImg())
                    .centerCrop()
                    .into(imageView);
        }

        @Override
        public void onClick(View v) {
            if (onItemListener != null) {
                onItemListener.onItemClick(getAdapterPosition());
            } else {
                Log.d(TAG, "onItemListener is null, set the onItemListener to CategoriesAdapter");
            }
        }
    }
}