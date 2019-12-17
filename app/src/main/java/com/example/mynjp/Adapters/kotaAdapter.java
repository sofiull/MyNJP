package com.example.mynjp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynjp.MainActivity;
import com.example.mynjp.R;
import com.example.mynjp.fragments.RatesFragment;
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
        final Kota kota = kotaList.get(position);
        final MainActivity mainActivity = new MainActivity();
        holder.namaKota.setText(kota.getNama());
        holder.namaProvinsi.setText(kota.getProvinsi());

        holder.namaKota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), kota.getNama()+" "+kota.getProvinsi(), Toast.LENGTH_SHORT).show();
                TextView origin = v.findViewById(R.id.origin_textView);
                TextView destination = v.findViewById(R.id.destination_textView);

                RatesFragment ratesFragment = new RatesFragment();
//                if(ratesFragment.getStatus()==1) {
//                    ratesFragment.setOriginLocation(kota.getJarak());
//                    origin.setText(kota.getNama());
//                }else{
//                    ratesFragment.setDestinationLocation(kota.getJarak());
//                    destination.setText(kota.getNama());
//                }
            }
        });
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
