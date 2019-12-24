package com.example.mynjp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynjp.MainActivity;
import com.example.mynjp.R;
import com.example.mynjp.fragments.RatesFragment;
import com.example.mynjp.model.Kota;

import java.util.List;

public class kotaAdapter extends RecyclerView.Adapter<kotaAdapter.MyViewHolder> {
    List<Kota> kotaList;
    public Context context;

    public kotaAdapter(List<Kota> kotaList) {
        this.kotaList = kotaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View kotaView = layoutInflater.inflate(R.layout.item_kota,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(kotaView);
        return myViewHolder;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Kota kota = kotaList.get(position);
        final MainActivity mainActivity = new MainActivity();
        holder.namaKota.setText(kota.getNama());
        holder.namaProvinsi.setText(kota.getProvinsi());

        holder.namaKota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), kota.getNama()+" "+kota.getProvinsi(), Toast.LENGTH_SHORT).show();
                if(mainActivity.getStatus()=="Origin") {
                    sendMessage(kota.getNama(),kota.getJarak());
                }else{
                    sendMessage(kota.getNama(),kota.getJarak());
                }
            }
        });
    }

    private void sendMessage(String kota,int jarak) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("data-kota");

        intent.putExtra("kota", kota);
        intent.putExtra("jarak", jarak);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return (kotaList!=null)?kotaList.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaKota;
        public TextView namaProvinsi;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            namaKota = itemView.findViewById(R.id.kota_textView);
            namaProvinsi = itemView.findViewById(R.id.purposeStatus_Textview);
        }
    }
}
