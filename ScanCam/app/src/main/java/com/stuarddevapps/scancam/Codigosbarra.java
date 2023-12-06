package com.stuarddevapps.scancam;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
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

public class Codigosbarra extends Fragment {
    //Variables
    Button btnLeer;
    private InterstitialAd mInterstitialAd;

    public Codigosbarra() {
        // Required empty public constructor
    }

    public static Codigosbarra newInstance() {

        Bundle args = new Bundle();

        Codigosbarra fragment = new Codigosbarra();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_codigosbarra, container, false);

        // Anuncios
        AdsClass.loadAd(getContext());
        AdsClass.interstitialAd(getString(R.string.intersictial_bar), getContext());

        Animacion(vista);

        btnLeer = vista.findViewById(R.id.btnScanBarras);
        btnScan();

        return vista;
    }

    private void btnScan(){
        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               escanear();
                AdsClass.Mostrar(getContext());
            }
        });
    }

    public void escanear(){
        IntentIntegrator intent = IntentIntegrator.forSupportFragment(Codigosbarra.this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
        intent.setPrompt(getString(R.string.ScanBarcode));
        intent.setCameraId(0);
        intent.setBeepEnabled(true);
        intent.setOrientationLocked(false);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data);
        if (result != null)
        {
            if (result.getContents() ==  null)
            {
                Toast.makeText(getContext(), getString(R.string.exitScan), Toast.LENGTH_LONG).show();
            }else{
                String envio = result.getContents().toString();
                Intent ie = new Intent(getContext(), Escaneo.class);
                ie.putExtra("Info",envio);
                startActivity(ie);

            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void Animacion(View vista){
        // Suponiendo que estás dentro de un Fragment o Activity
        LottieAnimationView animationView = vista.findViewById(R.id.animation_view1);

        // Duración de la animación en milisegundos
        int durationForward = 5000;
        int durationBackward = 1000;

        // Obtén la anchura de la pantalla
        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        // Crea la animación de traslación de izquierda a derecha
        ObjectAnimator forwardAnimator = ObjectAnimator.ofFloat(
                animationView,
                "translationX",
                -screenWidth,
                screenWidth
        );

        // Establece la duración de la animación
        forwardAnimator.setDuration(durationForward);

        // Crea la animación de rotación de 180 grados en modo espejo
        ObjectAnimator backwardAnimator = ObjectAnimator.ofFloat(
                animationView,
                "rotationY",
                180
        );

        // Establece la duración de la animación
        backwardAnimator.setDuration(durationBackward);

        // Configura el AnimatorSet para ejecutar ambas animaciones
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(forwardAnimator, backwardAnimator);

        // Agrega un listener para reiniciar la animación cuando termine
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Reinicia la animación después de que termine la secuencia
                animatorSet.start();
            }
        });

        // Inicia la animación
        animatorSet.start();

    }

}