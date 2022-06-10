package com.stuarddevapps.scancam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Escaneo extends AppCompatActivity {
    TextView enlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaneo);
        enlace = findViewById(R.id.link4);

        extraccion();
    }

    private void extraccion(){
        Bundle bundle = getIntent().getExtras();
        String datoenlace = bundle.getString("Info").toString();
        enlace.setText(datoenlace);
    }
}