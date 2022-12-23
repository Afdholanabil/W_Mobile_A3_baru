package com.example.recyclick;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Model.Laporan.DataItemUtama;
import com.example.recyclick.Model.Laporan.LaporanLengkap;
import com.example.recyclick.Model.Laporan.LaporanUtama;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanActivity extends AppCompatActivity {
    private CardView card;
    public static DataBarangActivity dba;
    public LinearLayout layoutmenu;
    private int menuloop = 0;
    private BottomNavigationView navigationViewlp;
    APIRequestData API;
    List<DataItemUtama> item;
    TextView penjualan, dikirim, penjualanbulan, penjualantahun, textgraphmenu;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);
        deklarasiVariabel();
        showLaporan();

        moveFragment(new grafikgaris());
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaporanActivity.this, LaporanLengkapActivity.class));
            }
        });
        layoutmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                textgraphmenu.setWidth(290);
                switch (menuloop) {
                    case 0:
                        text = "Grafik Mingguan";
                        textgraphmenu.setText(text);
                        menuloop = 2;
                        break;
                    case 1:
                        text = "Grafik Mingguan";
                        textgraphmenu.setText(text);
                        moveFragment(new grafikgaris());
                        menuloop++;
                        break;
                    case 2:
                        text = "Grafik Bulanan";
                        textgraphmenu.setText(text);
                        moveFragment(new grafikbatang());
                        menuloop = 1;
                        break;
                }
            }
        });


        navigationViewlp.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_nav_home:
                        startActivity(new Intent(LaporanActivity.this, new HomeActivity().getClass()));
                        break;
                    case R.id.id_nav_edit:
                        startActivity(new Intent(LaporanActivity.this, new DataBarangActivity().getClass()));

                        break;
                    case R.id.id_nav_laporan:
                        startActivity(new Intent(LaporanActivity.this, new LaporanActivity().getClass()));
                        break;
//                        if (role == 1) {
//
//                        } else {
//                            Toast.makeText(LaporanActivity.this, "Anda Tidak Dapat Menggunakan Fitur Ini !", Toast.LENGTH_SHORT).show();
//                            break;
//                        }

                    case R.id.id_nav_setting:
                        startActivity(new Intent(LaporanActivity.this, new PengaturanActivity().getClass()));

                        break;
                }
                return false;
            }
        });




    }
    public void deklarasiVariabel(){
        penjualan = findViewById(R.id.laps_text1);
        dikirim = findViewById(R.id.laps_text2);
        penjualanbulan = findViewById(R.id.laps_text3);
        penjualantahun = findViewById(R.id.laps_text4);
        card = findViewById(R.id.laps_carddatalengkap);
        layoutmenu = findViewById(R.id.lp_layout1);
        textgraphmenu = findViewById(R.id.lp_textmenu);
        navigationViewlp = findViewById(R.id.nav_view_lp);
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
    }

    public void showLaporan(){
        Call<LaporanUtama> call = API.getInfoLaporanUtama();
        call.enqueue(new Callback<LaporanUtama>() {
            @Override
            public void onResponse(Call<LaporanUtama> call, Response<LaporanUtama> response) {
                if(response.isSuccessful() && response.body() != null){
                    Log.d("berhasil", "kode "+response.body().getKode()+" || message "+response.body().getMessage());
                    item = response.body().getData();
                    int nilai = item.get(0).getBarangTerjual();
                    int nilai2 = item.get(0).getBarangDiantar();
                    int nilai3 = item.get(0).getPendapatanSebulan();
                    int nilai4 = item.get(0).getPendapatanSetahun();

                    penjualan.setText(nilai+" barang");
                    dikirim.setText(nilai2+" barang");
                    penjualanbulan.setText("Rp "+nilai3);
                    penjualantahun.setText("Rp "+nilai4);
                }
            }

            @Override
            public void onFailure(Call<LaporanUtama> call, Throwable t) {
                View view = getLayoutInflater().inflate(R.layout.toast_no_internet, null);
                view.findViewById(R.id.toast_noConnection);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
                Log.d("error", "onFailure: "+t.getMessage());
            }
        });
    }

    private void moveFragment(Fragment frag){
        FragmentTransaction fragtran = getSupportFragmentManager().beginTransaction();
        fragtran.replace(R.id.framechart, frag).commit();
    }

}
