package com.stuarddevapps.scancam;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Codigosbarra extends Fragment {
    //Variables
    Button btnLeer;

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

        btnLeer = vista.findViewById(R.id.btnScanBarras);

        btnScan();

        return vista;
    }

    private void btnScan(){
        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               escanear();
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

}