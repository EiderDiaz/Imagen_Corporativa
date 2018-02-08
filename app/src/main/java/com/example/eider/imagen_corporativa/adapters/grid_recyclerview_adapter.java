package com.example.eider.imagen_corporativa.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.R;
import com.example.eider.imagen_corporativa.firebase.AnimalResponse;
import com.example.eider.imagen_corporativa.firebase.FirebaseRestApiAdapter;
import com.example.eider.imagen_corporativa.modelos.ContactoResponse;
import com.example.eider.imagen_corporativa.modelos.Mascota;
import com.example.eider.imagen_corporativa.restAPI.EndPointAPI;
import com.example.eider.imagen_corporativa.restAPI.RestAPIAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        public TextView  raiting;
        public ImageView imagenMascota;



        public MyViewHolder(View view) {
            super(view);
            raiting = (TextView) view.findViewById(R.id.calificacion);
            imagenMascota =  (ImageView) view.findViewById(R.id.imagen_mascota);

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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
            final Mascota mascota = ArraylistMascotas.get(position);
            holder.raiting.setText(""+mascota.getRaiting());
///////////// mamalon si me sale de poner imagen directo solo usando string
            Resources res = context.getResources();
        String mDrawableName = mascota.getImagen();
        Picasso.with(context)
                .load(mascota.getImagen())
                .placeholder(R.drawable.sanic)
                .into(holder.imagenMascota);
        holder.imagenMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DarLikeInstagram(position);
            }
        });
       /* int resID = res.getIdentifier(mDrawableName , "drawable", context.getPackageName());
        Drawable drawable = ContextCompat.getDrawable(context,resID); //res.getDrawable(resID );
        holder.imagenMascota.setImageDrawable(drawable );*/
        ///////////


    }
    private void DarLikeInstagram(final int position) {
            String media_id= ArraylistMascotas.get(position).getMedia_id();
        RestAPIAdapter restAPIAdapter= new RestAPIAdapter();
        EndPointAPI endPointAPI= restAPIAdapter.establecerConexionRestAPIInstagramLIKE();
        Call<ResponseBody> contactoResponseCall = endPointAPI.likeEnIntragram(media_id,"301295440.800d162.6b24086df07341f5886ebc48b677a1d9");
        contactoResponseCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "le has dado like a foto con id:"+ArraylistMascotas.get(position).getMedia_id() , Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "valio pija hermano", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return  ArraylistMascotas.size();
    }



} 
