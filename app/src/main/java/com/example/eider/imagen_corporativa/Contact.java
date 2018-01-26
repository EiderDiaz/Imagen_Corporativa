package com.example.eider.imagen_corporativa;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.Email.SendMail;

public class Contact extends AppCompatActivity {
private TextInputEditText direccionEmail;
private  TextInputEditText nombre;
private  TextInputEditText asunto;
private  TextInputEditText cuerpoCorreo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            direccionEmail = (TextInputEditText)findViewById(R.id.tvdircorreo);
            nombre = (TextInputEditText)findViewById(R.id.tvnombre);
            asunto = (TextInputEditText)findViewById(R.id.tvasunto);
            cuerpoCorreo = (TextInputEditText)findViewById(R.id.tvcorreo);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            sendEmail();
                Snackbar.make(view, "Correo enviado ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public  void guardarPreferencia(View view) {
        SharedPreferences mysharedPreferences = getSharedPreferences("misdatospersonales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mysharedPreferences.edit();
        String mail = direccionEmail.getText().toString();
        String name = nombre.getText().toString();

        editor.putString("nombre", name);
        editor.putString("correo", mail);
        editor.commit();
        Toast.makeText(this, "se ha creado la preferencia compartida", Toast.LENGTH_SHORT).show();

    }


    public  void cargarPreferencia(View view){
        SharedPreferences mysharedPreferences = getSharedPreferences("misdatospersonales", Context.MODE_PRIVATE);
        String name = mysharedPreferences.getString("nombre","no existe");
        String mail = mysharedPreferences.getString("correo","no existe");
         direccionEmail.setText(mail);
        nombre.setText(name);


    }


    private void sendEmail() {
        //Getting content for emailca
        String email = direccionEmail.getText().toString().trim();
        String subject = asunto.getText().toString().trim();
        String message =  "Hola "+ nombre.getText().toString().trim() +"\n"+cuerpoCorreo.getText().toString().trim();
        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    }


