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

import com.example.recyclick.Fragment.BottomNavFragment;
import com.example.recyclick.Fragment.ContainerFragment;
import com.example.recyclick.Fragment.RegisterFragment;
import com.example.recyclick.Koneksi.dbHelper;
import com.example.recyclick.Notifikasi.GagalLogin;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    dbHelper db;
    TextView tRegist;
    TextInputEditText tInput;
    EditText tPass;
    Button btnMsk;

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
                    if (usr.equals("") || pass.equals("")) {
                        showLogOutDialog();
                    } else {
                        Boolean checkUserPass = db.checkUsernamePassword(usr, pass);
                        if (checkUserPass == true) {
                            Intent intent = new Intent(LoginActivity.this, new BaseActivity().getClass());
                            SharedPreferences prefs = LoginActivity.this.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
                            prefs.edit().putString("name",usr).apply();
                            startActivity(intent);
                            finish();
                        } else {
                            showLogOutDialog();
                        }
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
//    private void loadFragment(Fragment fragment) {
//// create a FragmentManager
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentManager fm = getFragmentManager();
//// create a FragmentTransaction to begin the transaction and replace the Fragment
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//// replace the FrameLayout with new Fragment
//        fragmentTransaction.replace(R.id.layoutFragment, fragment);
//        fragmentTransaction.commit(); // save the changes
//    }
}