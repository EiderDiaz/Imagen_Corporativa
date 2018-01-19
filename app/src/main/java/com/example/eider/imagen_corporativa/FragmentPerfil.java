package com.example.eider.imagen_corporativa;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eider.imagen_corporativa.adapters.grid_recyclerview_adapter;
import com.example.eider.imagen_corporativa.modelos.Mascota;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPerfil extends Fragment {
    grid_recyclerview_adapter adapter;
    ArrayList<Mascota> Arraylistmascotas = new ArrayList<>();

    public FragmentPerfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View Roooview =  inflater.inflate(R.layout.fragment_perfil, container, false);

        Arraylistmascotas.add(new Mascota("Sanic", 5, "sanic"));
        Arraylistmascotas.add(new Mascota("Bugs bunny", 6, "bugs"));
        Arraylistmascotas.add(new Mascota("Bad bunny", 7, "images"));
        Arraylistmascotas.add(new Mascota("Snowball", 2, "snowball"));
        Arraylistmascotas.add(new Mascota("Este perro", 3, "perro1"));
        Arraylistmascotas.add(new Mascota("sanic again", 1, "sanic2"));
        RecyclerView recyclerView = (RecyclerView) Roooview.findViewById(R.id.grid_recycler_view_mascota);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new grid_recyclerview_adapter(Arraylistmascotas, getContext());
        recyclerView.setAdapter(adapter);
        return  Roooview;
    }

}
