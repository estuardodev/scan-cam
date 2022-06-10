package com.stuarddevapps.scancam;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bnvMenu;
    private Fragment fragment;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                    case R.id.menu_other:
                        getSupportActionBar().setTitle(R.string.other);
                        fragment = Codigosotros.newInstance();
                        openFragment(fragment);
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

}