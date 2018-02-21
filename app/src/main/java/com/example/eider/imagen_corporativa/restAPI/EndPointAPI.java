package com.example.eider.imagen_corporativa.restAPI;

import com.example.eider.imagen_corporativa.firebase.AnimalResponse;
import com.example.eider.imagen_corporativa.firebase.UsuarioResponce;
import com.example.eider.imagen_corporativa.modelos.ContactoResponse;
import com.example.eider.imagen_corporativa.modelos.data;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndPointAPI {

    @GET(ConstantesRestAPI.URL_GET_INFO_USER)
    Call<ContactoResponse> getRecentMedia();

    @GET(ConstantesRestAPI.ID_NEGRO)
    Call<ContactoResponse> getRecentMedialuiisvega1();

    @FormUrlEncoded
    @POST(ConstantesRestAPI.INSERTAR_USUARIO)
    Call<UsuarioResponce> registarTokenId(@Field("token") String token,@Field("id_ig") String id_ig);

    @FormUrlEncoded
    @POST(ConstantesRestAPI.INSERTAR_USUARIO_INSTAGRAM)
    Call<AnimalResponse> registarUsuarioInstagram(@Field("token") String token, @Field("animal") String animal);



    @GET(ConstantesRestAPI.TOQUE_ANIMAL)
    Call<AnimalResponse> toqueanimal(@Path("id") String id, @Path("animal") String animal);

    @FormUrlEncoded
    @POST(ConstantesRestAPI.LIKE_INSTAGRAM)
    Call<ResponseBody> likeEnIntragram(@Path("mediaid") String media_id,@Field("access_token") String access_token);

    @FormUrlEncoded
    @POST(ConstantesRestAPI.FOLLOW_INSTAGRAM)
    Call<ResponseBody> FollowEnIntragram(@Path("user-id") String user_id,@Field("access_token") String access_token,@Field("action") String action ) ;

    @GET(ConstantesRestAPI.VERIFICAR_FOLLOW_INSTAGRAM)
    Call<Object> VerificarFollowEnIntragram();

}