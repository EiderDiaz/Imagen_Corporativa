package com.example.eider.imagen_corporativa.restAPI;

import com.example.eider.imagen_corporativa.modelos.ContactoResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class RestAPIAdapter {

    public  EndPointAPI establecerConexionRestAPIInstagram(Gson gson){
        Retrofit  retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return  retrofit.create(EndPointAPI.class);

    }
    public  EndPointAPI establecerConexionRestAPIInstagramLIKE(){
        Retrofit  retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit.create(EndPointAPI.class);
    }

public Gson contruyeGsonDeserializador(){
    GsonBuilder gsonBuilder =  new GsonBuilder();
    gsonBuilder.registerTypeAdapter(ContactoResponse.class,new MascotaDesearializador());
    return   gsonBuilder.create();

}


}