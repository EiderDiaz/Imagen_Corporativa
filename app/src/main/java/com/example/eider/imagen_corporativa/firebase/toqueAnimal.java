package com.example.eider.imagen_corporativa.firebase;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.R;
import com.example.eider.imagen_corporativa.adapters.grid_recyclerview_adapter;
import com.example.eider.imagen_corporativa.lista_mascota;
import com.example.eider.imagen_corporativa.modelos.ContactoResponse;
import com.example.eider.imagen_corporativa.modelos.data;
import com.example.eider.imagen_corporativa.restAPI.EndPointAPI;
import com.example.eider.imagen_corporativa.restAPI.RestAPIAdapter;
import com.example.eider.imagen_corporativa.ver_perfil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import javax.mail.Header;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.eider.imagen_corporativa.restAPI.ConstantesRestAPI.BASE_URL;


public  class toqueAnimal extends BroadcastReceiver {
    Context context = lista_mascota.getContext();

    @Override
    public void onReceive(Context context, Intent intent) {
        String ACCION_KEY = "TOQUE_ANIMAL";
        String accion = intent.getAction();

        if (accion == ACCION_KEY) {
            toqueanimal();
            Toast.makeText(context, "diste un toque al emulador ", Toast.LENGTH_SHORT).show();
        }
        if (accion == "VER-MI-PERFIL") {
            int page = 1;
            Intent intent2 = new Intent(lista_mascota.getContext(), lista_mascota.class);
            intent2.putExtra("One", page);// One is your argument
            lista_mascota.getContext().startActivity(intent2);
        }

        if (accion == "DAR-FOLLOW-UNFOLLOW") {
            //FollowInstagram();
            VerficarFollowInstagram();
        }


        if (accion == "VER-USUARIO") {
            obtenerMediosRecientesLuis();
        }


    }

    private void obtenerMediosRecientesLuis() {
        Intent intent2 = new Intent(context, ver_perfil.class);
        lista_mascota.getContext().startActivity(intent2);
        }


    public void toqueanimal() {
        Log.d("toque animal", "true");
        AnimalResponse animalResponse = new AnimalResponse("-L5eopErjHMMDjs3f-J6", "", "emulador");
        FirebaseRestApiAdapter firebaseRestApiAdapter = new FirebaseRestApiAdapter();
        EndPointAPI endPointAPI = firebaseRestApiAdapter.establecerConexionRestAPI();
        Call<AnimalResponse> animalResponseCall = endPointAPI.toqueanimal(animalResponse.getId(), animalResponse.getanimal());
        animalResponseCall.enqueue(new Callback<AnimalResponse>() {
            @Override
            public void onResponse(Call<AnimalResponse> call, Response<AnimalResponse> response) {
                AnimalResponse animalResponse1 = response.body();

                //Toast.makeText(getContext(), "ID_Firebase:"+animalResponse1.getId()+"\nAnimal:"+animalResponse1.getanimal(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AnimalResponse> call, Throwable t) {

            }
        });
    }

    private void FollowInstagram(String status) {
        Toast.makeText(context, ":"+status+":", Toast.LENGTH_SHORT).show();
        String accion = "follow";
        if (status.equals("follows")){
            accion= "unfollow";
        }
        else{
            accion = "follow";
        }

        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        EndPointAPI endPointAPI = restAPIAdapter.establecerConexionRestAPIInstagramLIKE();
        Call<ResponseBody> contactoResponseCall = endPointAPI.FollowEnIntragram("26745972", "301295440.800d162.6b24086df07341f5886ebc48b677a1d9", accion);
        contactoResponseCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                String csrf = response.headers().get("content-type");
                Toast.makeText(context, "CSRF: " + csrf, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "todo mal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void VerficarFollowInstagram() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        EndPointAPI endPointAPI = retrofit.create(EndPointAPI.class);
        Call  contactoResponseCall = endPointAPI.VerificarFollowEnIntragram();
        contactoResponseCall.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                // TODO: 20/02/2018 aqui esta como tener info de un json crudo
                String gsoncadena = new Gson().toJson(response.body());
                String[] splited1 = gsoncadena.split("outgoing_status\":\"");
                String[] splited2 = splited1[1].split("\",");
              //  Toast.makeText(context, ""+splited2[0], Toast.LENGTH_SHORT).show();
                FollowInstagram(splited2[0]);
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });


    }
}