package com.stuarddevapps.scancam;

import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class Codigosqr extends Fragment {
    //Variables
    Button btnLeer;

    public Codigosqr() {
        // Required empty public constructor
    }

    public static Codigosqr newInstance() {

        Bundle args = new Bundle();

        Codigosqr fragment = new Codigosqr();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_codigosqr, container, false);

        AdsClass.loadAd(getContext());
        AdsClass.interstitialAd(getString(R.string.intersictial_qr), getContext());

        btnLeer = vista.findViewById(R.id.btnScanQR);
        btnScan();
        return vista;
    }

    private void btnScan() {
        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                escanear();
                AdsClass.Mostrar(getContext());
            }
        });
    }

    public void escanear() {
        IntentIntegrator intent = IntentIntegrator.forSupportFragment(Codigosqr.this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setPrompt(getString(R.string.ScanQR));
        intent.setCameraId(0);
        intent.setBeepEnabled(true);
        intent.setOrientationLocked(false);
        intent.setCaptureActivity(CaptureActivityPortrait.class);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getContext(), getString(R.string.exitScan), Toast.LENGTH_LONG).show();
            } else {

                String envio = result.getContents().toString();
                Intent ie = new Intent(getContext(), Escaneo.class);
                ie.putExtra("Info", envio);

                startActivity(ie);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);

            

        }
    }




}