package com.stuarddevapps.scancam;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class Escaneo extends AppCompatActivity {
    TextView enlace, secure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaneo);
        enlace = findViewById(R.id.link4);
        secure = findViewById(R.id.txtSecure);

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
    }

    private void verify(URL url){
        if ("https".equals(url.getProtocol())) {
            secure.setText(getString(R.string.secure));
        } else if ("http".equals(url.getProtocol())) {
            secure.setText(getString(R.string.nosecure));
        }
    }


}