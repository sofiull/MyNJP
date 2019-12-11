package com.example.mynjp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynjp.R;
import com.example.mynjp.model.Kota;

import java.util.List;

public class kotaAdapter extends RecyclerView.Adapter<kotaAdapter.MyViewHolder> {
    List<Kota> kotaList;

    public kotaAdapter(List<Kota> kotaList) {
        this.kotaList = kotaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View kotaView = layoutInflater.inflate(R.layout.item_kota,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(kotaView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Kota kota = kotaList.get(position);
        holder.namaKota.setText(kota.getNama());
    }

    @Override
    public int getItemCount() {
        return (kotaList!=null)?kotaList.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaKota;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            namaKota = itemView.findViewById(R.id.kota_textView);
        }
    }
}
