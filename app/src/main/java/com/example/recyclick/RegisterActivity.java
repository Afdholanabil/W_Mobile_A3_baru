package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclick.Fragment.LoginFragment;
import com.example.recyclick.Fragment.RegisterFragment;
import com.example.recyclick.Koneksi.dbHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    TextView btnKembali;
    dbHelper db;
    CardView btnRegist;
    TextInputEditText txtNama, txtUsername, txtTelp;
    EditText txtPass,txtKonfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new dbHelper(getApplicationContext());
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
                String kedudukan = "karyawan";

                if(nama.equals("")||noTlp.equals("")||usr.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(getApplicationContext(),"Data Kosong, Harus Diisi ! ",Toast.LENGTH_LONG).show();

                }else {
                    if (usr.length() > 15) {
                        Toast.makeText(getApplicationContext(), "Username Tidak Boleh lebih dari 15 karakter", Toast.LENGTH_SHORT).show();
                    }else if (pass.length() > 10) {
                        Toast.makeText(getApplicationContext(), "Password tidak boleh lebih dari 10 karakter", Toast.LENGTH_SHORT).show();

                    }else if (pass.equals(repass)) {
                        boolean checkuser = db.checkUsername(usr);
                        if (checkuser == false) {
                            boolean insert = db.insertData(usr, pass, nama, noTlp, kedudukan);
                            if (insert == true) {
                                startActivity(new Intent(RegisterActivity.this, new LoginActivity().getClass()));
                                Toast.makeText(getApplicationContext(), "Berhasil Daftar", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(getApplicationContext(), "Gagal Daftar", Toast.LENGTH_LONG).show();
                                txtUsername.setText("");
                                txtPass.setText("");
                                txtKonfirm.setText("");
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "username sudah digunakan", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Masukan Password yang Sesuai", Toast.LENGTH_LONG).show();

                    }

                }

            }
        });


    }
}