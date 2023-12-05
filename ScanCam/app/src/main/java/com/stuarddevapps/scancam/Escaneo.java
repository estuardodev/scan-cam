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
    ImageView img;
    RelativeLayout rl;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaneo);
        enlace = findViewById(R.id.link4);
        secure = findViewById(R.id.txtSecure);
        btn = findViewById(R.id.btncopy);
        rl = findViewById(R.id.mys);


        FileOutputStream fileOutputStream = null;

        try {
            String textoASalvar = dato() + "\n";

            // Abre el archivo en modo MODE_APPEND para añadir contenido al final
            fileOutputStream = openFileOutput("pruebas.txt", MODE_APPEND);

            // Escribe la nueva línea en el archivo
            fileOutputStream.write(textoASalvar.getBytes());

            Log.d("TAG1", "Fichero salvado:" + getFilesDir() + "/" + "pruebas.txt");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        setAds();
        copy();
        banner();
        try {
            extraccion();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void extraccion() throws MalformedURLException {
        Bundle bundle = getIntent().getExtras();
        String datoenlace = bundle.getString("Info").toString();
        enlace.setText(datoenlace);
        URL url = new URL(datoenlace);
    }

    private String dato() throws MalformedURLException{
        Bundle bundle = getIntent().getExtras();
        String datoenlace = bundle.getString("Info").toString();
        return  datoenlace;
    }


    private void copy(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = enlace.getText().toString();
                Toast.makeText(Escaneo.this, getString(R.string.CopyScan), Toast.LENGTH_LONG).show();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text",  text);
                clipboard.setPrimaryClip(clip);

                if(mInterstitialAd!=null){
                    mInterstitialAd.show(Escaneo.this);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            mInterstitialAd = null;
                        }

                    });

                }

            }
        });

    }


    public void banner(){
        //ANUNCIOS

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    private void setAds(){
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,getString(R.string.intersictial_scan), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
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