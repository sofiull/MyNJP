package com.example.mynjp.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynjp.MainActivity;
import com.example.mynjp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private String beratString;
    private int originLocation;
    private int destinationLocation;
    TextView origin_TV;
    TextView destination_TV;

    public RatesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        originLocation=((MainActivity) getActivity()).getOriginDistance();
        destinationLocation=((MainActivity) getActivity()).getDestinationDistance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_rates, container, false);
        // Bind
        final TextView berat = view.findViewById(R.id.weightInput);
        // Bind layout origin & destination Textview
        origin_TV=view.findViewById(R.id.origin_textView);
        destination_TV=view.findViewById(R.id.destination_textView);
        // Set origin & destination
        origin_TV.setText(((MainActivity) getActivity()).getOriginValue());
        destination_TV.setText(((MainActivity) getActivity()).getDestinationValue());

        // Todo = Pilih Origin
        TextView originTV = view.findViewById(R.id.origin_textView);
        originTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onOriginTVClicked();
            }
        });

        // Todo = Pilih Destination
        TextView destinationTV = view.findViewById(R.id.destination_textView);
        destinationTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDestinationTVClicked();
            }
        });

        //  Todo = check tarif kirim
        Button checkButton = view.findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
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
        void onOriginTVClicked();
        void onDestinationTVClicked();
        void oncheckButtonClicked(String berat,int originLocation,int destinationLocation);
        void onincreaseButtonClicked(String beratString);
        void ondecreaseButtonClicked(String beratString);
        void onresetButtonClicked();
    }

}
