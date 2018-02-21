package com.example.eider.imagen_corporativa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.adapters.grid_recyclerview_adapter;
import com.example.eider.imagen_corporativa.modelos.ContactoResponse;
import com.example.eider.imagen_corporativa.modelos.Mascota;
import com.example.eider.imagen_corporativa.restAPI.EndPointAPI;
import com.example.eider.imagen_corporativa.restAPI.RestAPIAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ver_perfil extends AppCompatActivity {
    grid_recyclerview_adapter adapter;
    ArrayList<Mascota> Arraylistmascotas = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);
        recyclerView = (RecyclerView) findViewById(R.id.grid_recycler_view_ver_perfil);
        obtenerMediosRecientesLuis();
    }

    public  void obtenerMediosRecientesLuis() {
        RestAPIAdapter restAPIAdapter= new RestAPIAdapter();
        Gson gson = restAPIAdapter.contruyeGsonDeserializador();
        EndPointAPI endPointAPI= restAPIAdapter.establecerConexionRestAPIInstagram(gson);
        Call<ContactoResponse> contactoResponseCall = endPointAPI.getRecentMedialuiisvega1();
        contactoResponseCall.enqueue(new Callback<ContactoResponse>() {

            @Override
            public void onResponse(Call<ContactoResponse> call, Response<ContactoResponse> response) {
                ContactoResponse contactoResponse = response.body();
                Arraylistmascotas=contactoResponse.getMascotaArrayList();
                Toast.makeText(getApplicationContext(), Arraylistmascotas.get(0).getMedia_id(), Toast.LENGTH_SHORT).show();
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                adapter = new grid_recyclerview_adapter(Arraylistmascotas, getApplicationContext());
                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onFailure(Call<ContactoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}
