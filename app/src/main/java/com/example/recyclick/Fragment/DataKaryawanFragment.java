package com.example.recyclick.Fragment;

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


import com.example.recyclick.Adapter.karyawanAdapter;

import com.example.recyclick.Koneksi.dbHelper;

import com.example.recyclick.Model.brKaryawan;
import com.example.recyclick.R;
import com.example.recyclick.Util;
import com.example.recyclick.databinding.FragmentDataKaryawanBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataKaryawanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataKaryawanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private List<brKaryawan> listdata = new ArrayList<>();
    dbHelper data;


    public DataKaryawanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataKaryawanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataKaryawanFragment newInstance(String param1, String param2) {
        DataKaryawanFragment fragment = new DataKaryawanFragment();
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
        data = new dbHelper(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDataKaryawanBinding fragmentDataKaryawanBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_data_karyawan,container,false);
        recyclerView = fragmentDataKaryawanBinding.rcyKaryawan;
        fragmentDataKaryawanBinding.btnAddKaryawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.pindahFragment(getActivity().getSupportFragmentManager(), new TambahKaryawanFragment());
            }
        });
        fragmentDataKaryawanBinding.txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.pindahFragment(getActivity().getSupportFragmentManager(), new BottomNavFragment());
            }
        });
        showDataKaryawan();
        // Inflate the layout for this fragment
        return fragmentDataKaryawanBinding.getRoot();
    }

    public void showDataKaryawan(){
        brKaryawan karyawan = null;
        SQLiteDatabase db = data.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM Admin",null);
        cr.moveToFirst();
        for(int i =0;i<cr.getCount();i++){
            cr.moveToPosition(i);
            String username = cr.getString(0).toString();
            String pass = cr.getString(1).toString();
            String nama = cr.getString(2).toString();
            int noTelp = cr.getInt(3);
            int kedudukan = cr.getInt(4);

            karyawan = new brKaryawan(username,pass,nama,noTelp,kedudukan);
            listdata.add(karyawan);
        }

        karyawanAdapter adapter = new karyawanAdapter(listdata);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}