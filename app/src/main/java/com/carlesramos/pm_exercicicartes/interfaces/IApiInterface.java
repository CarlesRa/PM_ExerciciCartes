package com.carlesramos.pm_exercicicartes.interfaces;

import com.carlesramos.pm_exercicicartes.clasesaux.ValidarLogin;
import com.carlesramos.pm_exercicicartes.model.Jugadores;
import com.carlesramos.pm_exercicicartes.clasesaux.NuevaPartida;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApiInterface {

    @GET("ApiJuegoDef/rest/inicio/nickName")
    Call<String> nickNameExists(@Query("nickName") String nickName);

    @GET("ApiJuegoDef/rest/inicio/nuevaPartida")
    Call<NuevaPartida> nuevaPartida(@Query("idSession")String idSession,
                                    @Query("idCliente")int idCliente);

    @POST("ApiJuegoDef/rest/inicio/validarLogin")
    @FormUrlEncoded
    Call<String> validarLogin(@Field("nickName") String nickName,
                                    @Field("pass") String pass);

    @POST("ApiJuegoDef/rest/inicio/jugador")
    @FormUrlEncoded
    Call<Jugadores> insertarJugador(@Field("jugador") String jugador);

    @GET("ApiJuegoDef/rest/inicio/saluda")
    @FormUrlEncoded
    Call<String> getSaludo();

}
