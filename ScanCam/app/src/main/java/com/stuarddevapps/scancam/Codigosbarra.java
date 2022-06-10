package com.stuarddevapps.scancam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Codigosbarra extends Fragment {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_codigosbarra, container, false);
    }
}