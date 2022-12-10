package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Adapter.AdapterDataBarang;
import com.example.recyclick.Model.DataBarang.BarangGetInfo;
import com.example.recyclick.Model.DataBarang.DataItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detailBarangActiviy extends AppCompatActivity {
TextView tId,tNama,tHarga, tStok,tKat, tRat,tDesk,tBack;
ImageView img;
    APIRequestData API;
    private List<DataItem> listdata = new ArrayList<>();
    DataItem dataItem;
    public static detailBarangActiviy dba;
    String pesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang_activiy);
        tId = findViewById(R.id.tIdProd);
        tNama = findViewById(R.id.tNamaProd);
        tHarga = findViewById(R.id.tHargaProd);
        tStok = findViewById(R.id.tStok);
        tKat = findViewById(R.id.txt_kategori);
        tRat = findViewById(R.id.txtrating);
        tDesk = findViewById(R.id.txtDesk);
        img = findViewById(R.id.imgDetailBarang);
        tBack = findViewById(R.id.btn_back);

        tBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent i = getIntent();
        String idbr = i.getStringExtra("ID");
        tId.setText(idbr);

        String namabr = i.getStringExtra("NAMAPROD");
        tNama.setText(namabr);

        String hargabr = i.getStringExtra("HARGAPROD");
        tHarga.setText(hargabr);

        String stokbr = i.getStringExtra("STOKPROD");
        tStok.setText(stokbr);

        String deskbr = i.getStringExtra("DESKPROD");
        tDesk.setText(deskbr);

        String jenisbr = i.getStringExtra("JENISPROD");
        tKat.setText(jenisbr);

        String ratingbr = i.getStringExtra("RATINGPROD");
        tRat.setText(ratingbr);

        String gambarbr = i.getStringExtra("GAMBARPROD");

        Glide.with(getApplicationContext()).load(gambarbr).thumbnail(0.5f).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.photo_library_48px).into(img);

    }

    public void tampilProduk(){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<BarangGetInfo> call = API.getDataBarang();
        call.enqueue(new Callback<BarangGetInfo>() {
            @Override
            public void onResponse(Call<BarangGetInfo> call, Response<BarangGetInfo> response) {
                pesan = response.body().getMessage();
                listdata = response.body().getData();

                String id = listdata.get(0).getId();
                String nama  = listdata.get(0).getNama();
                int harga = Integer.parseInt(listdata.get(0).getHarga());
                int stok = Integer.parseInt(listdata.get(0).getStok());
                String kategori  = listdata.get(0).getKategori();
                String rating = listdata.get(0).getRating();
                String desk = listdata.get(0).getDeskripsi();

                tId.setText(id);
                tNama.setText(nama);
                tHarga.setText(String.valueOf(harga));
                tStok.setText(String.valueOf(stok));
                tKat.setText(kategori);
                tRat.setText(rating);
                tDesk.setText(desk);

            }
            @Override
            public void onFailure(Call<BarangGetInfo> call, Throwable t) {

            }
        });
    }
}