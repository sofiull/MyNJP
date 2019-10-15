package com.example.mynjp.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mynjp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private String beratString;

    public RatesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_rates, container, false);
        // Bind
        final TextView berat = view.findViewById(R.id.weightInput);
        final Spinner origin = view.findViewById(R.id.originInput);
        final Spinner destination = view.findViewById(R.id.destinationInput);

        //  Todo = check tarif kirim
        Button checkButton = view.findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    int lokasi[] = {5,4,3,2,1};
                    int originLocation = lokasi[origin.getSelectedItemPosition()];
                    int destinationLocation = lokasi[destination.getSelectedItemPosition()];
                    beratString = berat.getText().toString();
                    mListener.oncheckButtonClicked(beratString,originLocation,destinationLocation);
                }
            }
        });

        //  Todo = increment berat
        ImageView addButton = view.findViewById(R.id.addbutton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) {
                    beratString = berat.getText().toString();
                    mListener.onincreaseButtonClicked(beratString);
                }

            }
        });

        //  Todo = decrease berat
        ImageView decreaseButton = view.findViewById(R.id.decreaseButton);
        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) {
                    beratString = berat.getText().toString();
                    mListener.ondecreaseButtonClicked(beratString);
                }
            }
        });

        //  Todo = reset berat, dan tampilan biaya kirim
        Button resetButton = view.findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) {
                    mListener.onresetButtonClicked();
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void oncheckButtonClicked(String berat,int originLocation,int destinationLocation);
        void onincreaseButtonClicked(String beratString);
        void ondecreaseButtonClicked(String beratString);
        void onresetButtonClicked();
    }

}
