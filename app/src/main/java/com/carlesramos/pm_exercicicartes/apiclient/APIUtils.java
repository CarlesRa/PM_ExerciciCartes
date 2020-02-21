package com.carlesramos.pm_exercicicartes.apiclient;

import com.carlesramos.pm_exercicicartes.interfaces.IApiInterface;

public class APIUtils {

    private APIUtils(){

    }

    public static final String BASE_URL = "http://192.168.105.20:8080";
    public static IApiInterface getIApiInterface(){
        return ApiRestClient.getInstance().getRetrofit().create(IApiInterface.class);
    }


}
