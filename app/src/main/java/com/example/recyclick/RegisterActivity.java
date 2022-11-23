package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Fragment.LoginFragment;
import com.example.recyclick.Fragment.RegisterFragment;
import com.example.recyclick.Model.Register.RegisterInfo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextView btnKembali;
    CardView btnRegist;
    TextInputEditText txtNama, txtUsername, txtTelp;
    EditText txtPass,txtKonfirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtNama = (TextInputEditText) findViewById(R.id.inputText);
        txtUsername = (TextInputEditText) findViewById(R.id.inputText3);
        txtTelp = (TextInputEditText) findViewById(R.id.inputText2);
        txtPass = (EditText) findViewById(R.id.inputText4);
        txtKonfirm = (EditText) findViewById(R.id.inputText5);

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

                if(nama.equals("")||noTlp.equals("")||usr.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(getApplicationContext(),"Data Kosong, Harus Diisi ! ",Toast.LENGTH_LONG).show();
                }else {
                    if (usr.length() > 15) {
                        Toast.makeText(getApplicationContext(), "Username Tidak Boleh lebih dari 15 karakter", Toast.LENGTH_SHORT).show();
                    }else if (pass.length() > 10) {
                        Toast.makeText(getApplicationContext(), "Password tidak boleh lebih dari 10 karakter", Toast.LENGTH_SHORT).show();
                    }else if (pass.equals(repass)) {
                        RegisterPost(usr, pass, nama, noTlp, kedudukan);
                    } else {
                        Toast.makeText(getApplicationContext(), "Masukan Password yang Sesuai", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
    }

    private void RegisterPost(String user, String pass, String nama, String notel, int kedudukan){
        APIRequestData API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<RegisterInfo> call = API.CreateRegisterPost(user, pass, nama, notel, kedudukan);
        call.enqueue(new Callback<RegisterInfo>() {
            @Override
            public void onResponse(Call<RegisterInfo> call, Response<RegisterInfo> response) {
                if(response.isSuccessful() && response.body() != null){
                    if(response.body().isKondisi() == true){
                        Toast.makeText(RegisterActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterInfo> call, Throwable t) {
                Log.d("server error", "onFailure: "+t);
            }
        });
    }
}