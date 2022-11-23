package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Fragment.BottomNavFragment;
import com.example.recyclick.Fragment.ContainerFragment;
import com.example.recyclick.Fragment.RegisterFragment;
import com.example.recyclick.Koneksi.dbHelper;
import com.example.recyclick.Model.Login.LoginInfo;
import com.example.recyclick.Notifikasi.GagalLogin;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    dbHelper db;
    TextView tRegist;
    TextInputEditText tInput;
    EditText tPass;
    Button btnMsk;
    APIRequestData API;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tInput = (TextInputEditText) findViewById(R.id.inputText);
        tPass = (EditText) findViewById(R.id.inputPass);
        tRegist = (TextView)findViewById(R.id.text_regist) ;
        btnMsk = (Button)findViewById(R.id.btn_msk);
        btnMsk.setEnabled(false);
        db = new dbHelper(getApplicationContext());
        hideActionBar();
        TextWatcher loginTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String inputUsername = tInput.getText().toString();
                String inputPass = tPass.getText().toString();

                btnMsk.setEnabled(!inputUsername.isEmpty() && !inputPass.isEmpty());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        tInput.addTextChangedListener(loginTextWatcher);
        tPass.addTextChangedListener(loginTextWatcher);

        findViewById(R.id.btn_msk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String usr = tInput.getText().toString();
                    String pass = tPass.getText().toString();
                    if(usr.equals("")||pass.equals("")){
                        showLogOutDialog();
                    }else{
                        loginPost(usr, pass);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        tRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, new RegisterActivity().getClass()));


            }
        });
    }

    private void loginPost(String username, String password){
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<LoginInfo> call = API.CreateLoginPost(username, password);
        call.enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                if(response.body() != null && response.isSuccessful()){
                    if(response.body().getKondisi() == 1){
                        Intent itn = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(itn);
                    }else{
                        Log.e("TAG", "onResponse: "+response.body().getPesan());
                        showLogOutDialog();
                    }
                }else{
                    Log.e("TAG", "onResponse: "+response.body().getPesan());
                    showLogOutDialog();
                }
            }

            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {
                Log.e("error server", "onFailure: "+t);
            }
        });
    }

    private void showLogOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this, R.style.AlertDialog);
        View view = LayoutInflater.from(LoginActivity.this).inflate(
                R.layout.layout_error_login,(ConstraintLayout)findViewById(R.id.layoutDialogContainer));
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textJudul)).setText("Ada yang salah ketika Login !");
        ((TextView) view.findViewById(R.id.textMessage)).setText("Periksa kembali Username dan Password anda !");
        ((Button) view.findViewById(R.id.btnAction)).setText("Kembali");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_error_24);

        AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btnAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tPass.setText("");
                alertDialog.dismiss();;
            }
        });

        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    public void hideActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

}