package com.example.eider.imagen_corporativa.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Mascota implements Parcelable {

    private String nombre;
    private  int raiting;
    private  String imagen;


    public Mascota(String nombre, int raiting, String imagen) {
        this.nombre = nombre;
        this.raiting = raiting;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    protected Mascota(Parcel in) {
        nombre = in.readString();
        raiting = in.readInt();
        imagen = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeInt(raiting);
        dest.writeString(imagen);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Mascota> CREATOR = new Parcelable.Creator<Mascota>() {
        @Override
        public Mascota createFromParcel(Parcel in) {
            return new Mascota(in);
        }

        @Override
        public Mascota[] newArray(int size) {
            return new Mascota[size];
        }
    };
}
