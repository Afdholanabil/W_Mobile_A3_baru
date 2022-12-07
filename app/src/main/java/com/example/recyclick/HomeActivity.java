package com.example.recyclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Adapter.AdapterDataBarang;
import com.example.recyclick.Adapter.AdapterInfoPemesanan;
import com.example.recyclick.Adapter.AdapterKategori;
import com.example.recyclick.Koneksi.dbHelper;
import com.example.recyclick.Model.DataBarang.DataItem;
import com.example.recyclick.Model.DataKaryawan.KaryawanItem;
import com.example.recyclick.Model.Kategori.KategoriInfo;
import com.example.recyclick.Model.Kategori.KategoriItem;
import com.example.recyclick.Model.Login.LoginData;
import com.example.recyclick.Model.Transaksi.transaksiData;
import com.example.recyclick.Model.Transaksi.transaksiInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    String waktu,j;
    Cursor cursor;
    private TextView greeting;
    TextView lihatPemesanan;
    public dbHelper dbhelp;
    BottomNavigationView navigationView;
    LoginData loginData;
    View view;
    private RecyclerView recyclerView1,recyclerView2;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lymanager;
    private List<KategoriItem> listdata = new ArrayList<>();
    private List<transaksiData> listdata2 = new ArrayList<>();
    public static HomeActivity dba;
    APIRequestData API;
    String pesan;
    int kata;

public int role;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbhelp = new dbHelper(getApplicationContext());
        greeting = (TextView)findViewById(R.id.textHalo);

        role = SaveAccount.readDataPembeli(HomeActivity.this).getUserRole();
        Log.d(String.valueOf(role), "onCreate: ");
        lihatPemesanan = findViewById(R.id.lihatPemesanan);
        lihatPemesanan.setBackground(null);
        navigationView = (BottomNavigationView) findViewById(R.id.nav_view);

        if(role == 2){
            lihatPemesanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(HomeActivity.this, new DataPemesananActivity().getClass()));

                }
            });
        }else {
            lihatPemesanan.setOnClickListener(null);


        }




        setGreeting();
        greeting.setText("Selamat" + waktu + SaveAccount.readDataPembeli(HomeActivity.this).getNama());

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.id_nav_home:
                        startActivity(new Intent(HomeActivity.this, new HomeActivity().getClass()));
                        break;
                    case R.id.id_nav_edit:
                        startActivity(new Intent(HomeActivity.this, new DataBarangActivity().getClass()));

                        break;
                    case R.id.id_nav_laporan:

                        if(role == 1){
                            startActivity(new Intent(HomeActivity.this, new LoginActivity().getClass()));
                            break;
                        }else {
                            break;
                        }

                    case R.id.id_nav_setting:
                        startActivity(new Intent(HomeActivity.this, new PengaturanActivity().getClass()));

                        break;
                }
                return false;
            }
        });

        //RecycleView
        recyclerView1 = (RecyclerView) findViewById(R.id.rcy_kategori);
        dba = this;
        lymanager = new LinearLayoutManager(HomeActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(lymanager);
        tampilKategori();

        recyclerView2 = (RecyclerView) findViewById(R.id.rcy_infoPemesanan);
        dba = this;
        lymanager = new LinearLayoutManager(HomeActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(lymanager);
        tampilInfoPemesanan();

    }


        public void setGreeting() {
            //set waktu
            Calendar now = Calendar.getInstance();
            int hour = now.get(Calendar.HOUR_OF_DAY);
            System.out.println(hour);

            if (hour <= 6 || hour <= 11) {
                waktu = " pagi ";
            } else if (hour <= 17) {
                waktu = " Siang ";
            } else if (hour <= 24) {
                waktu = " Malam ";
            }
        }

        public void tampilKategori(){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
            Call<KategoriInfo> call = API.getKategoriData();
            call.enqueue(new Callback<KategoriInfo>() {
                @Override
                public void onResponse(retrofit2.Call<KategoriInfo> call, Response<KategoriInfo> response) {
                    pesan = response.body().getPesan();
                    listdata = response.body().getData();
                    adapter = new AdapterKategori(HomeActivity.this, listdata);
                    recyclerView1.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    kata = listdata.size();
                    Log.e("Error",String.valueOf(kata));

                }
                @Override
                public void onFailure(retrofit2.Call<KategoriInfo> call, Throwable t) {

                }
            });

        }
        public void tampilInfoPemesanan(){
            API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
            Call<transaksiInfo> call = API.getTransaksiStatusData();
            call.enqueue(new Callback<transaksiInfo>() {
                @Override
                public void onResponse(retrofit2.Call<transaksiInfo> call, Response<transaksiInfo> response) {
                    pesan = response.body().getMessage();
                    listdata2 = response.body().getData();
                    adapter = new AdapterInfoPemesanan(HomeActivity.this, listdata2);
                    recyclerView2.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    kata = listdata2.size();
                    Log.e("Error",String.valueOf(kata));

                }
                @Override
                public void onFailure(retrofit2.Call<transaksiInfo> call, Throwable t) {

                }
            });

        }
    }
