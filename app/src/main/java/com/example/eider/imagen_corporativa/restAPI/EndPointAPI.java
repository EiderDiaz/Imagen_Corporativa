package com.example.eider.imagen_corporativa.restAPI;

import com.example.eider.imagen_corporativa.modelos.ContactoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPointAPI {
    @GET(ConstantesRestAPI.URL_GET_INFO_USER)
    Call<ContactoResponse> getRecentMedia();


}