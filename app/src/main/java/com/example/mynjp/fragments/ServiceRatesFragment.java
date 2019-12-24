package com.example.mynjp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynjp.Adapters.kotaAdapter;
import com.example.mynjp.MainActivity;
import com.example.mynjp.R;
import com.example.mynjp.model.Kota;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceRatesFragment extends Fragment {

    private RecyclerView rvKota;
    private DatabaseReference mdatabase;
    private String status;
    List<Kota> kotaList = new ArrayList<>();

    public ServiceRatesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity mainActivity = (MainActivity)getActivity();
        this.status=mainActivity.getStatus();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service_rates, container, false);
        rvKota = view.findViewById(R.id.rvTextView);
        TextView statusTV = view.findViewById(R.id.purposeStatus_Textview);
        statusTV.setText(status);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("kota");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Kota kota = postSnapshot.getValue(Kota.class);
                    kotaList.add(kota);
//                    Log.d("nama Provinsi",kota.getProvinsi());
                }
                kotaAdapter kotaAdapter = new kotaAdapter(kotaList);
                rvKota.setAdapter(kotaAdapter);
                rvKota.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
