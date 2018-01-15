package com.example.eider.imagen_corporativa.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.R;
import com.example.eider.imagen_corporativa.modelos.Mascota;

import java.io.File;
import java.util.ArrayList;

public class Mascotas_adapter extends  RecyclerView.Adapter<Mascotas_adapter.MyViewHolder> {

    ArrayList <Mascota> ArraylistMascotas;
    Context context;
    public  Mascotas_adapter (ArrayList <Mascota> ArraylistMascotas,Context context ){
        this.ArraylistMascotas= ArraylistMascotas;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, raiting;
        public ImageView imagenMascota, huesoRate;



        public MyViewHolder(View view) {
            super(view);
            nombre=(TextView) view.findViewById(R.id.tvnombre_mascota);
            raiting = (TextView) view.findViewById(R.id.calificacion);
            imagenMascota =  (ImageView) view.findViewById(R.id.imagen_mascota);
            huesoRate = (ImageView) view.findViewById(R.id.boton_rate);

        }

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon_mascota, parent, false);
        return new Mascotas_adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

            final Mascota mascota = ArraylistMascotas.get(position);
            holder.nombre.setText(mascota.getNombre());
            holder.raiting.setText(""+mascota.getRaiting());
///////////// mamalon si me sale de poner imagen directo solo usando string
            Resources res = context.getResources();
        String mDrawableName = mascota.getImagen();
        int resID = res.getIdentifier(mDrawableName , "drawable", context.getPackageName());
        Drawable drawable = ContextCompat.getDrawable(context,resID); //res.getDrawable(resID );
        holder.imagenMascota.setImageDrawable(drawable );
        ///////////

    holder.huesoRate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           int Contador=0;
           Contador++;
           int nuevorate = mascota.getRaiting()+Contador;
           mascota.setRaiting(nuevorate);
           holder.raiting.setText(""+nuevorate);
            Toast.makeText(context, "le diste like a "+mascota.getNombre(), Toast.LENGTH_SHORT).show();
        }
    });

    }


    @Override
    public int getItemCount() {
        return  ArraylistMascotas.size();
    }



} 
