package com.example.eider.imagen_corporativa.firebase;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.lista_mascota;
import com.example.eider.imagen_corporativa.restAPI.EndPointAPI;
import com.example.eider.imagen_corporativa.restAPI.RestAPIAdapter;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public  class toqueAnimal extends BroadcastReceiver{
Context context = lista_mascota.getContext();
    @Override
    public void onReceive(Context context, Intent intent) {
            String ACCION_KEY = "TOQUE_ANIMAL";
            String accion = intent.getAction();

        if (accion==ACCION_KEY){
                toqueanimal();
                Toast.makeText(context, "diste un toque al emulador ", Toast.LENGTH_SHORT).show();
            }
            if (accion=="VER-MI-PERFIL"){
                int page = 1;
                Intent intent2 = new Intent(lista_mascota.getContext(),lista_mascota.class);
                intent2.putExtra("One", page);// One is your argument
                lista_mascota.getContext().startActivity(intent2);
                Log.d("toque animal","ALVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");

            }
            if (accion=="DAR-FOLLOW-UNFOLLOW"){
                FollowInstagram();
            }
    }

    public void toqueanimal (){
        Log.d("toque animal","true");
        AnimalResponse animalResponse = new AnimalResponse("-L5eopErjHMMDjs3f-J6", "","emulador");
        FirebaseRestApiAdapter firebaseRestApiAdapter = new FirebaseRestApiAdapter();
        EndPointAPI endPointAPI = firebaseRestApiAdapter.establecerConexionRestAPI();
        Call<AnimalResponse> animalResponseCall= endPointAPI.toqueanimal(animalResponse.getId(),animalResponse.getanimal());
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

    private void FollowInstagram() {
        RestAPIAdapter restAPIAdapter= new RestAPIAdapter();
        EndPointAPI endPointAPI= restAPIAdapter.establecerConexionRestAPIInstagramLIKE();
        Call<ResponseBody> contactoResponseCall = endPointAPI.FollowEnIntragram("26745972","301295440.800d162.6b24086df07341f5886ebc48b677a1d9","follow");
        contactoResponseCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "todo bien", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "todo mal", Toast.LENGTH_SHORT).show();
            }
        });

    }

}