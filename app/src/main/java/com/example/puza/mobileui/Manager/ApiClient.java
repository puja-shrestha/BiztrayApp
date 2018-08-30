package com.example.puza.mobileui.Manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://www.biztray.com/";

    public static final String BASE_API_URL = BASE_URL+"api/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_API_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
