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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Fragment.LoginFragment;
import com.example.recyclick.Fragment.RegisterFragment;
import com.example.recyclick.Model.Register.RegisterInfo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextView btnKembali;
    CardView btnRegist;
    TextInputEditText txtNama, txtUsername, txtTelp;
    EditText txtPass, txtKonfirm;
    Uri uri;
    ImageView imagepp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtNama = (TextInputEditText) findViewById(R.id.inputText);
        txtUsername = (TextInputEditText) findViewById(R.id.inputText3);
        txtTelp = (TextInputEditText) findViewById(R.id.inputText2);
        txtPass = (EditText) findViewById(R.id.inputText4);
        txtKonfirm = (EditText) findViewById(R.id.inputText5);
        imagepp = findViewById(R.id.imgprofil);
        imagepp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImg();
            }
        });
        btnKembali = (TextView) findViewById(R.id.btn_back);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, new LoginActivity().getClass()));
            }
        });
        btnRegist = (CardView) findViewById(R.id.cardview2);
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = txtNama.getText().toString();
                String noTlp = txtTelp.getText().toString();
                String usr = txtUsername.getText().toString();
                String pass = txtPass.getText().toString();
                String repass = txtKonfirm.getText().toString();
                int kedudukan = 2;

                if (nama.equals("") || noTlp.equals("") || usr.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Data Kosong, Harus Diisi ! ", Toast.LENGTH_LONG).show();
                } else {
                    if (usr.length() > 15) {
                        Toast.makeText(getApplicationContext(), "Username Tidak Boleh lebih dari 15 karakter", Toast.LENGTH_SHORT).show();

                    } else if(usr.length()<5){
                        Toast.makeText(RegisterActivity.this, "Username harus lebih dari 5 karakter", Toast.LENGTH_SHORT).show();
                    } else if (pass.length() > 10) {
                        Toast.makeText(getApplicationContext(), "Password tidak boleh lebih dari 10 karakter", Toast.LENGTH_SHORT).show();
                    }else if(pass.length()<5){
                        Toast.makeText(RegisterActivity.this, "Password harus lebih dari 5 karakter", Toast.LENGTH_SHORT).show();
                    } else if(noTlp.length()>13){
                        Toast.makeText(RegisterActivity.this, "Nomor Handphone Anda lebih dari 13 karakter", Toast.LENGTH_SHORT).show();

                    } else if (pass.equals(repass)) {
                        RegisterPost(usr, pass, nama, noTlp, kedudukan);
                    } else {
                        Toast.makeText(getApplicationContext(), "Masukan Password yang Sesuai", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }

    private void RegisterPost(String user, String pass, String nama, String notel, int kedudukan) {
        String uripp = getRealPathFromUri(this, uri);
        File file = new File(uripp);
        RequestBody requestPhoto = RequestBody.create(MediaType.parse("image/*"), file);
        RequestBody userReg = RequestBody.create(MediaType.parse("text/plain"), user);
        RequestBody passReg = RequestBody.create(MediaType.parse("text/plain"), pass);
        RequestBody namaReg = RequestBody.create(MediaType.parse("text/plain"), nama);
        RequestBody notelReg = RequestBody.create(MediaType.parse("text/plain"), notel);
        RequestBody kedudukanReg = RequestBody.create(MediaType.parse("text/plain"),String.valueOf(kedudukan));


        MultipartBody.Part photo = MultipartBody.Part.createFormData("photo", file.getName(),requestPhoto);

        APIRequestData API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<RegisterInfo> call = API.CreateRegisterPost(photo,userReg, passReg, namaReg, notelReg, kedudukanReg);
        call.enqueue(new Callback<RegisterInfo>() {
            @Override
            public void onResponse(Call<RegisterInfo> call, Response<RegisterInfo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isKondisi() == true) {
                        Toast.makeText(RegisterActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterInfo> call, Throwable t) {
                Log.d("server error", "onFailure: " + t);
            }
        });
    }
    public void syaratTelp(){

    }

    public void getImg() {
        final CharSequence[] opsiImg = {"Gallery", "Camera"};
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setTitle("Pilih photo dari");
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
            uri = data.getData();
            imagepp.setImageURI(uri);
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