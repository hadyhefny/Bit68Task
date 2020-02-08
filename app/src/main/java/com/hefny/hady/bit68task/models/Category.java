package com.hefny.hady.bit68task.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    @SerializedName("category_img")
    private String categoryImg;

    @SerializedName("products")
    private List<ProductsItem> products;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }

    public String getCategoryImg() {
        return categoryImg;
    }

    public void setProducts(List<ProductsItem> products) {
        this.products = products;
    }

    public List<ProductsItem> getProducts() {
        return products;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null ||
                getClass() != obj ||
                getClass() != obj.getClass()) {
            return false;
        }
        Category category = (Category) obj;
        return category.name.equals(name) && category.id.equals(id);
    }

    @Override
    public String toString() {
        return
                "Category{" +
                        "name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",category_img = '" + categoryImg + '\'' +
                        ",products = '" + products + '\'' +
                        "}";
    }
}