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
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Model.DataBarang.AddBarang;
import com.example.recyclick.Model.Register.RegisterInfo;
import com.example.recyclick.Model.Register.addAkun;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahKaryawanActivity extends AppCompatActivity {
    TextView btnBack;
    TextInputEditText tUser, tpass, tKonfirPass, tHp, tNama;
    CardView btnSimpan;
    int role;
    ImageView image;
    CardView cardGambar;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_karyawan);
        btnBack = (TextView) findViewById(R.id.btn_back);
        tUser = findViewById(R.id.inputUser);
        tpass = findViewById(R.id.inputPass);
        tKonfirPass = findViewById(R.id.inputKonfPass);
        tHp = findViewById(R.id.inputNoHp);
        btnSimpan = findViewById(R.id.btnSimpan);
        tNama = findViewById(R.id.inputNama);
        role = 2;
        image = findViewById(R.id.img);
        cardGambar = findViewById(R.id.crd3);
        image.setOnClickListener(new View.OnClickListener() {
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
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = tUser.getText().toString();
                String pass = tpass.getText().toString();
                String passKon = tKonfirPass.getText().toString();
                String nama = tNama.getText().toString();
                String noHp = tHp.getText().toString();

                if (tUser.length() == 0 || tpass.length() == 0  || tKonfirPass.length() == 0 || tNama.length() == 0 || tHp.length() == 0 || uri == null) {
                    Toast.makeText(TambahKaryawanActivity.this, "Data Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                } else {
                    if (username.length() > 15) {
                        Toast.makeText(TambahKaryawanActivity.this, "Username tidak boleh lebih dari 15 karakter", Toast.LENGTH_SHORT).show();
                    } else if(username.length() <5){
                        Toast.makeText(TambahKaryawanActivity.this, "Username Harus Lebih Dari 5 Karakter", Toast.LENGTH_SHORT).show();
                    } else if (pass.length() > 10) {
                        Toast.makeText(TambahKaryawanActivity.this, "Password tidak boleh lebih dari 10 karakter", Toast.LENGTH_SHORT).show();
                    }else if(pass.length()<5){
                        Toast.makeText(TambahKaryawanActivity.this, "Password Harus Lebih dari 5 Karakter", Toast.LENGTH_SHORT).show();
                    } else if (noHp.length() > 13) {
                        Toast.makeText(TambahKaryawanActivity.this, "Nomor Hp anda lebih dari 13 karakter", Toast.LENGTH_SHORT).show();
                    } else if (passKon.equals(pass)) {
                        addKaryawan(username, pass, nama, noHp, role);
                    } else {
                        Toast.makeText(TambahKaryawanActivity.this, "Masukan input password yang sama", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void addKaryawan(String username, String pass, String nama, String noHp, int role) {
        String uriphoto = getRealPathFromUri(this, uri);
        File file = new File(uriphoto);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        RequestBody usrname = RequestBody.create(MediaType.parse("text/plain"), username);
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), pass);
        RequestBody namausr = RequestBody.create(MediaType.parse("text/plain"), nama);
        RequestBody noHpusr = RequestBody.create(MediaType.parse("text/plain"), noHp);
        RequestBody roleusr = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(role));

        MultipartBody.Part body = MultipartBody.Part.createFormData("photo", file.getName(),requestFile);

        APIRequestData api = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<RegisterInfo> call = api.CreateRegisterPost(body, usrname, password, namausr, noHpusr, roleusr);
        call.enqueue(new Callback<RegisterInfo>() {
            @Override
            public void onResponse(Call<RegisterInfo> call, Response<RegisterInfo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isKondisi() == true) {
                        View view = getLayoutInflater().inflate(R.layout.toast_berhasil_daftar, null);
                        view.findViewById(R.id.toast_succesRegist);
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(view);
                        toast.show();
                        toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
                        startActivity(new Intent(TambahKaryawanActivity.this, DataKaryawanActivity.class));
                    } else {
                        Toast.makeText(TambahKaryawanActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterInfo> call, Throwable t) {
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


    public void getImg() {
//        final CharSequence[] opsiImg = {"Gallery", "Camera"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(TambahKaryawanActivity.this);
//        builder.setTitle("Pilih photo dari");
//        builder.setItems(opsiImg, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                switch (i) {
//                    case 0:
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, 0);
//                        break;
//                    case 1:
//                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivityForResult(takePicture, 1);
//                        break;
//                }
//            }
//        });
//        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            uri = data.getData();
            image.setImageURI(uri);
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