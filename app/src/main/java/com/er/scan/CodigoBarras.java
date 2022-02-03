package com.er.scan;

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

public class CodigoBarras extends Fragment {
    TextView link;
    Button btnLeer;


    public CodigoBarras() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_codigo_barras, container, false);

        link = vista.findViewById(R.id.link2);
        btnLeer = vista.findViewById(R.id.btnScanBarras);


        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escanear();
            }
        });

        return vista;
    }

    public void escanear(){
        IntentIntegrator intent = IntentIntegrator.forSupportFragment(CodigoBarras.this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
        intent.setPrompt("ESCANEAR CÓDIGO DE BARRAS");
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
                Toast.makeText(getContext(), "Cancelaste el escaneo", Toast.LENGTH_LONG).show();
            }else{
                link.setText(result.getContents().toString());
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}