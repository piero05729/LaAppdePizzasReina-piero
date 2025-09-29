package com.example.pizzadelivery.core.di;

import com.example.pizzadelivery.core.constants.ApiConfig;
import com.example.pizzadelivery.data.datasource.remote.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitProvider {
    private static ApiService apiService;

    private RetrofitProvider() {}

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (RetrofitProvider.class) {
                if (apiService == null) {
                    OkHttpClient client = new OkHttpClient.Builder()
                            .build();

                    Gson gson = new GsonBuilder().create();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ApiConfig.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(client)
                            .build();

                    apiService = retrofit.create(ApiService.class);
                }
            }
        }
        return apiService;
    }
}
