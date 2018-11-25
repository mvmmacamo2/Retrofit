package com.kaya.retrofit;

import com.kaya.retrofit.Evento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("eventos")
    Call<List<Evento>> getEventos();
}
