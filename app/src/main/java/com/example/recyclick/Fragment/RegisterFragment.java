package com.example.recyclick.Fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.recyclick.Koneksi.dbHelper;
import com.example.recyclick.R;
import com.example.recyclick.Util;
import com.example.recyclick.databinding.FragmentRegisterBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    dbHelper db;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        db = new dbHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentRegisterBinding fragmentRegisterBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_register,container,false);
        fragmentRegisterBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.pindahFragment(getActivity().getSupportFragmentManager(), new LoginFragment());
            }
        });
        fragmentRegisterBinding.cardview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = fragmentRegisterBinding.inputText.getText().toString();
                String noTlp = fragmentRegisterBinding.inputText2.getText().toString();
                String usr = fragmentRegisterBinding.inputText3.getText().toString();
                String pass = fragmentRegisterBinding.inputText4.getText().toString();
                String repass = fragmentRegisterBinding.inputText5.getText().toString();
                String kedudukan = "karyawan";

                if(nama.equals("")||noTlp.equals("")||usr.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(getActivity(), "Data Kosong", Toast.LENGTH_LONG).show();
                }else {
                    if (usr.length() > 15) {
                        Toast.makeText(getActivity(), "Username Tidak Boleh lebih dari 15 karakter", Toast.LENGTH_SHORT).show();
                    }else if (pass.length() > 10) {
                        Toast.makeText(getActivity(), "Password tidak boleh lebih dari 10 karakter", Toast.LENGTH_SHORT).show();

                    }else if (pass.equals(repass)) {
                        boolean checkuser = db.checkUsername(usr);
                            if (checkuser == false) {
                                boolean insert = db.insertData(usr, pass, nama, noTlp, kedudukan);
                                if (insert == true) {
                                    Util.pindahFragment(getActivity().getSupportFragmentManager(), new LoginFragment());
                                    Toast.makeText(getActivity(), "Berhasil Daftar", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(getActivity(), "Gagal Daftar", Toast.LENGTH_LONG).show();
                                    Util.pindahFragment(getActivity().getSupportFragmentManager(), new RegisterFragment());
                                }
                            } else {
                                Toast.makeText(getActivity(), "username sudah digunakan", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Masukan Password yang Sesuai", Toast.LENGTH_LONG).show();

                        }

                }
            }
        });
        fragmentRegisterBinding.tv33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.pindahFragment(getActivity().getSupportFragmentManager(), new LoginFragment());
            }
        });
        Util.setCustomColorText(fragmentRegisterBinding.tv33,"Sudah Punya Akun? ","Masuk","76BA99");
        // Inflate the layout for this fragment
        return fragmentRegisterBinding.getRoot();



    }
}