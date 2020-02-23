package com.carlesramos.pm_exercicicartes.interfaces;

import com.carlesramos.pm_exercicicartes.clasesaux.JugadaCpu;
import com.carlesramos.pm_exercicicartes.model.Cartas;
import com.carlesramos.pm_exercicicartes.model.Jugadores;
import com.carlesramos.pm_exercicicartes.clasesaux.NuevaPartida;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApiInterface {

    @GET("ApiJuegoDef/rest/inicio/nickName")
    Call<String> nickNameExists(@Query("nickName") String nickName);

    @GET("ApiJuegoDef/rest/inicio/juegaCPU")
    Call<JugadaCpu> juegaCPU();

    @GET("ApiJuegoDef/rest/inicio/jugadores")
    Call<ArrayList<Jugadores>> getJugadores();

    @GET("ApiJuegoDef/rest/inicio/cartas")
    Call<ArrayList<Cartas>> getCartas();

    @POST("ApiJuegoDef/rest/inicio/comprobarJugada")
    @FormUrlEncoded
    Call<Integer> comprobarJugada(@Field("idPartida")int idPartida,
                                  @Field("cartaJugadorA")int cartaJugadorA,
                                  @Field("caracteristica")String caracteristica,
                                  @Field("cartaJugadorB")int idCartaB);

    @POST("ApiJuegoDef/rest/inicio/nuevaPartida")
    @FormUrlEncoded
    Call<NuevaPartida> nuevaPartida(@Field("idSession") String idSession,
                                    @Field("idCliente") int idCliente);

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
