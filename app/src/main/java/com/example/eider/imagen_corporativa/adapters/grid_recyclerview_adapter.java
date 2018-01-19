package com.example.eider.imagen_corporativa.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.R;
import com.example.eider.imagen_corporativa.modelos.Mascota;

import java.util.ArrayList;

public class grid_recyclerview_adapter extends  RecyclerView.Adapter<grid_recyclerview_adapter.MyViewHolder> {

    ArrayList <Mascota> ArraylistMascotas;
    Context context;
    private LayoutInflater mInflater;
    //private ItemClickListener mClickListener;


    public grid_recyclerview_adapter(ArrayList <Mascota> ArraylistMascotas, Context context ){
        this.ArraylistMascotas= ArraylistMascotas;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);

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
        //View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon_mascota, parent, false);
        //return new grid_recyclerview_adapter.MyViewHolder(itemView);
        View view = mInflater.inflate(R.layout.grid_recyclerview_item, parent, false);
        return new MyViewHolder(view);
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
