package com.er.scan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent i = new Intent(this, Terminos.class);
                startActivity(i);
                return true;
            case R.id.item2:
                Intent ie = new Intent(this, Politicas.class);
                startActivity(ie);
                return true;
            case R.id.item3:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("Text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,getResources().getString(R.string.app_name));
                String aux = "Hey,descarga esta increíble aplicación.\n¡Descargala y dale 5 estrellas!\n";
                aux = aux +"https://play.google.com/store/apps/details?id=com.er.scan";
                intent.putExtra(intent.EXTRA_TEXT, aux);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}