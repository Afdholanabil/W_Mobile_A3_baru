package com.example.recyclick;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Adapter.AdapterKategoriTB;
import com.example.recyclick.Model.DataBarang.AddBarang;
import com.example.recyclick.Model.Kategori.KategoriInfo;
import com.example.recyclick.Model.Kategori.KategoriItem;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahBarangActivity extends AppCompatActivity {

    TextInputEditText id, nama, stok, harga, deksripsi, kategori;
    TextView btnBack;
    Button simpan;
    ImageView img, imagekgr;
    CardView cardgambar;
    RecyclerView recy;
    Uri ur;
    PopupWindow popupWindow;
    private  APIRequestData api;
    private String kategoriid;
    public static TambahBarangActivity tba;
    private List<KategoriItem> item = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deklarasi();

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_kategori, null);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false;
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        imagekgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showKategori(view, popupView);
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idbr = id.getText().toString();
                String namabr = nama.getText().toString();
                int stokbr = Integer.parseInt(String.valueOf(stok.getText()));
                int hargabr = Integer.parseInt(String.valueOf(harga.getText()));
                String deskripsi = deksripsi.getText().toString();
                int rating = 0;
                if (idbr.equals(null) || namabr.equals(null) || stok.equals(null) || harga.equals(null) || deskripsi.equals(null) || kategori.equals(null)) {
                    Toast.makeText(TambahBarangActivity.this, "Data Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                } else {
                    addProduk(idbr, namabr, stokbr, hargabr, deskripsi, rating);
                }

            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImg();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void deklarasi() {
        setContentView(R.layout.activity_tambahbarang);
        tba = this;
        id = findViewById(R.id.idPrd);
        nama = findViewById(R.id.namaPrd);
        stok = findViewById(R.id.stokPrd);
        harga = findViewById(R.id.hargaprd);
        deksripsi = findViewById(R.id.deskPrd);
        kategori = findViewById(R.id.kategoriprd);
        btnBack = findViewById(R.id.btn_back);
        img = findViewById(R.id.img);
        simpan = findViewById(R.id.btn_spn);
        imagekgr = findViewById(R.id.tb_btnkategori);
        api = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
    }

    public void addProduk(String id, String nama, int stok, int harga, String deskripsi, int rating) {
        String path = getRealPathFromUri(this, ur);
        File file = new File(path);
        RequestBody idbr = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody namabr = RequestBody.create(MediaType.parse("text/plain"), nama);
        RequestBody stokbr = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(stok));
        RequestBody hargabr = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(harga));
        RequestBody deskripsibr = RequestBody.create(MediaType.parse("text/plain"), deskripsi);
        RequestBody kategoribr = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(kategoriid));
        RequestBody ratingbr = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(rating));
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageup", file.getName(), requestFile);
        Call<AddBarang> call = api.postDataBarang(body, idbr, namabr, stokbr, hargabr, deskripsibr, kategoribr, ratingbr);
        call.enqueue(new Callback<AddBarang>() {
            @Override
            public void onResponse(Call<AddBarang> call, Response<AddBarang> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isKondisi() == true) {
                        Toast.makeText(TambahBarangActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(TambahBarangActivity.this, DataBarangActivity.class));
                    } else {
                        Toast.makeText(TambahBarangActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddBarang> call, Throwable t) {
                Log.d(t.getMessage(), "onFailure: ");
            }
        });
    }


    public void getImg() {
        final CharSequence[] opsiImg = {"Gallery", "Camera"};
        AlertDialog.Builder builder = new AlertDialog.Builder(TambahBarangActivity.this);
        builder.setTitle("Pilih gambar dari");
        builder.setItems(opsiImg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, 0);
                        break;
                    case 1:
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, 1);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ur = data.getData();
            img.setImageURI(ur);
        }
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void showKategori(View view, View view2) {
        int[] loc_int = new int[2];
        kategori.getLocationOnScreen(loc_int);
        Rect location = new Rect();
        location.left = loc_int[0];
        location.top = loc_int[1];
        location.right = location.left + kategori.getWidth();
        location.bottom = location.top + kategori.getHeight();

        recy = view2.findViewById(R.id.popuprcy);
        Call<KategoriInfo> call = api.getKategoriData();
        call.enqueue(new Callback<KategoriInfo>() {
            @Override
            public void onResponse(Call<KategoriInfo> call, Response<KategoriInfo> response) {
                item = response.body().getData();
                AdapterKategoriTB adapter = new AdapterKategoriTB(item);
                recy.setAdapter(adapter);
                recy.setLayoutManager(new LinearLayoutManager(TambahBarangActivity.this));
            }

            @Override
            public void onFailure(Call<KategoriInfo> call, Throwable t) {

            }
        });
        popupWindow.showAtLocation(view, Gravity.TOP | Gravity.LEFT, location.left, location.bottom);
    }

    public void closepopkategori() {
        popupWindow.dismiss();
    }

    public void setkategori(String id, String kategoriname) {
        kategori.setText(kategoriname);
        kategoriid = id;
    }


}


