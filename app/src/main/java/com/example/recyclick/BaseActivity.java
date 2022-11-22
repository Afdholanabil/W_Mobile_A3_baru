package com.example.recyclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.recyclick.Fragment.BottomNavFragment;
import com.example.recyclick.Fragment.DataBarangFragment;
import com.example.recyclick.Fragment.DataKaryawanFragment;
import com.example.recyclick.Fragment.HomeFragment;
import com.example.recyclick.Fragment.LaporanPenjualanFragment;
import com.example.recyclick.Fragment.LoginFragment;
import com.example.recyclick.Fragment.PengaturanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        Intent intent = new Intent(BaseActivity.this, new HomeActivity().getClass());
        startActivity(intent);
        finish();
        hideActionBar();

    }

    public void hideActionBar(){
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }
    }
