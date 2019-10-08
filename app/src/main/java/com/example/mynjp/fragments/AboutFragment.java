package com.example.mynjp.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mynjp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    public static final String ARG_NAME = "nama";
    public static final String ARG_ALAMAT = "alamat";
    private String name;
    private String alamat;


    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment newInstance(String name, String alamat) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_ALAMAT, alamat);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
            alamat = getArguments().getString(ARG_ALAMAT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        EditText nameText = view.findViewById(R.id.editnamatxt);
        EditText alamatText = view.findViewById(R.id.editalamattxt);
        nameText.setText(name);
        alamatText.setText(alamat);
        return view;
    }

}
