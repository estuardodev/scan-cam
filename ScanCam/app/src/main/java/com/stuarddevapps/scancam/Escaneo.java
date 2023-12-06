package com.stuarddevapps.scancam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Escaneo extends AppCompatActivity {
    TextView enlace, secure;
    Button btn;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaneo);
        enlace = findViewById(R.id.link4);
        secure = findViewById(R.id.txtSecure);
        btn = findViewById(R.id.btncopy);
        rl = findViewById(R.id.mys);

        String TextoObtenido = Utilidades.ObtenerDatoIntent(Escaneo.this, "Info");
        enlace.setText(TextoObtenido);

        Archivos archivos = new Archivos();
        archivos.Escribir(Escaneo.this ,TextoObtenido, "pruebas.txt");

        AdsClass.loadAd(Escaneo.this);
        AdsClass.interstitialAd(getString(R.string.intersictial_scan), Escaneo.this);
        AdsClass.bannerAd(findViewById(R.id.adView));

        copy();
    }

    private void copy(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsClass.Mostrar(Escaneo.this);
                Utilidades.CopiarPortapapeles(Escaneo.this, Utilidades.ObtenerDatoIntent(Escaneo.this, "Info"));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itb1:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}