package com.example.eider.imagen_corporativa;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eider.imagen_corporativa.adapters.Mascotas_adapter;
import com.example.eider.imagen_corporativa.modelos.Mascota;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_recyclerview extends Fragment {
    private Mascotas_adapter mascotas_adapter;
    private RecyclerView recyclerView;
    ArrayList<Mascota> Arraylistmascotas = new ArrayList<>();


    public fragment_recyclerview() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View Roooview = inflater.inflate(R.layout.fragment_fragment_recyclerview, container, false);
        recyclerView = (RecyclerView) Roooview.findViewById(R.id.recycler_view_mascotas);
        Arraylistmascotas.add(new Mascota("Sanic", 5, "sanic"));
        Arraylistmascotas.add(new Mascota("Bugs bunny", 6, "bugs"));
        Arraylistmascotas.add(new Mascota("Bad bunny", 7, "images"));
        Arraylistmascotas.add(new Mascota("Snowball", 2, "snowball"));
        Arraylistmascotas.add(new Mascota("Este perro", 3, "perro1"));
        Arraylistmascotas.add(new Mascota("sanic again", 1, "sanic2"));


        mascotas_adapter = new Mascotas_adapter(Arraylistmascotas, getActivity());
        recyclerView.setAdapter(mascotas_adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        return Roooview;

    }
}
