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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hefny.hady.bit68task.R;
import com.hefny.hady.bit68task.models.ProductsItem;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private List<ProductsItem> productsItemList = new ArrayList<>();
    private OnItemListener onItemListener;
    private static final String TAG = "ProductsAdapter";

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_products, parent, false);
        return new ProductsViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        ProductsItem productsItem = productsItemList.get(position);
        holder.bind(productsItem);
    }

    @Override
    public int getItemCount() {
        return productsItemList.size();
    }

    public void setProductsItemList(List<ProductsItem> productsItemList) {
        this.productsItemList = productsItemList;
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemListener onItemListener;
        TextView name;
        TextView weight;
        TextView price;
        ImageView imageView;
        FloatingActionButton addFab;
        FloatingActionButton unCheckFab;

        public ProductsViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            name = itemView.findViewById(R.id.text_product_name);
            weight = itemView.findViewById(R.id.text_product_weight);
            price = itemView.findViewById(R.id.text_product_price);
            imageView = itemView.findViewById(R.id.image_product);
            addFab = itemView.findViewById(R.id.add_product_fab);
            unCheckFab = itemView.findViewById(R.id.unCheck_product_fab);
            itemView.setOnClickListener(this);
            this.onItemListener = onItemListener;

            addFab.setOnClickListener(v -> {
                addFab.setVisibility(View.GONE);
                unCheckFab.setVisibility(View.VISIBLE);
            });
            unCheckFab.setOnClickListener(v -> {
                addFab.setVisibility(View.VISIBLE);
                unCheckFab.setVisibility(View.GONE);
            });
        }

        public void bind(ProductsItem productsItem) {
            name.setText(productsItem.getName());
            weight.setText(productsItem.getWeight());
            price.setText(productsItem.getPrice());
            RequestOptions requestOptions = RequestOptions
                    .placeholderOf(R.drawable.white_background)
                    .error(R.drawable.white_background);

            Glide.with(imageView)
                    .setDefaultRequestOptions(requestOptions)
                    .load(productsItem.getProductImg())
                    .centerCrop()
                    .into(imageView);
        }

        @Override
        public void onClick(View v) {
            if (onItemListener != null) {
                onItemListener.onItemClick(getAdapterPosition());
            } else {
                Log.d(TAG, "onItemListener is null, set the onItemListener to ProductsAdapter");
            }
        }
    }
}