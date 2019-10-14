package com.example.mynjp.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mynjp.MainActivity;
import com.example.mynjp.R;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    // Variable
    public static final String ARG_NAME = "nama";
    public static final String ARG_ALAMAT = "alamat";
    private static final int GALLERY_REQUEST_CODE = 1;
    private static final String TAG = AboutFragment.class.getCanonicalName();
    private EditText nameText;
    private EditText alamatText;
    private ImageView avatar;
    private String name;
    private String alamat;


    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity mainActivity = (MainActivity)getActivity();
        name = mainActivity.getNama();
        alamat = mainActivity.getAlamat();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        // Bind layout
        nameText = view.findViewById(R.id.editnamatxt);
        alamatText = view.findViewById(R.id.editalamattxt);
        avatar = view.findViewById(R.id.profilImage);
        // Set Text edit nama & edit alamat
        nameText.setText(name);
        alamatText.setText(alamat);

        //  Todo = merubah foto
        ImageView profilImage = view.findViewById(R.id.profilImage);
        profilImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            }
        });

        //  Todo = menyimpan data user
        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    String namaTemp = nameText.getText().toString();
                    String alamatTemp = alamatText.getText().toString();
                    if(namaTemp.equals(name) && alamatTemp.equals(alamat)){
                        Toast.makeText(getActivity(), "Tidak ada perubahan data", Toast.LENGTH_SHORT).show();
                    }else{
                        mListener.onsaveButtonClicked(namaTemp,alamatTemp);
                        Toast.makeText(getActivity(), "Data berhail diupdate", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //  Todo = logout dari aplikasi
        Button logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mListener.onlogoutButtonCLicked();
            }
        });
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST_CODE){
            if(data!=null) {
                try{
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                    avatar.setImageBitmap(bitmap);
                }catch (IOException e){
                    Toast.makeText(getActivity(), "Can't Load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
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
        void onsaveButtonClicked(String nama, String alamat);
        void onlogoutButtonCLicked();
    }
}
