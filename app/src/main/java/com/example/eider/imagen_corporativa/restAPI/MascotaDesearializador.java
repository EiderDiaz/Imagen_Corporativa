package com.example.eider.imagen_corporativa.restAPI;


import com.example.eider.imagen_corporativa.modelos.ContactoResponse;
import com.example.eider.imagen_corporativa.modelos.Mascota;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MascotaDesearializador implements JsonDeserializer<ContactoResponse>{

    @Override
    public ContactoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson= new Gson();
       ContactoResponse contactoResponse = gson.fromJson(json,ContactoResponse.class);
        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray("data");
        contactoResponse.setMascotaArrayList(deserializarContactoDeJson(contactoResponseData));
        return contactoResponse;
    }

    private ArrayList<Mascota> deserializarContactoDeJson(JsonArray contactoResponseData){
        ArrayList<Mascota> mascotaArrayList= new ArrayList<>();
        for (int i =0;i<contactoResponseData.size();i++){
            JsonObject contactoResponseDataObject = contactoResponseData.get(i).getAsJsonObject();
            String media_id = contactoResponseDataObject.get("id").getAsString();
            JsonObject userJson =contactoResponseDataObject.getAsJsonObject("user");
            String id = userJson.get("id").getAsString();
            String nombre = userJson.get("full_name").getAsString();
            JsonObject imageJson = contactoResponseDataObject.getAsJsonObject("images");
            JsonObject stdResolutionJson  = imageJson.getAsJsonObject("standard_resolution");
            String urlFoto = stdResolutionJson.get("url").getAsString();
            JsonObject likesJson = contactoResponseDataObject.getAsJsonObject("likes");
            int likes = likesJson.get("count").getAsInt();

            mascotaArrayList.add(new Mascota(Integer.valueOf(id),nombre,likes,urlFoto,media_id));
        }
        return mascotaArrayList;
    }
}