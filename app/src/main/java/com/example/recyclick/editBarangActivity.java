package com.example.recyclick;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Model.DataBarang.DeleteBarang;
import com.example.recyclick.Model.DataBarang.EditBarang;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    TextInputEditText txtId, txtNama, txtJenis, txtStok, txtHarga, txtDesk;
    int ratingbr;
    private Uri ur;
    private ImageView imgBarang;

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
        imgBarang = findViewById(R.id.ed_image);
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

        String gambarbr = i.getStringExtra("GAMBARPROD");

        Glide.with(getApplicationContext()).load(gambarbr).thumbnail(0.5f).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.photo_library_48px).into(imgBarang);


        Log.d(String.valueOf(ur), "nilai ur: ");


        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtId.equals("") || txtNama.equals("") || txtStok.equals("") || txtHarga.equals("") || txtDesk.equals("") || txtJenis.equals("")) {
                    Toast.makeText(editBarangActivity.this, "Data Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                } else {
                    String idbr = txtId.getText().toString();
                    String namabr = txtNama.getText().toString();
                    int stokbr = Integer.parseInt(String.valueOf(txtStok.getText()));
                    int hargabr = Integer.parseInt(String.valueOf(txtHarga.getText()));
                    String deskbr = txtDesk.getText().toString();
                    int jenisbr = Integer.parseInt(String.valueOf(txtJenis.getText()));
                    int rating = 0;
                    EditDataBarang(idbr, namabr, stokbr, hargabr, deskbr, jenisbr, rating, gambarbr);

                }

            }
        });

        imgBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImg();
            }
        });

    }

    public void EditDataBarang(String id, String nama, int stok, int harga, String desk, int jenis, int rating, String gambarOld) {
        APIRequestData api = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);

//        RequestBody idbr = RequestBody.create(MediaType.parse("text/plain"), id);
//        RequestBody namabr = RequestBody.create(MediaType.parse("text/plain"), nama);
//        RequestBody stokbr = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(stok));
//        RequestBody hargabr = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(harga));
//        RequestBody deskripsibr = RequestBody.create(MediaType.parse("text/plain"), desk);
//        RequestBody kategoribr = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(jenis));
//        RequestBody ratingbr = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(rating));
//        RequestBody gambarOld2 = RequestBody.create(MediaType.parse("text/plain"), gambarOld);

        Call<EditBarang> call;
        if (ur != null) {
            String path = getRealPathFromUri(this, ur);
            File file = new File(path);
            RequestBody idbr = RequestBody.create(MediaType.parse("text/plain"), id);
            RequestBody namabr = RequestBody.create(MediaType.parse("text/plain"), nama);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            RequestBody deskripsibr = RequestBody.create(MediaType.parse("text/plain"), desk);
            RequestBody gambarOld2 = RequestBody.create(MediaType.parse("text/plain"), gambarOld);
            RequestBody isnull = RequestBody.create(MediaType.parse("text/plain"), "false");
            MultipartBody.Part body = MultipartBody.Part.createFormData("imageup", file.getName(), requestFile);
            call = api.postEditBarangWithImg(body, idbr, namabr, stok, harga, gambarOld2, deskripsibr, jenis, isnull);
        } else {
            call = api.postEditBarangNoImg(id, nama, stok, harga, gambarOld, desk, jenis, "true");
        }



        call.enqueue(new Callback<EditBarang>() {
            @Override
            public void onResponse(Call<EditBarang> call, Response<EditBarang> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String pesan = response.body().getPesan();
                    if (response.body().isKondisi() == true) {
                        Toast.makeText(editBarangActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(editBarangActivity.this, DataBarangActivity.class));
                    } else {
                        Toast.makeText(editBarangActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<EditBarang> call, Throwable t) {
                Log.e("error", "onFailure: " + t);
            }

        });
    }


    public void getImg() {
        final CharSequence[] opsiImg = {"Gallery", "Camera"};
        AlertDialog.Builder builder = new AlertDialog.Builder(editBarangActivity.this);
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
            imgBarang.setImageURI(ur);
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
}