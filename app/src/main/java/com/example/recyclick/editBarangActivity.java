package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Model.DataBarang.DeleteBarang;
import com.example.recyclick.Model.DataBarang.EditBarang;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class editBarangActivity extends AppCompatActivity {
    private static final String TAG = "editBarangActivity";
    TextView btnBack;
    CardView btnSimpan;
    APIRequestData API;
    String pesan;
    public static editBarangActivity dba;
    TextInputEditText txtId, txtNama, txtJenis,txtStok,txtHarga, txtDesk;
    int ratingbr;
    String gambarbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);
        btnBack = (TextView) findViewById(R.id.btn_back);
        txtId = findViewById(R.id.inputId);
        txtNama = findViewById(R.id.inputNama);
        txtHarga = findViewById(R.id.inputHarga);
        txtJenis = findViewById(R.id.inputJenis);
        txtStok = findViewById(R.id.inputStok);
        txtDesk = findViewById(R.id.inputDesk);
        ratingbr = 1;
        gambarbr = "image";
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Intent i = getIntent();
        String idbr = i.getStringExtra("ID");
        txtId.setText(idbr);

        String namabr = i.getStringExtra("NAMAPROD");
        txtNama.setText(namabr);

        String hargabr = i.getStringExtra("HARGAPROD");
        txtHarga.setText(hargabr);

        String stokbr = i.getStringExtra("STOKPROD");
        txtStok.setText(stokbr);

        String deskbr = i.getStringExtra("DESKPROD");
        txtDesk.setText(deskbr);

        String jenisbr = i.getStringExtra("JENISPROD");
        txtJenis.setText(jenisbr);





//        String namabr = i.getStringExtra("NAMA");
//        txtNama.setText(namabr);
//
//
//        int jenisbr = Integer.parseInt(i.getStringExtra("JENIS"));
//        txtJenis.setText(jenisbr);
//
//
//        int stokbr = Integer.parseInt(i.getStringExtra("STOK"));
//        txtStok.setText(stokbr);
//
//        int hargabr = Integer.parseInt(i.getStringExtra("HARGA"));
//        txtHarga.setText(hargabr);
//
//
//        String deskbr = i.getStringExtra("DESKRIPSI");
//        txtDesk.setText(deskbr);
//
//        int ratingbr = Integer.parseInt(i.getStringExtra("RATING"));
//
//        String gambarbr = i.getStringExtra("GAMBAR");


        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idbr = txtId.getText().toString();
                String namabr = txtNama.getText().toString();
                int stokbr = Integer.parseInt(String.valueOf(txtStok.getText()));
                int hargabr = Integer.parseInt(String.valueOf(txtHarga.getText()));
                String deskbr = txtDesk.getText().toString();
                int jenisbr = Integer.parseInt(String.valueOf(txtJenis.getText()));
                if (idbr.equals(null) || namabr.equals(null) || txtStok.equals(null) || txtHarga.equals(null) || deskbr.equals(null) || txtJenis.equals(null)) {
                    Toast.makeText(editBarangActivity.this, "Data Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                }else {
                    EditDataBarang(idbr,namabr,stokbr,hargabr,gambarbr,deskbr,jenisbr,ratingbr);
                    finish();
                }

            }
        });
        }

        public void EditDataBarang(String id,String nama,int stok, int harga,String gambar, String desk, int jenis,int rating){
            API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
            Call<EditBarang> call = API.postEditBarang(id,nama,stok,harga,gambar,desk,jenis,rating);
            call.enqueue(new Callback<EditBarang>() {
                @Override
                public void onResponse(Call<EditBarang> call, Response<EditBarang> response) {
                    if(response.isSuccessful() && response.body() != null){
                        String pesan = response.body().getPesan();
                        if(response.body().isKondisi() == true){
                            Toast.makeText(editBarangActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(editBarangActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<EditBarang> call, Throwable t) {
                    Log.e("error", "onFailure: "+t );
                }

            });
        }


    }