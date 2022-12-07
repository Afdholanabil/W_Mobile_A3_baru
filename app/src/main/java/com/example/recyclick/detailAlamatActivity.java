package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Adapter.AdapterAlamatPbl;
import com.example.recyclick.Model.Pembeli.PembeliData;
import com.example.recyclick.Model.Pembeli.PembeliInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detailAlamatActivity extends AppCompatActivity {
    TextView idAlamat,userPbl,kab,kec,desa,pos,desk;
    public static detailAlamatActivity dba;
    private List<PembeliData> listdata;
    APIRequestData API;
    String pesan;
    PembeliData pembeliData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alamat);
        idAlamat = findViewById(R.id.tIdAlamat);
        userPbl = findViewById(R.id.tIdUser);
        kab = findViewById(R.id.txtKab);
        kec = findViewById(R.id.txtKec);
        desa = findViewById(R.id.txtDesa);
        pos = findViewById(R.id.txtKodePos);
        desk = findViewById(R.id.txtDesk);

        Intent intent = getIntent();
        String idAl = intent.getStringExtra("IDALAMAT");
        String userP = intent.getStringExtra("USERNAME");
        idAlamat.setText(idAl);
        userPbl.setText(userP);


        tampilAlamatDetail("akhdan123");


    }

    public void tampilAlamatDetail(String username){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<PembeliInfo> call = API.getPembeliAlamat(username);
        call.enqueue(new Callback<PembeliInfo>() {
            @Override
            public void onResponse(retrofit2.Call<PembeliInfo> call, Response<PembeliInfo> response) {
                listdata = response.body().getData();

                String kab1 = listdata.get(0).getKab();
                String kec1 = listdata.get(0).getKec();
                String desa1 =listdata.get(0).getDesa();
                int pos1 = listdata.get(0).getKodePos();
                String deskripsi1 = listdata.get(0).getDeskripsi();

                kab.setText(kab1);
                kec.setText(kec1);
                desa.setText(desa1);
                pos.setText(String.valueOf(pos1));
                desk.setText(deskripsi1);

            }
            @Override
            public void onFailure(retrofit2.Call<PembeliInfo> call, Throwable t) {
                Log.d(t.getMessage(), "onFailure: ");
            }
        });

    }
}