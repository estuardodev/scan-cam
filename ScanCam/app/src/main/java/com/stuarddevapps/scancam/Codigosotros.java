package com.stuarddevapps.scancam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Codigosotros extends Fragment {

    public Codigosotros() {
        // Required empty public constructor
    }

    public static Codigosotros newInstance() {
        
        Bundle args = new Bundle();
        
        Codigosotros fragment = new Codigosotros();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_codigosotros, container, false);
    }
}