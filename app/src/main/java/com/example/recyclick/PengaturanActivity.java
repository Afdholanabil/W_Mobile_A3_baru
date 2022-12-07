package com.example.recyclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclick.Fragment.DataKaryawanFragment;
import com.example.recyclick.Fragment.LoginFragment;
import com.example.recyclick.Fragment.TambahBarangFragment;
import com.example.recyclick.Fragment.TambahKaryawanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class PengaturanActivity extends AppCompatActivity {
public TextView tNama,tUsername,tpass;
BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        tNama = (TextView)findViewById(R.id.txt_nama);
        tUsername = (TextView)findViewById(R.id.txt_username);
        tpass= findViewById(R.id.txt_pass);
        navigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        //navigator
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.id_nav_home:
                        startActivity(new Intent(PengaturanActivity.this, new HomeActivity().getClass()));
                        break;
                    case R.id.id_nav_edit:
                        startActivity(new Intent(PengaturanActivity.this, new DataBarangActivity().getClass()));

                        break;
                    case R.id.id_nav_laporan:
                        startActivity(new Intent(PengaturanActivity.this, new LoginActivity().getClass()));
                        break;
                    case R.id.id_nav_setting:
                        startActivity(new Intent(PengaturanActivity.this, new PengaturanActivity().getClass()));

                        break;
                }
                return false;
            }
        });
        tNama.setText(SaveAccount.readDataPembeli(PengaturanActivity.this).getNama());
        tUsername.setText(SaveAccount.readDataPembeli(PengaturanActivity.this).getUsername());
        tpass.setText(SaveAccount.readDataPembeli(PengaturanActivity.this).getPass());

        findViewById(R.id.linear2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passLogin = tpass.getText().toString();
                Intent intent2 = new Intent(PengaturanActivity.this, new EditProfileActivity().getClass());
                intent2.putExtra("PASS",passLogin);
                startActivity(intent2);
            }
        });

        findViewById(R.id.linear8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogOutDialog();
            }
        });
        findViewById(R.id.linear3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PengaturanActivity.this, new TambahBarangActivity().getClass());
                startActivity(intent);

            }
        });
        findViewById(R.id.linear5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PengaturanActivity.this, new TambahKaryawanActivity().getClass()));

            }
        });
        findViewById(R.id.linear6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PengaturanActivity.this, new DataKaryawanActivity().getClass()));
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.container_logout,new DataKaryawanFragment()).commit();
            }
        });

    }
    private void showLogOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PengaturanActivity.this, R.style.AlertDialog);
        View view = LayoutInflater.from(PengaturanActivity.this).inflate(
                R.layout.layout_logout_dialog,(ConstraintLayout)findViewById(R.id.layoutDialogContainer));
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textJudul)).setText("Apakah Yakin Untuk LogOut ?");
        ((TextView) view.findViewById(R.id.textMessage)).setText("Klik Logout untuk Logout");
        ((Button) view.findViewById(R.id.btnOut)).setText("LogOut");
        ((Button) view.findViewById(R.id.btnKembali)).setText("Kembali");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_help_24);

        AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btnOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PengaturanActivity.getSingleInstance().setLoggingOut(true);
                Intent intent = new Intent(PengaturanActivity.this, new LoginActivity().getClass());
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                SharedPreferences sharedPreferences = getSharedPreferences("PREF_MODEL", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                intent.putExtra("USERNAME", String.valueOf(tUsername));
                editor.clear();
                editor.apply();
                startActivity(intent);

                finish();



            }
        });
        view.findViewById(R.id.btnKembali).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}