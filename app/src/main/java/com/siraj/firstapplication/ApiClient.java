package com.siraj.firstapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.siraj.firstapplication.Activities.MainActivity.BASE_URL;

/**
 * Created by siraj on 24-Jan-17.
 */

public class ApiClient {
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
