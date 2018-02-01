package com.example.eider.imagen_corporativa.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.AgregarMascota;
import com.example.eider.imagen_corporativa.R;
import com.example.eider.imagen_corporativa.adapters.Mascotas_adapter;
import com.example.eider.imagen_corporativa.modelos.Mascota;
import com.example.eider.imagen_corporativa.sqlite.BaseDatos;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_recyclerview extends Fragment {
    private Mascotas_adapter mascotas_adapter;
    private RecyclerView recyclerView;
    ArrayList<Mascota> Arraylistmascotas = new ArrayList<>();
    BaseDatos admin;

    public fragment_recyclerview() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View Roooview = inflater.inflate(R.layout.fragment_fragment_recyclerview, container, false);
        recyclerView = (RecyclerView) Roooview.findViewById(R.id.recycler_view_mascotas);
        admin = new BaseDatos(getContext());
        ArrayList<Mascota> testLIST = admin.obtenerTodosLasMascotas();
        for (Mascota mascota:testLIST){
            Arraylistmascotas.add(mascota);
        }
        FloatingActionButton fab = (FloatingActionButton) Roooview.findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "agregar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(getContext(),AgregarMascota.class);
                intent.putExtra("lista",Arraylistmascotas);
                startActivityForResult(intent,0);

            }
        });

        mascotas_adapter = new Mascotas_adapter(Arraylistmascotas, getActivity());
        recyclerView.setAdapter(mascotas_adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        return Roooview;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                if(data.getExtras().containsKey("lista")) {
                    Arraylistmascotas = (ArrayList<Mascota>) data.getExtras().getSerializable("lista");
                    mascotas_adapter = new Mascotas_adapter(Arraylistmascotas, getActivity());
                    recyclerView.setAdapter(mascotas_adapter);
                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
