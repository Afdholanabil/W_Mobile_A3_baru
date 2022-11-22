package com.example.recyclick.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.recyclick.Adapter.barangAdapter;
import com.example.recyclick.Koneksi.dbBarang;
import com.example.recyclick.Model.brData;
import com.example.recyclick.R;
import com.example.recyclick.Util;
import com.example.recyclick.databinding.FragmentDataBarangBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataBarangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataBarangFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private List<brData> listdata = new ArrayList<>();
    dbBarang data;

    public DataBarangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataBarangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataBarangFragment newInstance(String param1, String param2) {
        DataBarangFragment fragment = new DataBarangFragment();
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
        data = new dbBarang(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDataBarangBinding fragmentDataBarangBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_data_barang,container,false);
        recyclerView = fragmentDataBarangBinding.rcyBarang;
        fragmentDataBarangBinding.btnAddBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.pindahFragment(getActivity().getSupportFragmentManager(), new TambahBarangFragment());
            }
        });
        showDataBarang();

        // Inflate the layout for this fragment
        return fragmentDataBarangBinding.getRoot();
    }
    public void showDataBarang(){
        brData barang = null;
        SQLiteDatabase db = data.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM barang", null);
//        String []daftar = new String[cr.getCount()];
        cr.moveToFirst();
        for(int i = 0; i< cr.getCount(); i++){
            cr.moveToPosition(i);
            String id = cr.getString(0).toString();
            String nama = cr.getString(1).toString();
            String kategori = cr.getString(2).toString();
            int stok = cr.getInt(3);
            String deskripsi = cr.getString(4).toString();
            barang = new brData(id, nama, kategori, deskripsi, stok);
            listdata.add(barang);
        }

        barangAdapter adapter = new barangAdapter(listdata);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    public void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        // set title dialog
        alertDialogBuilder.setTitle("Hapus Data Barang ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik Hapus untuk melanjutkan !")
                .setIcon(R.drawable.logo300x169)
                .setCancelable(false)
                .setPositiveButton("Hapus",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {


                    }
                })
                .setNegativeButton("Kembali",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

    }
