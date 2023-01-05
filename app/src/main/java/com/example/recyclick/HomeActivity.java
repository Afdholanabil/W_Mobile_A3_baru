package com.example.recyclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Adapter.AdapterBarangLaris;
import com.example.recyclick.Adapter.AdapterDataBarang;
import com.example.recyclick.Adapter.AdapterInfoPemesanan;
import com.example.recyclick.Adapter.AdapterKategori;
import com.example.recyclick.Adapter.AdapterSearch;
import com.example.recyclick.Koneksi.dbHelper;
import com.example.recyclick.Model.DataBarang.BarangGetInfo;
import com.example.recyclick.Model.DataBarang.BarangLaris;
import com.example.recyclick.Model.DataBarang.BarangLarisInfo;
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
    private TextView greeting,tLihatProduk;
    TextView lihatPemesanan;
    public dbHelper dbhelp;
    BottomNavigationView navigationView;
    CardView lay1,imgPrf;
    private EditText srch;
    ImageView img;
    private RecyclerView recyclerView1, recyclerView2,recyclerView3;
    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView.LayoutManager lymanager;
    private List<KategoriItem> listdata = new ArrayList<>();
    private List<transaksiData> listdata2 = new ArrayList<>();
    private List<DataItem> listdata3 = new ArrayList<>();
    public static HomeActivity dba;
    APIRequestData API;
    String pesan;
    int kata;
    public int role;
    public PopupWindow pop;
    private List<DataItemSearch> item = new ArrayList<>();
    private ScrollView scroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        scroll = findViewById(R.id.ach_scroll);
        dbhelp = new dbHelper(getApplicationContext());
        greeting = (TextView) findViewById(R.id.textHalo);
        lay1 = findViewById(R.id.home_cardsearch);
        role = SaveAccount.readDataPembeli(HomeActivity.this).getUserRole();
        lihatPemesanan = findViewById(R.id.lihatPemesanan);
        lihatPemesanan.setBackground(null);
        navigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        tLihatProduk = findViewById(R.id.tLihat);
        tLihatProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, DataBarangActivity.class));
            }
        });

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_search, null);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        pop = new PopupWindow(popupView, width, height, false);
        img = findViewById(R.id.prfHome);
        imgPrf = findViewById(R.id.iconAkun);


        String gambarProf =(SaveAccount.readDataPembeli(HomeActivity.this).getPhoto());
//        Toast.makeText(this,gambarProf, Toast.LENGTH_SHORT).show();
        Glide.with(getApplicationContext()).load("https://workshopjti.com/RecyclickA3/"+gambarProf).thumbnail(0.5f).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.photo_library_48px).into(img);

        srch = findViewById(R.id.home_textsearch);
        srch.clearFocus();
        srch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String kata = srch.getText().toString();
                showPencarian(srch ,popupView, kata, i);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);

            lihatPemesanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(role == 1){
                        startActivity(new Intent(HomeActivity.this, new DataPemesananActivity().getClass()));
                    }else{
                        Toast.makeText(HomeActivity.this, "Fitur Tidak Tersedia !", Toast.LENGTH_SHORT).show();
                        lihatPemesanan.setOnClickListener(null);

                    }

                }
            });



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
                            Toast.makeText(HomeActivity.this, "Anda Tidak Dapat Menggunakan Fitur Ini !", Toast.LENGTH_SHORT).show();
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

        lymanager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(lymanager);
        tampilKategori();

        recyclerView2 = (RecyclerView) findViewById(R.id.rcy_infoPemesanan);

        lymanager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(lymanager);
        tampilInfoPemesanan();

        recyclerView3 = (RecyclerView) findViewById(R.id.rcy_barangLaris);

        recyclerView3.setLayoutManager(new GridLayoutManager(HomeActivity.this,3));
        recyclerView3.setHasFixedSize(true);

        tampilBarangLaris();

        scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                pop.dismiss();
            }
        });

    }


    public void setGreeting() {
        //set waktu
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);

        if (hour <= 6 || hour <= 11) {
            waktu = " Pagi ";
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
                View view = getLayoutInflater().inflate(R.layout.toast_no_internet, null);
                view.findViewById(R.id.toast_noConnection);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);

            }
        });

    }

    public void tampilBarangLaris() {
        Call<BarangGetInfo> call = API.getBarangLaris();
        call.enqueue(new Callback<BarangGetInfo>() {
            @Override
            public void onResponse(retrofit2.Call<BarangGetInfo> call, Response<BarangGetInfo> response) {
                pesan = response.body().getMessage();
                listdata3 = response.body().getData();
                adapter2 = new AdapterBarangLaris(HomeActivity.this, listdata3);
                recyclerView3.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();

            }

            @Override
            public void onFailure(retrofit2.Call<BarangGetInfo> call, Throwable t) {
                View view = getLayoutInflater().inflate(R.layout.toast_no_internet, null);
                view.findViewById(R.id.toast_noConnection);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
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
                View view = getLayoutInflater().inflate(R.layout.toast_no_internet, null);
                view.findViewById(R.id.toast_noConnection);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
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
                    AdapterSearch adapter = new AdapterSearch(HomeActivity.this,item);
                    recy.setAdapter(adapter);
                    recy.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                }else{
                    Log.d("gagal", "onResponse: "+response.body().getKode()+" pesan "+response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<SearchGetInfo> call, Throwable t) {
                Log.d("error", "onFailure: "+t.getMessage());
                View view = getLayoutInflater().inflate(R.layout.toast_no_internet, null);
                view.findViewById(R.id.toast_noConnection);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
            }
        });

        if (nilai > 0 ) {
            pop.showAtLocation(view, Gravity.TOP | Gravity.LEFT, location.left, location.bottom);
        } else {
            pop.dismiss();
        }
    }
    private void showLogOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this, R.style.AlertDialog);
        View view = LayoutInflater.from(HomeActivity.this).inflate(
                R.layout.layout_logout_dialog,(ConstraintLayout)findViewById(R.id.layoutDialogContainer));
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textJudul)).setText("Apakah Yakin Untuk LogOut ?");
        ((TextView) view.findViewById(R.id.textMessage)).setText("Klik Logout untuk Logout");
        ((Button) view.findViewById(R.id.btnOut)).setText("LogOut");
        ((Button) view.findViewById(R.id.btnKembali)).setText("Kembali");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_help_24);

        AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.btnOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, new LoginActivity().getClass());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.btnKembali).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
@Override
    public void onBackPressed(){
        showLogOutDialog();
    }
}
