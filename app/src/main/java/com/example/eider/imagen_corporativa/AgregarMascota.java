package com.example.eider.imagen_corporativa;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.modelos.Mascota;
import com.example.eider.imagen_corporativa.sqlite.BaseDatos;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public class AgregarMascota extends AppCompatActivity {
    ArrayList<String> MASCOTAS_LIST= new ArrayList<>();
    ArrayList<Mascota> testLIST;
    MaterialBetterSpinner mascotaSpinner;
    BaseDatos admin;
    String MascotaSeleccionada = "badbunny";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mascota);
        MASCOTAS_LIST.add("badbunny");
        MASCOTAS_LIST.add("bugs");
        MASCOTAS_LIST.add("redsanic");
        MASCOTAS_LIST.add("sanic");
        MASCOTAS_LIST.add("snowball");
        admin = new BaseDatos(getApplicationContext());
        if(getIntent().getExtras() != null) {
            if(getIntent().getExtras().containsKey("lista")) {
                 testLIST = (ArrayList<Mascota>) getIntent().getExtras().getSerializable("lista");
            }
        }

        FloatingActionButton actionButton = (FloatingActionButton)findViewById(R.id.fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    admin.insertarmascota(new Mascota(MascotaSeleccionada,0,MascotaSeleccionada));
                    // TODO: 29/01/2018 cehcar el activity for result en fragments
                    int ultimoindice = testLIST.size();
                    testLIST.add(new Mascota(ultimoindice,MascotaSeleccionada,0,MascotaSeleccionada));
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("lista",testLIST);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "error guardado sqlite camara: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            }
        );
        ArrayAdapter<String> arrayaAdapterLista = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, MASCOTAS_LIST);
        mascotaSpinner = (MaterialBetterSpinner) findViewById(R.id.MascotaSpinner);
        mascotaSpinner.setAdapter(arrayaAdapterLista);
        mascotaSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MascotaSeleccionada = MASCOTAS_LIST.get(position);
            }
        });
    }
    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}
