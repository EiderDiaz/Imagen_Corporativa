package com.example.eider.imagen_corporativa.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.R;
import com.example.eider.imagen_corporativa.modelos.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String querycreartabla_mascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" +
                ConstantesBaseDatos.TABLE_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_RAITING + " INTEGER, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_FOTO + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(querycreartabla_mascota);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(context.getString(R.string.DROP) +" "+ ConstantesBaseDatos.TABLE_MASCOTAS);
        onCreate(sqLiteDatabase);

    }


    public ArrayList<Mascota> obtenerTodosLasMascotas() {

        ArrayList<Mascota> Mascotas = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            Mascota MascotaActual = new Mascota();
            MascotaActual.setId(registros.getInt(0));
            MascotaActual.setNombre(registros.getString(1));
            MascotaActual.setRaiting(registros.getInt(2));
            MascotaActual.setImagen(registros.getString(3));
            Mascotas.add(MascotaActual);
        }

        db.close();

        return Mascotas;

    }



    public void insertarmascota (Mascota mascota){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, mascota.getNombre());
            values.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING,mascota.getRaiting());
            values.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,mascota.getImagen());
            long err= db.insert(ConstantesBaseDatos.TABLE_MASCOTAS, null, values);
            db.close();
            if (err == -1) Toast.makeText(context, "error en la insercion de la tabla "+ConstantesBaseDatos.TABLE_MASCOTAS+" error:"+err, Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(context, "sqlite insertarmascota: "+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public int actualizarMascota(Mascota mascota) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ConstantesBaseDatos.TABLE_MASCOTAS_ID, mascota.getId());
        values.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, mascota.getNombre());
        values.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING,mascota.getRaiting());
        values.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO,mascota.getImagen());
        // updating row
        return db.update(ConstantesBaseDatos.TABLE_MASCOTAS, values, ConstantesBaseDatos.TABLE_MASCOTAS_ID + " = ?", new String[] {String.valueOf(mascota.getId())});
    }
}