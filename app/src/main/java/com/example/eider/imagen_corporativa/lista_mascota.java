package com.example.eider.imagen_corporativa;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.adapters.Mascotas_adapter;
import com.example.eider.imagen_corporativa.modelos.Mascota;
import com.example.eider.imagen_corporativa.modelos.Top5Mascotas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class lista_mascota extends AppCompatActivity {

    private Mascotas_adapter mascotas_adapter;
    private RecyclerView recyclerView;
    ArrayList<Mascota> Arraylistmascotas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascota);
        Toolbar toolbar = (Toolbar)findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view_mascotas);
        Arraylistmascotas.add(new Mascota("Sanic",5,"sanic"));
        Arraylistmascotas.add(new Mascota("Bugs bunny",6,"bugs"));
        Arraylistmascotas.add(new Mascota("Bad bunny",7,"images"));
        Arraylistmascotas.add(new Mascota("Snowball",2,"snowball"));
        Arraylistmascotas.add(new Mascota("Este perro",3,"perro1"));


        mascotas_adapter = new Mascotas_adapter(Arraylistmascotas,getApplicationContext());
        recyclerView.setAdapter(mascotas_adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }


    public void top5(View view){
        Intent intent = new Intent(getApplicationContext(), Top5Mascotas.class);
        intent.putExtra("lista",Arraylistmascotas);
        startActivity(intent);

    }



}
