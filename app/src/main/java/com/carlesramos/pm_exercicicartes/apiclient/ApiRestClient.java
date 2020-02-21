package com.carlesramos.pm_exercicicartes.apiclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRestClient {

    private static ApiRestClient instance;
    private Retrofit retrofit;


    private ApiRestClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(APIUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiRestClient getInstance(){
        if (instance == null){
            synchronized (ApiRestClient.class){
                if (instance == null){
                    instance = new ApiRestClient();
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}
