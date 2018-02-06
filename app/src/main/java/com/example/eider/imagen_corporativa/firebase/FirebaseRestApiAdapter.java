package com.example.eider.imagen_corporativa.firebase;


import com.example.eider.imagen_corporativa.restAPI.ConstantesRestAPI;
import com.example.eider.imagen_corporativa.restAPI.EndPointAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class FirebaseRestApiAdapter {
    public EndPointAPI establecerConexionRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPI.BASE_URL_FIREBASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(EndPointAPI.class);
    }

}