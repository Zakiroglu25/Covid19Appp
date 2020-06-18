package com.aris.covid19app.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String API_BASE_URL = "https://api.covid19api.com";
    private static Retrofit retrofit;
    static Gson gson = new GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create();

    static OkHttpClient client = new OkHttpClient();

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
