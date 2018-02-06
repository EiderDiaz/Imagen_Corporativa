package com.example.eider.imagen_corporativa.restAPI;

import com.example.eider.imagen_corporativa.firebase.UsuarioResponce;
import com.example.eider.imagen_corporativa.modelos.ContactoResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EndPointAPI {

    @GET(ConstantesRestAPI.URL_GET_INFO_USER)
    Call<ContactoResponse> getRecentMedia();

    @GET(ConstantesRestAPI.ID_NEGRO)
    Call<ContactoResponse> getRecentMedialuiisvega1();

    @FormUrlEncoded
    @POST(ConstantesRestAPI.INSERTAR_USUARIO)
    Call<UsuarioResponce> registarTokenId(@Field("token") String token,@Field("id_ig") String id_ig);
}