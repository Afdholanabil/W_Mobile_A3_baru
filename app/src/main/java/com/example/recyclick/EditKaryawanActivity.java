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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Model.DataBarang.EditBarang;
import com.example.recyclick.Model.Login.EditProfil;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditKaryawanActivity extends AppCompatActivity {
    private static final String TAG = "EditKaryawanActivity";
    APIRequestData API;
    String pesan;
    public static EditKaryawanActivity dba;
    TextInputEditText tUser,tpass,tpassBaru,tNama,tNohp;
    CardView btnSimpan;
    TextView btnBack;
    ImageView imgEditKry;
    int role;
    Uri ur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_karyawan);
        role =2;
        tUser = findViewById(R.id.inputUsername);
        tpass = findViewById(R.id.inputPassLama);
        tpassBaru = findViewById(R.id.inputPassBaru);
        tNama = findViewById(R.id.inputNama);
        tNohp = findViewById(R.id.inputNoHp);
        imgEditKry = findViewById(R.id.imgKry);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent it = getIntent();
        String userKr =it.getStringExtra("USERKRY");
        tUser.setText(userKr);

        String namaKr =it.getStringExtra("NAMAKRY");
        tNama.setText(namaKr);

        String noHpKr =it.getStringExtra("TELPKRY");
        tNohp.setText(noHpKr);

        String gambarKr =it.getStringExtra("GAMBARKRY");
        Glide.with(getApplicationContext()).load("https://workshopjti.com/RecyclickA3/"+gambarKr).thumbnail(0.5f).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.photo_library_48px).into(imgEditKry);


        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = tUser.getText().toString();
                String pass = tpass.getText().toString();
                String passBaru = tpassBaru.getText().toString();
                String nama = tNama.getText().toString();
                String noHp = tNohp.getText().toString();
                Intent it2 = getIntent();
                String passKr = it2.getStringExtra("PASSKRY");

                if (username.equals(null) || nama.equals(null) || pass.equals(null) ||passBaru.equals(null) || noHp.equals(null) ) {
                    Toast.makeText(EditKaryawanActivity.this, "Data Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                }else {
                    if(passBaru.length()>10){
                        Toast.makeText(EditKaryawanActivity.this, "Password Baru tidak boleh lebih dari 10 karakter", Toast.LENGTH_SHORT).show();
                    }else if(passBaru.length()<5){
                        Toast.makeText(EditKaryawanActivity.this, "Password baru harus lebih dari 5 karakter", Toast.LENGTH_SHORT).show();
                    }else if(noHp.length()>13){
                        Toast.makeText(EditKaryawanActivity.this, "Nomor HP anda lebih dari 13 karakter", Toast.LENGTH_SHORT).show();
                    }
                    else if (pass.equals(passKr)){
                        EditDataKaryawan(username,passBaru,nama,noHp,gambarKr);

                    }else {
                        Toast.makeText(EditKaryawanActivity.this, "Passwrod Lama harus sama", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        imgEditKry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImg();
            }
        });

    }
    public void EditDataKaryawan(String username,String pass,String nama, String noHp,String gambarOld){
        APIRequestData api = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);

        Call<EditProfil> call;

        if(ur != null){
            String path = getRealPathFromUri(this, ur);
            File file = new File(path);
            RequestBody user = RequestBody.create(MediaType.parse("text/plain"), username);
            RequestBody passUsr = RequestBody.create(MediaType.parse("text/plain"), pass);
            RequestBody namaUsr = RequestBody.create(MediaType.parse("text/plain"), nama);
            RequestBody phoneUsr = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(noHp));
            RequestBody gambarOld2 = RequestBody.create(MediaType.parse("text/plain"),gambarOld);
            RequestBody isNull = RequestBody.create(MediaType.parse("text/plain"),"false");
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("photo", file.getName(),requestFile);
            call = api.postEditKaryawan(body,user,passUsr,namaUsr,phoneUsr,gambarOld2,isNull);
        }else{
            call = api.postEditKaryawannoImg(username,pass,nama,noHp,gambarOld,"true");
        }

        View view = getLayoutInflater().inflate(R.layout.toast_loading, null);
        view.findViewById(R.id.toast_noConnection);
        Toast toast = new Toast(getApplicationContext());
        toast.setView(view);
        toast.show();
        toast.setGravity(Gravity.CENTER,0,0);

        call.enqueue(new Callback<EditProfil>() {
            @Override
            public void onResponse(Call<EditProfil> call, Response<EditProfil> response) {
                if(response.isSuccessful() && response.body() != null){
                    toast.cancel();
                    String pesan = response.body().getPesan();
                    if(response.body().isKondisi() == true){
                        View view = getLayoutInflater().inflate(R.layout.toast_edit_karyawan, null);
                        view.findViewById(R.id.toast_succesRegist);
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(view);
                        toast.show();
                        toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
                        startActivity(new Intent(EditKaryawanActivity.this, PengaturanActivity.class));
                    }else{
                        Toast.makeText(EditKaryawanActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<EditProfil> call, Throwable t) {
                toast.cancel();
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

    public void getImg() {
//        final CharSequence[] opsiImg = {"Gallery", "Camera"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(EditKaryawanActivity.this);
//        builder.setTitle("Pilih gambar dari");
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
            ur = data.getData();
            imgEditKry.setImageURI(ur);
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