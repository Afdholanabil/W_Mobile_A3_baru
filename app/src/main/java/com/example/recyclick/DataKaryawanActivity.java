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
import com.example.recyclick.Adapter.AdapterKaryawan;
import com.example.recyclick.Model.DataBarang.BarangGetInfo;
import com.example.recyclick.Model.DataBarang.DataItem;
import com.example.recyclick.Model.DataBarang.DeleteBarang;
import com.example.recyclick.Model.DataKaryawan.DeleteKaryawan;
import com.example.recyclick.Model.DataKaryawan.KaryawanGetInfo;
import com.example.recyclick.Model.DataKaryawan.KaryawanItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataKaryawanActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lymanager;
    private List<KaryawanItem> listdata = new ArrayList<>();
    public static DataKaryawanActivity dba;
    APIRequestData API;
    String pesan;
    FloatingActionButton tambahData;
    TextView btnBack;
    TextInputEditText src;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_karyawan);
        recyclerView = (RecyclerView) findViewById(R.id.rcyKaryawan);
        dba = this;
        lymanager = new LinearLayoutManager(DataKaryawanActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lymanager);
        tambahData = (FloatingActionButton) findViewById(R.id.btnAddKaryawan);
        tambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DataKaryawanActivity.this, new TambahKaryawanActivity().getClass()));
            }
        });
        tampilDataKaryawan();

        btnBack = (TextView)findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        src = findViewById(R.id.srcKry);
        src.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String kata = src.getText().toString();
                showPencarianKry(kata);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void tampilDataKaryawan(){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<KaryawanGetInfo> call = API.getKaryawanData();
        call.enqueue(new Callback<KaryawanGetInfo>() {
            @Override
            public void onResponse(Call<KaryawanGetInfo> call, Response<KaryawanGetInfo> response) {
                pesan = response.body().getMessage();
                listdata = response.body().getData();
                Log.e("data", "onResponse: "+listdata );
                adapter = new AdapterKaryawan(DataKaryawanActivity.this,listdata);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<KaryawanGetInfo> call, Throwable t) {
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

    public void hapusDataKaryawan(String idprd,String gambar){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<DeleteKaryawan> call = API.postDeleteKaryawan(idprd,gambar);
        call.enqueue(new Callback<DeleteKaryawan>() {
            @Override
            public void onResponse(Call<DeleteKaryawan> call, Response<DeleteKaryawan> response) {
                if(response.isSuccessful() && response.body() != null){
                    String pesan = response.body().getPesan();
                    if(response.body().isKondisi() == true){
                        View view = getLayoutInflater().inflate(R.layout.toast_hapus_kry, null);
                        view.findViewById(R.id.toast_deleteProduk);
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
            public void onFailure(Call<DeleteKaryawan> call, Throwable t) {
                View view = getLayoutInflater().inflate(R.layout.toast_no_internet, null);
                view.findViewById(R.id.toast_noConnection);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
                Log.e("error", "onFailure: "+t );
            }

        });
    }

    private void showPencarianKry(String dicari){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<KaryawanGetInfo> call = API.getSrcLiveKry(dicari);
        call.enqueue(new Callback<KaryawanGetInfo>() {
            @Override
            public void onResponse(Call<KaryawanGetInfo> call, Response<KaryawanGetInfo> response) {
                if(response.body() != null && response.isSuccessful()){
                    listdata = response.body().getData();
                    adapter = new AdapterKaryawan(DataKaryawanActivity.this, listdata);
                    recyclerView.setAdapter(adapter);


                }else{
                    Log.d("gagal", "onResponse: "+response.body().getKode()+" pesan "+response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<KaryawanGetInfo> call, Throwable t) {
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
    public void hideteksKosongKry(){
        findViewById(R.id.txt_kondisiProdukKosong).setVisibility(View.GONE);
    }
}