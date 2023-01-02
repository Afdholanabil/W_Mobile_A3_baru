package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Adapter.AdapterDataBarang;
import com.example.recyclick.Adapter.AdapterDataPemesanan;
import com.example.recyclick.Adapter.AdapterInfoPemesanan;
import com.example.recyclick.Model.DataBarang.BarangGetInfo;
import com.example.recyclick.Model.DataBarang.EditBarang;
import com.example.recyclick.Model.Pembeli.EditPembeli;
import com.example.recyclick.Model.Pembeli.PembeliData;
import com.example.recyclick.Model.Transaksi.transaksiData;
import com.example.recyclick.Model.Transaksi.transaksiInfo;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPemesananActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lymanager;
    private List<transaksiData> listdata = new ArrayList<>();
    private List<PembeliData> listdata2 = new ArrayList<>();
    public static DataPemesananActivity dba;
    APIRequestData API;
    String pesan;
    TextView btnBack;
    TextInputEditText src;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pemesanan);
        recyclerView = (RecyclerView)findViewById(R.id.rcyTransaksi);
        dba = this;
        lymanager = new LinearLayoutManager(DataPemesananActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lymanager);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tampilDataTransaksi();
        Intent intent = getIntent();
        String kodetr = intent.getStringExtra("KODETR");
        int idStatusProses = intent.getIntExtra("STATUSPROSES",1);
        int idStatusAntar = intent.getIntExtra("STATUSANTAR",2);
        int idStatusTerima = intent.getIntExtra("STATUSTERIMA",3);

        ubahTerimaStatus(kodetr,idStatusTerima);
        ubahProsesStatus(kodetr,idStatusProses);
        ubahAntarStatus(kodetr,idStatusAntar);

        src= findViewById(R.id.txt_cariPemesanan);
        src.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
String kata = src.getText().toString();
showPencarianPemesanan(kata);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void hideteksKosong(){
        findViewById(R.id.txt_kondisiProdukKosong).setVisibility(View.GONE);
    }

    private void showPencarianPemesanan(String dicari){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<transaksiInfo> call = API.getSrcLivePemesanan(dicari);
        call.enqueue(new Callback<transaksiInfo>() {
            @Override
            public void onResponse(Call<transaksiInfo> call, Response<transaksiInfo> response) {
                if(response.body() != null && response.isSuccessful()){
                    listdata = response.body().getData();
                    adapter = new AdapterDataPemesanan(DataPemesananActivity.this, listdata);
                    recyclerView.setAdapter(adapter);

                }else{
                    Log.d("gagal", "onResponse: "+response.body().getKode()+" pesan "+response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<transaksiInfo> call, Throwable t) {
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


    }

    public void tampilDataTransaksi(){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<transaksiInfo> call = API.getTransaksiData();
        call.enqueue(new Callback<transaksiInfo>() {
            @Override
            public void onResponse(Call<transaksiInfo> call, Response<transaksiInfo> response) {
              pesan = response.body().getMessage();
                listdata = response.body().getData();
                adapter = new AdapterDataPemesanan(DataPemesananActivity.this,listdata);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<transaksiInfo> call, Throwable t) {
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
    public void ubahTerimaStatus(String kodetran1,int IdStatus1){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<EditPembeli> call = API.postEditPembeli(kodetran1,IdStatus1);
        call.enqueue(new Callback<EditPembeli>() {
            @Override
            public void onResponse(Call<EditPembeli> call, Response<EditPembeli> response) {
                if(response.isSuccessful() && response.body() != null){
                    String pesan = response.body().getPesan();
                    if(response.body().isKondisi() == true){
                        View view = getLayoutInflater().inflate(R.layout.toast_rubah_terima, null);
                        view.findViewById(R.id.toast_succesRegist);
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(view);
                        toast.show();
                        toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
                    }else{

                    }
                }
            }
            @Override
            public void onFailure(Call<EditPembeli> call, Throwable t) {

            }

        });
    }

    public void ubahProsesStatus(String kodetran2,int IdStatus2){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<EditPembeli> call = API.postEditPembeli(kodetran2,IdStatus2);
        call.enqueue(new Callback<EditPembeli>() {
            @Override
            public void onResponse(Call<EditPembeli> call, Response<EditPembeli> response) {
                if(response.isSuccessful() && response.body() != null){
                    String pesan = response.body().getPesan();
                    if(response.body().isKondisi() == true){
                        View view = getLayoutInflater().inflate(R.layout.toast_rubah_proses, null);
                        view.findViewById(R.id.toast_succesRegist);
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(view);
                        toast.show();
                        toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
                    }else{

                    }
                }
            }
            @Override
            public void onFailure(Call<EditPembeli> call, Throwable t) {

            }

        });
    }

    public void ubahAntarStatus(String kodetran3,int IdStatus3){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<EditPembeli> call = API.postEditPembeli(kodetran3,IdStatus3);
        call.enqueue(new Callback<EditPembeli>() {
            @Override
            public void onResponse(Call<EditPembeli> call, Response<EditPembeli> response) {
                if(response.isSuccessful() && response.body() != null){
                    String pesan = response.body().getPesan();
                    if(response.body().isKondisi() == true){
                        View view = getLayoutInflater().inflate(R.layout.toast_rubah_pesan, null);
                        view.findViewById(R.id.toast_succesRegist);
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(view);
                        toast.show();
                        toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
                    }else{
                    }
                }
            }
            @Override
            public void onFailure(Call<EditPembeli> call, Throwable t) {

            }

        });
    }
}