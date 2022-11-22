package com.example.recyclick.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclick.PengaturanActivity;
import com.example.recyclick.R;
import com.example.recyclick.Util;
import com.example.recyclick.databinding.FragmentBottomNavBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottomNavFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomNavFragment extends Fragment {
    BottomNavigationView bottomNavigationView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BottomNavFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BottomNavFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BottomNavFragment newInstance(String param1, String param2) {
        BottomNavFragment fragment = new BottomNavFragment();
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentBottomNavBinding fragmentBottomNavBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_nav,container,false);
        Util.pindahFragmentNav(getActivity().getSupportFragmentManager(), new HomeFragment());
        fragmentBottomNavBinding.navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.id_nav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.id_nav_edit:
                        selectedFragment = new DataBarangFragment();
                        break;
                    case R.id.id_nav_laporan:
                        selectedFragment = new LaporanPenjualanFragment();
                        break;
                    case R.id.id_nav_setting:
                        selectedFragment = new PengaturanFragment();
                        break;

                }
                Util.pindahFragmentNav(getActivity().getSupportFragmentManager(), selectedFragment);
                return true;
            }
        });
        // Inflate the layout for this fragment
        return fragmentBottomNavBinding.getRoot();
    }
}