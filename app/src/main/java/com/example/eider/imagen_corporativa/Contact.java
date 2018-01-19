package com.example.eider.imagen_corporativa;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
    private void sendEmail() {
        //Getting content for email
        String email = direccionEmail.getText().toString().trim();
        String subject = asunto.getText().toString().trim();
        String message =  "Hola "+ nombre.getText().toString().trim() +"\n"+cuerpoCorreo.getText().toString().trim();
        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    }


