package com.example.eider.imagen_corporativa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.eider.imagen_corporativa.ToolBarMenu.About_me;
import com.example.eider.imagen_corporativa.adapters.Mascotas_adapter;
import com.example.eider.imagen_corporativa.adapters.PageAdapter;
import com.example.eider.imagen_corporativa.fragments.FragmentPerfil;
import com.example.eider.imagen_corporativa.fragments.fragment_recyclerview;
import com.example.eider.imagen_corporativa.modelos.Mascota;

import java.util.ArrayList;

public class lista_mascota extends AppCompatActivity {


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
        SetupViewPager();
        if (toolbar != null){
            setSupportActionBar(toolbar);

        }

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

        }
        return  true;
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
