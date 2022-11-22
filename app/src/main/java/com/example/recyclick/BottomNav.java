package com.example.recyclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.recyclick.Fragment.DataBarangFragment;
import com.example.recyclick.Fragment.HomeFragment;
import com.example.recyclick.Fragment.LaporanPenjualanFragment;
import com.example.recyclick.Fragment.PengaturanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomNav extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        bottomNavigationView = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DataBarangFragment()).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
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

                }getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            }
        });
    }
}