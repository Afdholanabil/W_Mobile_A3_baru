package com.example.recyclick.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.recyclick.DataBarangActivity;
import com.example.recyclick.Koneksi.dbBarang;
import com.example.recyclick.R;
import com.example.recyclick.Util;
import com.example.recyclick.databinding.FragmentTambahBarangBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TambahBarangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TambahBarangFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    dbBarang db;
    public String nama, id, jenis, stok, deskripsi;
    public int stok2;

    public TambahBarangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TambahBarangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TambahBarangFragment newInstance(String param1, String param2) {
        TambahBarangFragment fragment = new TambahBarangFragment();
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
        db = new dbBarang(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTambahBarangBinding fragmentTambahBarangBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tambah_barang, container, false);
        fragmentTambahBarangBinding.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = fragmentTambahBarangBinding.idPrd.getText().toString();
                nama = fragmentTambahBarangBinding.namaPrd.getText().toString();
                jenis = fragmentTambahBarangBinding.jenisPrd.getText().toString();
                stok = String.valueOf(fragmentTambahBarangBinding.stokPrd.getText());
                stok2 = Integer.parseInt(stok);
                deskripsi = fragmentTambahBarangBinding.deskPrd.getText().toString();
                tambahBarang();
            }
        });
        fragmentTambahBarangBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), new DataBarangActivity().getClass()));
            }
        });

        return fragmentTambahBarangBinding.getRoot();

    }

    public void tambahBarang() {
        try {
            if (id.equals("") || nama.equals("") || jenis.equals("") || stok.equals("") || deskripsi.equals("")) {
                Toast.makeText(getActivity(), "data tidak boleh kosong", Toast.LENGTH_SHORT).show();
            } else {
                boolean insData = db.insertDataBarang(id, nama, jenis, stok2, deskripsi);
                if (insData == true) {
                    startActivity(new Intent(getActivity(), new DataBarangActivity().getClass()));
                    Toast.makeText(getActivity(), "Berhasil menambahkan barang", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "gagal menambahkan barang", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "kesalahan : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}