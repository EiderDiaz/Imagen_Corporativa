package com.example.eider.imagen_corporativa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.R;
import com.example.eider.imagen_corporativa.adapters.Mascotas_adapter;
import com.example.eider.imagen_corporativa.modelos.Mascota;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;

public class Top5Mascotas extends AppCompatActivity {
    private RecyclerView recyclerView;
    ArrayList<Mascota> Arraylistmascotas = new ArrayList<>();
    private Mascotas_adapter mascotas_adapter;
    ArrayList<Mascota> TOP5 = new ArrayList<>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top5_mascotas);

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("lista")) {
                Arraylistmascotas = (ArrayList<Mascota>) getIntent().getExtras().getSerializable("lista");
                acomodartops5();
                recyclerView = (RecyclerView) findViewById(R.id.recycler_view_top5_mascotas);
                mascotas_adapter = new Mascotas_adapter(TOP5, getApplicationContext());
                recyclerView.setAdapter(mascotas_adapter);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
            }

        }
    }

    public  void acomodartops5(){

        TOP5.add(Arraylistmascotas.get(2));
        TOP5.add(Arraylistmascotas.get(1));
        TOP5.add(Arraylistmascotas.get(0));
        TOP5.add(Arraylistmascotas.get(4));
        TOP5.add(Arraylistmascotas.get(3));


    }

    public void volver(View view){
        finish();

    }
}
