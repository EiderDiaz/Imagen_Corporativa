package com.example.eider.imagen_corporativa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public class ConfigurarCuenta extends AppCompatActivity {
    MaterialBetterSpinner seleccionaCuentaSpinner;
    String CuentaSeleccionada ;
    ArrayList<String> cuentasList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cuentasList.add("eiderdiaz");
        cuentasList.add("luiisvega1");
        ArrayAdapter<String> arrayadapteragricola = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, cuentasList);
        seleccionaCuentaSpinner = (MaterialBetterSpinner) findViewById(R.id.CuentaSpinner);
        seleccionaCuentaSpinner.setAdapter(arrayadapteragricola);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("cuenta",CuentaSeleccionada);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        seleccionaCuentaSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CuentaSeleccionada = cuentasList.get(position);
                Toast.makeText(ConfigurarCuenta.this, CuentaSeleccionada, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

}
