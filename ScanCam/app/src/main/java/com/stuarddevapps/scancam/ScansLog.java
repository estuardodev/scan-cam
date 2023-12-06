package com.stuarddevapps.scancam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ScansLog extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scans_log);

        AdsClass.loadAd(ScansLog.this);

        list = findViewById(R.id.list_view);
        list.setOnItemClickListener(this);

        RenderizarDatos();

        AdsClass.interstitialAd(getString(R.string.intersictial_logs), ScansLog.this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // Obtener el texto del elemento clicado
        String text = adapterView.getItemAtPosition(i).toString();

        // Mostrar el anuncio intersticial
        AdsClass.Mostrar(ScansLog.this);

        // Copiar el texto al portapapeles
        Utilidades.CopiarPortapapeles(ScansLog.this, text);
    }

    public void RenderizarDatos(){
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = openFileInput("pruebas.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
            list.setAdapter(mAdapter);

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Aquí, podrías agregar el elemento a tu adaptador
                mAdapter.add(line);
                stringBuilder.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.iMenu1:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.iMenu2:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ScansLog.this);
                alertDialog.setMessage("Eliminara todos los escaneos permanentemente.")
                        .setCancelable(false)
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Archivos archivos = new Archivos();
                                archivos.Vaciar(ScansLog.this,"pruebas.txt");

                                Intent i2 = new Intent(ScansLog.this, ScansLog.class);
                                startActivity(i2);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog titulo = alertDialog.create();
                        titulo.setTitle("¿Desea vaciar el historial?");
                        titulo.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}