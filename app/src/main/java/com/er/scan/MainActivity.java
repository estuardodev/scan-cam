package com.er.scan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
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
                case R.id.barras:
                    Toast.makeText(getApplicationContext(), "Si", Toast.LENGTH_LONG).show();
                case R.id.otros:
                    Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG).show();

            }
            return  false;
        }
    };


}