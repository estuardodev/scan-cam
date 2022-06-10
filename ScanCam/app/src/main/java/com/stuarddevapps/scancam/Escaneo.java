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
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.net.MalformedURLException;
import java.net.URL;

public class Escaneo extends AppCompatActivity {
    TextView enlace, secure;
    Button btn;
    ImageView img;
    RelativeLayout rl;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaneo);
        enlace = findViewById(R.id.link4);
        secure = findViewById(R.id.txtSecure);
        btn = findViewById(R.id.btncopy);
        img = findViewById(R.id.imageView);
        rl = findViewById(R.id.mys);

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
        verify(url);
        clip();
    }

    private void verify(URL url){
        if ("https".equals(url.getProtocol())) {
            secure.setText(getString(R.string.secure));
            img.setVisibility(View.VISIBLE);
            img.setImageResource(R.drawable.ic_verify);
            rl.setBackgroundResource(R.color.green_1);

        } else if ("http".equals(url.getProtocol())) {
            secure.setText(getString(R.string.nosecure));
            img.setVisibility(View.VISIBLE);
            img.setImageResource(R.drawable.ic_negative);
            rl.setBackgroundResource(R.color.other_6);
        }
    }


    private void clip(){
        Toast.makeText(Escaneo.this, getString(R.string.CopyScan), Toast.LENGTH_LONG).show();
        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", enlace.getText());
        clipboard.setPrimaryClip(clip);
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