package com.example.recyclick;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Adapter.AdapterDataBarang;
import com.example.recyclick.Adapter.AdapterLaporan;
import com.example.recyclick.Model.DataBarang.BarangGetInfo;
import com.example.recyclick.Model.DataBarang.DataItem;
import com.example.recyclick.Model.Laporan.DataItemLengkap;
import com.example.recyclick.Model.Laporan.LaporanLengkap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanLengkapActivity  extends AppCompatActivity {

    private RecyclerView recycle;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lymanager;
    EditText src;
    private APIRequestData api;
    private List<DataItemLengkap> item = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deklarasiVariable();
        showDataLaporanLengkap();



        src.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String kata = src.getText().toString();
                showPencarian(kata);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void deklarasiVariable(){
        setContentView(R.layout.activty_laporandata);
        recycle = findViewById(R.id.lapsdt_recycle);
        api = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        src = findViewById(R.id.txt_Search);
    }
    private void showDataLaporanLengkap(){
        Call<LaporanLengkap> call = api.getInfoLaporan();

        call.enqueue(new Callback<LaporanLengkap>() {
            @Override
            public void onResponse(Call<LaporanLengkap> call, Response<LaporanLengkap> response) {
                if(response.body() != null && response.isSuccessful()){
                    Log.d("berhasil", " onResponse: "+response.body().getKode()+"|| pesan "+response.body().getMessage());
                    item = response.body().getData();
                    AdapterLaporan laporan = new AdapterLaporan(item);
                    recycle.setAdapter(laporan);
                    recycle.setLayoutManager(new LinearLayoutManager(LaporanLengkapActivity.this));
                }
            }

            @Override
            public void onFailure(Call<LaporanLengkap> call, Throwable t) {
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

    private void showPencarian(String dicari){
        api = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<LaporanLengkap> call = api.getInfoSearchlaporan(dicari);
        call.enqueue(new Callback<LaporanLengkap>() {
            @Override
            public void onResponse(Call<LaporanLengkap> call, Response<LaporanLengkap> response) {
                if(response.body() != null && response.isSuccessful()){
                    item = response.body().getData();
                    adapter = new AdapterLaporan(item);
                    recycle.setAdapter(adapter);


                }else{
                    Log.d("gagal", "onResponse: "+response.body().getKode()+" pesan "+response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<LaporanLengkap> call, Throwable t) {
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
}
