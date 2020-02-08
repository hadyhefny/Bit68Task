package com.hefny.hady.bit68task.models;

import com.google.gson.annotations.SerializedName;

public class ProductsItem {

    @SerializedName("price")
    private String price;

    @SerializedName("name")
    private String name;

    @SerializedName("weight")
    private String weight;

    @SerializedName("id")
    private String id;

    @SerializedName("product_img")
    private String productImg;

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductImg() {
        return productImg;
    }

    @Override
    public String toString() {
        return
                "ProductsItem{" +
                        "price = '" + price + '\'' +
                        ",name = '" + name + '\'' +
                        ",weight = '" + weight + '\'' +
                        ",id = '" + id + '\'' +
                        ",product_img = '" + productImg + '\'' +
                        "}";
    }
}