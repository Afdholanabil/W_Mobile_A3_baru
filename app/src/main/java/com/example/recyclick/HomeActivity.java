package com.example.recyclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Adapter.AdapterDataBarang;
import com.example.recyclick.Adapter.AdapterInfoPemesanan;
import com.example.recyclick.Adapter.AdapterKategori;
import com.example.recyclick.Adapter.AdapterSearch;
import com.example.recyclick.Koneksi.dbHelper;
import com.example.recyclick.Model.DataBarang.DataItem;
import com.example.recyclick.Model.DataKaryawan.KaryawanItem;
import com.example.recyclick.Model.Home.DataItemSearch;
import com.example.recyclick.Model.Home.SearchGetInfo;
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
    String waktu, j;
    Cursor cursor;
    private TextView greeting;
    TextView lihatPemesanan;
    public dbHelper dbhelp;
    BottomNavigationView navigationView;
    CardView lay1;
    private EditText srch;
    private RecyclerView recyclerView1, recyclerView2;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lymanager;
    private List<KategoriItem> listdata = new ArrayList<>();
    private List<transaksiData> listdata2 = new ArrayList<>();
    public static HomeActivity dba;
    APIRequestData API;
    String pesan;
    int kata;
    public int role;
    public PopupWindow pop;
    private List<DataItemSearch> item = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbhelp = new dbHelper(getApplicationContext());
        greeting = (TextView) findViewById(R.id.textHalo);
        lay1 = findViewById(R.id.home_cardsearch);
        role = SaveAccount.readDataPembeli(HomeActivity.this).getUserRole();
        lihatPemesanan = findViewById(R.id.lihatPemesanan);
        lihatPemesanan.setBackground(null);
        navigationView = (BottomNavigationView) findViewById(R.id.nav_view);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_search, null);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        pop = new PopupWindow(popupView, width, height, false);

        srch = findViewById(R.id.home_textsearch);
        srch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String kata = srch.getText().toString();
                showPencarian(srch,popupView, kata, i);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);

        if (role == 2) {
            lihatPemesanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(HomeActivity.this, new DataPemesananActivity().getClass()));
                }
            });
        } else {
            lihatPemesanan.setOnClickListener(null);
        }


        setGreeting();
        greeting.setText("Selamat" + waktu + SaveAccount.readDataPembeli(HomeActivity.this).getNama());

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_nav_home:
                        startActivity(new Intent(HomeActivity.this, new HomeActivity().getClass()));
                        break;
                    case R.id.id_nav_edit:
                        startActivity(new Intent(HomeActivity.this, new DataBarangActivity().getClass()));

                        break;
                    case R.id.id_nav_laporan:

                        if (role == 1) {
                            startActivity(new Intent(HomeActivity.this, new LaporanActivity().getClass()));
                            break;
                        } else {
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
        lymanager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(lymanager);
        tampilKategori();

        recyclerView2 = (RecyclerView) findViewById(R.id.rcy_infoPemesanan);
        dba = this;
        lymanager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
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

    public void tampilKategori() {
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
                Log.e("Error", String.valueOf(kata));

            }

            @Override
            public void onFailure(retrofit2.Call<KategoriInfo> call, Throwable t) {

            }
        });

    }

    public void tampilInfoPemesanan() {
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


            }

            @Override
            public void onFailure(retrofit2.Call<transaksiInfo> call, Throwable t) {

            }
        });

    }

    private void showPencarian(View view, View view1, String dicari, int nilai){

        int[] loc_int = new int[2];
        lay1.getLocationOnScreen(loc_int);
        Rect location = new Rect();
        location.left = loc_int[0];
        location.top = loc_int[1];
        location.right = location.left + lay1.getWidth();
        location.bottom = location.top + lay1.getHeight();

        RecyclerView recy = view1.findViewById(R.id.popuprcyhome);
        Call<SearchGetInfo> call = API.getInfoSearch(dicari);
        call.enqueue(new Callback<SearchGetInfo>() {
            @Override
            public void onResponse(Call<SearchGetInfo> call, Response<SearchGetInfo> response) {
                if(response.body() != null && response.isSuccessful()){
                    item = response.body().getData();
                    AdapterSearch adapter = new AdapterSearch(item);
                    recy.setAdapter(adapter);
                    recy.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                }else{
                    Log.d("gagal", "onResponse: "+response.body().getKode()+" pesan "+response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<SearchGetInfo> call, Throwable t) {
                Log.d("error", "onFailure: "+t.getMessage());
            }
        });

        if (nilai > 0) {
            pop.showAtLocation(view, Gravity.TOP | Gravity.LEFT, location.left, location.bottom);
        } else {
            pop.dismiss();
        }
    }
}
