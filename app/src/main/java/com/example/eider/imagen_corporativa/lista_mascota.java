package com.example.eider.imagen_corporativa;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.ToolBarMenu.About_me;
import com.example.eider.imagen_corporativa.adapters.Mascotas_adapter;
import com.example.eider.imagen_corporativa.adapters.PageAdapter;
import com.example.eider.imagen_corporativa.firebase.AnimalResponse;
import com.example.eider.imagen_corporativa.firebase.FirebaseRestApiAdapter;
import com.example.eider.imagen_corporativa.firebase.UsuarioResponce;
import com.example.eider.imagen_corporativa.fragments.FragmentPerfil;
import com.example.eider.imagen_corporativa.fragments.fragment_recyclerview;
import com.example.eider.imagen_corporativa.modelos.Mascota;
import com.example.eider.imagen_corporativa.restAPI.EndPointAPI;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class lista_mascota extends AppCompatActivity {

    public static Context mContext;
    private  Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String cuentaSeleccionada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascota);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        mContext = getBaseContext();

        SetupViewPager();
        if (toolbar != null){
            setSupportActionBar(toolbar);

        }

        int defaultValue = 0;
        try {
            Toast.makeText(mContext, ""+getIntent().getIntExtra("One",defaultValue), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (getIntent().getIntExtra("One",defaultValue)==1){
            Toast.makeText(this, "k pex", Toast.LENGTH_SHORT).show();
            int page = getIntent().getIntExtra("One", defaultValue);
            viewPager.setCurrentItem(page);
        }

    }
    public static Context getContext() {
        return mContext;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if(data.getExtras().containsKey("cuenta")) {
                    cuentaSeleccionada =  data.getExtras().getString("cuenta");

                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_opciones,menu);
            return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mabout:
                    Intent intent = new Intent(getApplicationContext(),About_me.class);
                    startActivity(intent);
                break;
            case  R.id.contacto:
                Intent intent1 = new Intent(getApplicationContext(),Contact.class);
                startActivity(intent1);
                    break;

            case  R.id.confCuenta:
                Intent intent2 = new Intent(getApplicationContext(),ConfigurarCuenta.class);
                startActivityForResult(intent2,1);
                break;
            case  R.id.notificaciones:
                enviarToken();
                break;

        }
        return  true;
    }

    public void enviarToken(){
        String token = FirebaseInstanceId.getInstance().getToken();
        //enviarTokenRegistro(token);
        enviarTokenRegistro(token);
    }

    public void enviarTokenRegistro(String token) {
        FirebaseRestApiAdapter firebaseRestApiAdapter = new FirebaseRestApiAdapter();
        EndPointAPI endPointAPI = firebaseRestApiAdapter.establecerConexionRestAPI();
        Call<UsuarioResponce> usuarioResponceCall= endPointAPI.registarTokenId(token,"26745972");

        usuarioResponceCall.enqueue(new Callback<UsuarioResponce>() {
            @Override
            public void onResponse(Call<UsuarioResponce> call, Response<UsuarioResponce> response) {
                UsuarioResponce usuarioResponce = response.body();
                //Toast.makeText(lista_mascota.this, "token:"+usuarioResponce.getToken()+"\nid:"+usuarioResponce.getId()+"\nuser_ig"+usuarioResponce.getInstagram_user(), Toast.LENGTH_SHORT).show();
                lanzarNotificacion("26745972");
            }

            @Override
            public void onFailure(Call<UsuarioResponce> call, Throwable t) {

            }
        });

    }


    public void lanzarNotificacion(String mensaje) {
        Intent intent = new Intent(this, lista_mascota.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Notificacion")
                .setContentText("Registro con ID:"+mensaje+ " insertado en Firebase :)")
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationCompat.build());
    }



    private ArrayList<Fragment> agregarfragment(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new fragment_recyclerview());
        fragments.add(new FragmentPerfil());
        return  fragments;

    }
    private void SetupViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarfragment()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.top5);
        tabLayout.getTabAt(1).setIcon(R.drawable.bone);

    }
    public void top5(View view){
        Intent intent = new Intent(getApplicationContext(), Top5Mascotas.class);
        startActivity(intent);

    }



}
