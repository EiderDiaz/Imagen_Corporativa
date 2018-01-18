package com.example.eider.imagen_corporativa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.eider.imagen_corporativa.adapters.Mascotas_adapter;
import com.example.eider.imagen_corporativa.adapters.PageAdapter;
import com.example.eider.imagen_corporativa.modelos.Mascota;

import java.util.ArrayList;

public class lista_mascota extends AppCompatActivity {


    private  Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;



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




       /* */


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    private ArrayList<Fragment> agregarfragment(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new fragment_recyclerview());
        fragments.add(new  FragmentPerfil());
        return  fragments;

    }
    private void SetupViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarfragment()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.top5);
        tabLayout.getTabAt(1).setIcon(R.drawable.bone);

    }
   /* public void top5(View view){
        Intent intent = new Intent(getApplicationContext(), Top5Mascotas.class);
        intent.putExtra("lista",Arraylistmascotas);
        startActivity(intent);

    } */



}
