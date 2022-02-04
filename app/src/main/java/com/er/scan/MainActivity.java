package com.er.scan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    Codigoqr codigoqr = new Codigoqr();
    CodigoBarras codigoBarras = new CodigoBarras();
    CodigoOtros codigoOtros = new CodigoOtros();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnv = findViewById(R.id.navegador);
        bnv.setOnNavigationItemSelectedListener(mOnNavigationItemSelectdListener);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectdListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.qr:
                    loadFragment(codigoqr);
                    return true;
                case R.id.barras:
                    loadFragment(codigoBarras);
                    return true;
                case R.id.otros:
                    loadFragment(codigoOtros);
                    return true;

            }
            return  false;
        }
    };
    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragments, fragment);
        transaction.commit();
    }

}