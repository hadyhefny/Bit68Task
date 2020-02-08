package com.hefny.hady.bit68task.di;

import com.hefny.hady.bit68task.adapters.CategoriesAdapter;
import com.hefny.hady.bit68task.adapters.ProductsAdapter;
import com.hefny.hady.bit68task.api.CategoriesApi;
import com.hefny.hady.bit68task.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

//    // caching the result of the retrofit request to handle no internet connection scenarios
//    @Singleton
//    @Provides
//    static OkHttpClient provideOkHttpClient(Application application) {
//        long cacheSize = 5 * 1024 * 1024;
//        Cache cache = new Cache(application.getCacheDir(), cacheSize);
//        OkHttpClient.Builder okHttp = new OkHttpClient.Builder()
//                .cache(cache)
//                .addInterceptor(chain -> {
//                    Request request = chain.request();
//                    // Request customization: add request headers
//                    if (ConnectivityUtil.isConnectedToInternet(application)) {
//                        request = request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build();
//                    } else {
//                        request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
//                    }
//                    return chain.proceed(request);
//                });
//        return okHttp.build();
//    }

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
//                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static CategoriesApi provideCategoriesAPi(Retrofit retrofit) {
        return retrofit.create(CategoriesApi.class);
    }

    @Provides
    static CategoriesAdapter provideCategoriesAdapter() {
        return new CategoriesAdapter();
    }

    @Provides
    static ProductsAdapter provideProductsAdapter() {
        return new ProductsAdapter();
    }
}