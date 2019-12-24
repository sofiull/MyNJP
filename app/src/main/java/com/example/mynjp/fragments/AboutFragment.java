package com.example.mynjp.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mynjp.MainActivity;
import com.example.mynjp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    // Variable
    private EditText nameText;
    private EditText usernameText;
    private EditText alamatText;
    private EditText passwordText;
    private EditText repasswordText;
    private String name;
    private String alamat;
    private String username;
    private String password;
    private Uri imageUrl;


    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity mainActivity = (MainActivity)getActivity();
        name = mainActivity.getNama();
        alamat = mainActivity.getAlamat();
        username = mainActivity.getUsername();
        password = mainActivity.getPassword();
        imageUrl= mainActivity.getImageUrl();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        // Todo: Bind layout
        nameText = view.findViewById(R.id.editnamatxt);
        usernameText = view.findViewById(R.id.text_username);
        alamatText = view.findViewById(R.id.editalamattxt);
        passwordText = view.findViewById(R.id.editPassword);
        repasswordText = view.findViewById(R.id.editRePassword);
        ImageView avatar = view.findViewById(R.id.profilImage);
        // Todo: Set Text
        nameText.setText(name);
        alamatText.setText(alamat);
        usernameText.setText(username);
        passwordText.setText(password);
        // Todo: set avatar Image
        if(imageUrl!=null){
            avatar.setImageURI(imageUrl);
        }

        //  Todo = merubah foto
        ImageView profilImage = view.findViewById(R.id.profilImage);
        profilImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mListener.onchangeAvatarButtonClicked();
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
                    String usernameTemp = usernameText.getText().toString();
                    String passwordTemp = passwordText.getText().toString();
                    MainActivity mainActivity = (MainActivity)getActivity();
                    Uri imageTemp = mainActivity.getImageUrlTemp();

                    if(checkDiffrent(namaTemp, alamatTemp, usernameTemp, passwordTemp, imageTemp)){
                        Toast.makeText(getActivity(), "Tidak ada perubahan data", Toast.LENGTH_SHORT).show();
                    }else{
                        mListener.onsaveButtonClicked(namaTemp,alamatTemp, usernameTemp, passwordTemp);
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

    public boolean checkDiffrent(String nameTemp, String alamatTemp, String usernameTemp, String passwordTemp, Uri imageTemp){
        if(nameTemp.equals(name) && alamatTemp.equals(alamat) && usernameTemp.equals(username) && passwordTemp.equals(password) && imageUrl==imageTemp){
            return true;
        }else{
            return false;
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
        void onchangeAvatarButtonClicked();
        void onsaveButtonClicked(String nama, String alamat, String username, String password);
        void onlogoutButtonCLicked();
    }
}
