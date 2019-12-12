package com.example.mynjp.fragments;
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
import android.widget.Toast;

import com.example.mynjp.MainActivity;
import com.example.mynjp.R;
import com.example.mynjp.Adapters.bannerAdapter;
import com.example.mynjp.model.Banner;
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
public class HomeFragment extends Fragment {

    private String name;
    private RecyclerView rvBanner;
    private DatabaseReference mdatabase;
    List<Banner> bannerList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MainActivity mainActivity = (MainActivity)getActivity();

        name = mainActivity.getNama();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvBanner=view.findViewById(R.id.rvImage);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("banner");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Banner banner = postSnapshot.getValue(Banner.class);
                    bannerList.add(banner);
                    Log.d("nama", banner.getNama());
                    Log.d("image:",banner.getLink());
                }
                bannerAdapter banneradapter = new bannerAdapter(bannerList);
                rvBanner.setAdapter(banneradapter);
                rvBanner.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        Banner banner = new Banner("ikan","https://raw.githubusercontent.com/sofiull/MyAsset/master/ikan.jpg");
//        bannerList.add(banner);
//        banner = new Banner("ikan","https://raw.githubusercontent.com/sofiull/MyAsset/master/ikan.jpg");
//        bannerList.add(banner);

        return view;
    }
}
