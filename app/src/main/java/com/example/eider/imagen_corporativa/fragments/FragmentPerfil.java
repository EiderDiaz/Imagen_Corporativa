package com.example.eider.imagen_corporativa.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.R;
import com.example.eider.imagen_corporativa.adapters.grid_recyclerview_adapter;
import com.example.eider.imagen_corporativa.firebase.AnimalResponse;
import com.example.eider.imagen_corporativa.firebase.FirebaseRestApiAdapter;
import com.example.eider.imagen_corporativa.lista_mascota;
import com.example.eider.imagen_corporativa.modelos.ContactoResponse;
import com.example.eider.imagen_corporativa.modelos.Mascota;
import com.example.eider.imagen_corporativa.restAPI.EndPointAPI;
import com.example.eider.imagen_corporativa.restAPI.RestAPIAdapter;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;

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
   CircularImageView imageView;
    private static  final String ANIMAL_EMISOR = "Perro";
    private static  final String ID_EMISOR = "dtZAlCFYZ4s:APA91bFSzhNwIeK9ci0VSNX4d837qyexTM07FphbjIBiC540GelX0ExfxK8OUYyJbecl8VvQtiwYhWgL9DZ_tPAupkvy6-Lfp3itwoMBC_kbfJYCKcMYD4Boifr3PJkODe__h9MAkeR9";
    private static  final String ANIMAL_RECEPTOR = "Gato";
    private static  final String ID_RECEPTOR ="fnJIP94VEZo:APA91bGh_p-8uVoPPbfrAPq-Vd0fUP6DrlmHl_Fl3njv6k4LPD5T8qoPzKHq2cELL27VlEpA7HmEqYHOtbP3Tem1bsI9c7x94A22yygexmt1DH-31GEtUnJaAuM6RY5QFfrSJ7VTNmM3";


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
        imageView= (CircularImageView) Roooview.findViewById(R.id.imgPerfil);
        // TODO: 07/02/2018 aqui se supone que se envia el meotdo
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toqueanimal();
            }
        });

        // TODO: 07/02/2018 logclick envia el token
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                enviarToken();
                return true;
            }
        });
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



    public void enviarToken(){
        String token = FirebaseInstanceId.getInstance().getToken();
        //enviarTokenRegistro(token);
        enviarTokenRegistroINSTAGRAM(token);
    }
    public void enviarTokenRegistroINSTAGRAM(String token) {

        FirebaseRestApiAdapter firebaseRestApiAdapter = new FirebaseRestApiAdapter();
        EndPointAPI endPointAPI = firebaseRestApiAdapter.establecerConexionRestAPI();
        Call<AnimalResponse> usuarioResponceCall= endPointAPI.registarUsuarioInstagram(token,ANIMAL_RECEPTOR);

        usuarioResponceCall.enqueue(new Callback<AnimalResponse>() {
            @Override
            public void onResponse(Call<AnimalResponse> call, Response<AnimalResponse> response) {
                AnimalResponse animalResponse = response.body();
                Toast.makeText(getContext(), "id:"+animalResponse.getId()+"\n animal:"+animalResponse.getanimal()
                        +"\ntoken:"+animalResponse.getToken(), Toast.LENGTH_SHORT).show();
                //lanzarNotificacion("26745972");
            }

            @Override
            public void onFailure(Call<AnimalResponse> call, Throwable t) {

            }
        });

    }
    public void toqueanimal (){
        Log.d("toque animal","true");
        AnimalResponse animalResponse = new AnimalResponse("-L4lkKcTePZmfMSFIlMH", "",ANIMAL_EMISOR);
        FirebaseRestApiAdapter firebaseRestApiAdapter = new FirebaseRestApiAdapter();
        EndPointAPI endPointAPI = firebaseRestApiAdapter.establecerConexionRestAPI();
        Call<AnimalResponse> animalResponseCall= endPointAPI.toqueanimal(animalResponse.getId(),animalResponse.getanimal());
        animalResponseCall.enqueue(new Callback<AnimalResponse>() {
            @Override
            public void onResponse(Call<AnimalResponse> call, Response<AnimalResponse> response) {
                    AnimalResponse animalResponse1 = response.body();
                Toast.makeText(getContext(), "ID_Firebase:"+animalResponse1.getId()+"\nAnimal:"+animalResponse1.getanimal(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AnimalResponse> call, Throwable t) {

            }
        });
    }
    ////////////////////
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
                Toast.makeText(getContext(), Arraylistmascotas.get(0).getMedia_id(), Toast.LENGTH_SHORT).show();
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
