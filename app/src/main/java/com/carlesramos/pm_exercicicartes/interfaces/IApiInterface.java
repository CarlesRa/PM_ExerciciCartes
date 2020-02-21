package com.carlesramos.pm_exercicicartes.interfaces;

import com.carlesramos.pm_exercicicartes.model.Jugadores;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApiInterface {

    @GET("ApiJuegoDef/rest/inicio/nickNameExists")
    Call<String> nickNameExists(@Query("nickName") String nickName);

    @POST("ApiJuegoDef/rest/inicio/validarLogin")
    @FormUrlEncoded
    Call<String> validarLogin(@Field("nickName") String nickName,
                                   @Field("pass") String pass);

    @POST("ApiJuegoDef/rest/inicio/insertarJugador")
    @FormUrlEncoded
    Call<Jugadores> insertarJugador(@Field("jugador") String jugador);

    @GET("ApiJuegoDef/rest/inicio/saluda")
    @FormUrlEncoded
    Call<String> getSaludo();

}
