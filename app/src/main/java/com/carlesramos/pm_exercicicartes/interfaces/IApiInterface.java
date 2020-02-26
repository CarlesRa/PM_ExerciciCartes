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

/**
 * @author Juan Carlos Ramos.
 * Interfaz para el framework Retrofit
 */
public interface IApiInterface {

    /**
     * LLama al metodo de comprobar si el nickName existe.
     * @param nickName
     * @return si existe o no.
     */
    @GET("ApiJuegoDef/rest/inicio/nickName")
    Call<String> nickNameExists(@Query("nickName") String nickName);

    /**
     * Devuelve la jugada de la CPU
     * @return Un objeto de la clase JugadaCpu
     */
    @GET("ApiJuegoDef/rest/inicio/juegaCPU")
    Call<JugadaCpu> juegaCPU();

    /**
     * Devulve lista de todos los jugadores Ordenados por número de victorias.
     * @return
     */
    @GET("ApiJuegoDef/rest/inicio/jugadores")
    Call<ArrayList<Jugadores>> getJugadores();

    /**
     * Devuelve lista de todas las cartas ordenadas según rondas ganadas.
     * @return
     */
    @GET("ApiJuegoDef/rest/inicio/cartas")
    Call<ArrayList<Cartas>> getCartas();

    /**
     * Conecta con el método de comprobarJugada
     * @param idPartida
     * @param cartaJugadorA
     * @param caracteristica
     * @param idCartaB
     * @return El resultado de la ronda
     */
    @POST("ApiJuegoDef/rest/inicio/comprobarJugada")
    @FormUrlEncoded
    Call<Integer> comprobarJugada(@Field("idPartida")int idPartida,
                                  @Field("cartaJugadorA")int cartaJugadorA,
                                  @Field("caracteristica")String caracteristica,
                                  @Field("cartaJugadorB")int idCartaB);

    @POST("ApiJuegoDef/rest/inicio/resultadoPartida")
    @FormUrlEncoded
    Call<Integer>resultadoPartida(@Field("idPartida")int idPartida,
                                  @Field("idCliente")int idCliente);

    @POST("ApiJuegoDef/rest/inicio/nuevaPartida")
    @FormUrlEncoded
    Call<NuevaPartida> nuevaPartida(@Field("idSession") String idSession,
                                    @Field("idCliente") int idCliente);

    @POST("ApiJuegoDef/rest/inicio/validarLogin")
    @FormUrlEncoded
    Call<String> validarLogin(@Field("nickName") String nickName,
                                    @Field("pass") String pass);

    @POST("ApiJuegoDef/rest/inicio/jugadores")
    @FormUrlEncoded
    Call<Jugadores> insertarJugador(@Field("jugador") String jugador);

    @GET("ApiJuegoDef/rest/inicio/saluda")
    @FormUrlEncoded
    Call<String> getSaludo();

}
