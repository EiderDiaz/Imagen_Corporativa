package com.example.eider.imagen_corporativa.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ContactoResponse  {

    ArrayList<Mascota> mascotaArrayList = new ArrayList<>();


    public ContactoResponse(ArrayList<Mascota> mascotaArrayList) {
        this.mascotaArrayList = mascotaArrayList;
    }

    public ArrayList<Mascota> getMascotaArrayList() {
        return mascotaArrayList;
    }

    public void setMascotaArrayList(ArrayList<Mascota> mascotaArrayList) {
        this.mascotaArrayList = mascotaArrayList;
    }
}
