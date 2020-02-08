package com.hefny.hady.bit68task.api;


import com.hefny.hady.bit68task.models.Category;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CategoriesApi {

    @GET("categories")
    Single<List<Category>> getCategories();
}
