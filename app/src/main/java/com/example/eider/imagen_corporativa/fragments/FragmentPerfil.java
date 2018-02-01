package com.example.eider.imagen_corporativa.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.R;
import com.example.eider.imagen_corporativa.adapters.grid_recyclerview_adapter;
import com.example.eider.imagen_corporativa.lista_mascota;
import com.example.eider.imagen_corporativa.modelos.ContactoResponse;
import com.example.eider.imagen_corporativa.modelos.Mascota;
import com.example.eider.imagen_corporativa.restAPI.EndPointAPI;
import com.example.eider.imagen_corporativa.restAPI.RestAPIAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPerfil extends Fragment {
    grid_recyclerview_adapter adapter;
    ArrayList<Mascota> Arraylistmascotas = new ArrayList<>();
    RecyclerView recyclerView;
    public FragmentPerfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View Roooview =  inflater.inflate(R.layout.fragment_perfil, container, false);
        recyclerView = (RecyclerView) Roooview.findViewById(R.id.grid_recycler_view_mascota);
        obtenerMediosRecientes();
        return  Roooview;
    }

    private void obtenerMediosRecientes() {
        RestAPIAdapter restAPIAdapter= new RestAPIAdapter();
           Gson gson = restAPIAdapter.contruyeGsonDeserializador();
        EndPointAPI endPointAPI= restAPIAdapter.establecerConexionRestAPIInstagram(gson);
        Call<ContactoResponse> contactoResponseCall = endPointAPI.getRecentMedia();
        contactoResponseCall.enqueue(new Callback<ContactoResponse>() {

            @Override
            public void onResponse(Call<ContactoResponse> call, Response<ContactoResponse> response) {
                ContactoResponse contactoResponse = response.body();
                Arraylistmascotas=contactoResponse.getMascotaArrayList();
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                adapter = new grid_recyclerview_adapter(Arraylistmascotas, getContext());
                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onFailure(Call<ContactoResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void obtenerMediosRecientesLuis() {
        RestAPIAdapter restAPIAdapter= new RestAPIAdapter();
        Gson gson = restAPIAdapter.contruyeGsonDeserializador();
        EndPointAPI endPointAPI= restAPIAdapter.establecerConexionRestAPIInstagram(gson);
        Call<ContactoResponse> contactoResponseCall = endPointAPI.getRecentMedialuiisvega1();
        contactoResponseCall.enqueue(new Callback<ContactoResponse>() {

            @Override
            public void onResponse(Call<ContactoResponse> call, Response<ContactoResponse> response) {
                ContactoResponse contactoResponse = response.body();
                Arraylistmascotas=contactoResponse.getMascotaArrayList();
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                adapter = new grid_recyclerview_adapter(Arraylistmascotas, getContext());
                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onFailure(Call<ContactoResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        String cuenta = lista_mascota.cuentaSeleccionada;
        switch (cuenta){
            case "luiisvega1":
                obtenerMediosRecientesLuis();
                break;
            case "eiderdiaz":
                obtenerMediosRecientes();
                break;
        }


    }
}
