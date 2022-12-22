package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Adapter.AdapterAlamatPbl;
import com.example.recyclick.Adapter.AdapterDetailPemesanan;
import com.example.recyclick.Model.Pembeli.PembeliData;
import com.example.recyclick.Model.Pembeli.PembeliInfo;
import com.example.recyclick.Model.Transaksi.transaksiDataDetailAlamat;
import com.example.recyclick.Model.Transaksi.transaksiDetailAlamatInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPemesananActivity extends AppCompatActivity {
    TextView id,username;
    private RecyclerView recyclerView1,recyclerView2;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lymanager;
    private RecyclerView.LayoutManager lymanager2;
    private List<transaksiDataDetailAlamat> listdata2 = new ArrayList<>();
    private List<PembeliData> listdata = new ArrayList<>();
    private PembeliData listdata3;
    public static DetailPemesananActivity dba;
    APIRequestData API;
    String pesan;
    int kata;
    TextView back,kab,kec,desa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pemesanan);
        id = findViewById(R.id.txtIdTrans);
        username = findViewById(R.id.txt3);
        back = findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent i = getIntent();
        String idTr = i.getStringExtra("ID");
        id.setText(idTr);

        String UserPbl = i.getStringExtra("USERNAME");
        username.setText(UserPbl);




        recyclerView1 = (RecyclerView) findViewById(R.id.rcyInfoDetailPemesanan);
        dba = this;
        lymanager = new LinearLayoutManager(DetailPemesananActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView1.setLayoutManager(lymanager);
        tampilDetailPemesanan(idTr);

        recyclerView2 = findViewById(R.id.rcyAlamatPembeli);
        dba= this;
        lymanager2 =new LinearLayoutManager(DetailPemesananActivity.this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(lymanager2);
        tampilAlamatPembeli(UserPbl);



    }

    public void tampilDetailPemesanan(String kdtr){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<transaksiDetailAlamatInfo> call = API.postTrDetail2(kdtr);
        call.enqueue(new Callback<transaksiDetailAlamatInfo>() {
            @Override
            public void onResponse(retrofit2.Call<transaksiDetailAlamatInfo> call, Response<transaksiDetailAlamatInfo> response) {
                pesan = response.body().getMessage();
                listdata2 = response.body().getData();
                adapter = new AdapterDetailPemesanan(DetailPemesananActivity.this, listdata2);
                recyclerView1.setAdapter(adapter);
                adapter.notifyDataSetChanged();
//                kata = listdata2.size();
//                Log.e("Error",String.valueOf(kata));
//                Log.d(pesan, "onResponse: ");

            }
            @Override
            public void onFailure(retrofit2.Call<transaksiDetailAlamatInfo> call, Throwable t) {
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

    public void tampilAlamatPembeli(String username){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<PembeliInfo> call = API.getPembeliAlamat(username);
        call.enqueue(new Callback<PembeliInfo>() {
            @Override
            public void onResponse(retrofit2.Call<PembeliInfo> call, Response<PembeliInfo> response) {
                listdata = response.body().getData();
                adapter = new AdapterAlamatPbl(DetailPemesananActivity.this,listdata);

                recyclerView2.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(retrofit2.Call<PembeliInfo> call, Throwable t) {
                Log.d(t.getMessage(), "onFailure: ");
            }
        });

    }
}