package com.example.recyclick;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
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
    TextView btnKembali, backMasuk;
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
        backMasuk = findViewById(R.id.tv33);
        backMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
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

                if (txtNama.equals(null) || txtNama.length() == 0 || txtTelp.length() == 0 || txtUsername.length() == 0 || txtPass.length() == 0 || txtKonfirm.length() == 0 || uri == null) {
                    Toast.makeText(getApplicationContext(), "Data Kosong, Harus Diisi ! ", Toast.LENGTH_LONG).show();
                } else {
                    if (usr.length() > 15) {
                        Toast.makeText(getApplicationContext(), "Username Tidak Boleh lebih dari 15 karakter", Toast.LENGTH_SHORT).show();
                    } else if (usr.length() < 5) {
                        Toast.makeText(RegisterActivity.this, "Username harus lebih dari 5 karakter", Toast.LENGTH_SHORT).show();
                    } else if (pass.length() > 10) {
                        Toast.makeText(getApplicationContext(), "Password tidak boleh lebih dari 10 karakter", Toast.LENGTH_SHORT).show();
                    } else if (pass.length() < 5) {
                        Toast.makeText(RegisterActivity.this, "Password harus lebih dari 5 karakter", Toast.LENGTH_SHORT).show();
                    } else if (txtTelp.length() > 13) {
                        Toast.makeText(RegisterActivity.this, "Nomor Handphone Anda lebih dari 13 karakter", Toast.LENGTH_SHORT).show();

                    } else if (pass.equals(repass)) {
//                        checkpermission();
//                        if(kondisi){
                        RegisterPost(usr, pass, nama, noTlp, kedudukan);
//                        }
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
        RequestBody kedudukanReg = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(kedudukan));


        MultipartBody.Part photo = MultipartBody.Part.createFormData("photo", file.getName(), requestPhoto);

        APIRequestData API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<RegisterInfo> call = API.CreateRegisterPost(photo, userReg, passReg, namaReg, notelReg, kedudukanReg);
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
                        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(RegisterActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
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
                toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
                Log.d("server error", "onFailure: " + t.getMessage());
            }
        });
    }

    public void getImg() {
//        final CharSequence[] opsiImg = {"Gallery", "Camera"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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

//
//
//    private void requestpermission(){
//        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.R){
//            try{
//                Intent itn = new Intent();
//                itn.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
//                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
//                itn.setData(uri);
//                storageActivityResultLauncher.launch(itn);
//            }catch (Exception e){
//                Intent itn = new Intent();
//                itn.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
//                storageActivityResultLauncher.launch(itn);
//            }
//        }else{
//            ActivityCompat.requestPermissions(this,
//                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
//        }
//    }
//
//    private ActivityResultLauncher<Intent> storageActivityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
//                        if(Environment.isExternalStorageManager()){
//                            kondisi = true;
//                        }else{
//                            kondisi = false;
//                            Toast.makeText(RegisterActivity.this, "akses ditolak", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//            });
//    private boolean checkpermission(){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
//            return Environment.isExternalStorageManager();
//        }else{
//            int write = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            int read = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            return write == PackageManager.PERMISSION_GRANTED && read == PackageManager.PERMISSION_GRANTED;
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == STORAGE_PERMISSION_CODE){
//            if(grantResults.length > 0){
//                boolean write = grantResults[0]==PackageManager.PERMISSION_GRANTED;
//                boolean read = grantResults[1]==PackageManager.PERMISSION_GRANTED;
//                if(write && read){
//                    kondisi= true;
//                }else{
//                    kondisi = false;
//                }
//            }
//        }
//    }
}