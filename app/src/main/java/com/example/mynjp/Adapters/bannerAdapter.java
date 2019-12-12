package com.example.mynjp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynjp.R;
import com.example.mynjp.model.Banner;
import com.squareup.picasso.Picasso;

import java.util.List;

public class bannerAdapter extends RecyclerView.Adapter<bannerAdapter.MyViewHolder> {
    List<Banner> bannerList;

    public bannerAdapter(List<Banner> bannerList) {
        this.bannerList = bannerList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View bannerView = layoutInflater.inflate(R.layout.item_banner,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(bannerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Banner banner = bannerList.get(position);
        Picasso.get().load(banner.getLink()).fit().into(holder.bannerImage);
    }

    @Override
    public int getItemCount() {
        return (bannerList!=null)?bannerList.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView bannerImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerImage = itemView.findViewById(R.id.banner_imageview);
        }
    }
}
