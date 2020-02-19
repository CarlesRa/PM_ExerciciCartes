package com.carlesramos.pm_exercicicartes.apiclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRestClient {
    private static final String BASE_URL = "http://localhost:8080/ApiJuegoDef/rest/inicio";
    private static ApiRestClient instance;
    private Retrofit retrofit;


    private ApiRestClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static ApiRestClient getInstance(){
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
