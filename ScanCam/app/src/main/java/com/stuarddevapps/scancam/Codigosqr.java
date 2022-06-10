package com.stuarddevapps.scancam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Codigosqr extends Fragment {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_codigosqr, container, false);
    }
}