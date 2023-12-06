package com.stuarddevapps.scancam;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bnvMenu;
    private Fragment fragment;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ANUNCIOS
        AdsClass.loadAd(this);
        AdsClass.bannerAd(findViewById(R.id.adView1));

        initView();
        initValues();
        initListener();
    }

    private void initView(){
        bnvMenu = findViewById(R.id.bnvMenu);
    }
    private void initValues(){
        manager = getSupportFragmentManager();
        loadFirstFragment();
    }
    private void initListener(){
        bnvMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int idMenu = item.getItemId();
                switch (idMenu){
                    case R.id.menu_qr:
                        getSupportActionBar().setTitle(R.string.qr);
                        fragment = Codigosqr.newInstance();
                        openFragment(fragment);
                        return true;
                    case R.id.menu_bar:
                        getSupportActionBar().setTitle(R.string.barcode);
                        fragment = Codigosbarra.newInstance();
                        openFragment(fragment);
                        return true;
                    case R.id.menu_scans:
                        Intent iea= new Intent(MainActivity.this, ScansLog.class);
                        startActivity(iea);
                        return true;
                }
                return false;
            }
        });
    }
    private void openFragment(Fragment fragment){
        manager.beginTransaction().
                replace(R.id.frameContainer, fragment)
                .commit();
    }
    private void loadFirstFragment(){
        getSupportActionBar().setTitle(R.string.app_name);
        fragment = Codigosqr.newInstance();
        openFragment(fragment);
    }

    //MENU ALTO
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_alto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.it1:
                Intent i = new Intent(this, Terminos.class);
                startActivity(i);
                return true;
            case R.id.it2:
                Intent ie = new Intent(this, Politicas.class);
                startActivity(ie);
                return true;
            case R.id.it3:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("Text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,getResources().getString(R.string.app_name));
                String aux = getString(R.string.msg);
                aux = aux +getString(R.string.appurl);
                intent.putExtra(intent.EXTRA_TEXT, aux);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

}}