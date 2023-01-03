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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recyclick.Fragment.DataKaryawanFragment;
import com.example.recyclick.Fragment.LoginFragment;
import com.example.recyclick.Fragment.TambahBarangFragment;
import com.example.recyclick.Fragment.TambahKaryawanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class PengaturanActivity extends AppCompatActivity {
public TextView tNama,tUsername,tpass,tRole;
ImageView gambarProfil;
BottomNavigationView navigationView;
int rolePengaturan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        tNama = (TextView)findViewById(R.id.txt_nama);
        tUsername = (TextView)findViewById(R.id.txt_username);
        tpass= findViewById(R.id.txt_pass);
        gambarProfil = findViewById(R.id.imgProfil);
        tRole = findViewById(R.id.txt_role);

        //role2



        rolePengaturan = SaveAccount.readDataPembeli(PengaturanActivity.this).getUserRole();
//        tRole.setText(SaveAccount.readDataPembeli(PengaturanActivity.this).getUserRole());



        navigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        //navigator
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.id_nav_home:
                        Intent intent = new Intent(PengaturanActivity.this, HomeActivity.class);

                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities

                        startActivity(intent);
                        break;
                    case R.id.id_nav_edit:
                        startActivity(new Intent(PengaturanActivity.this, new DataBarangActivity().getClass()));

                        break;
                    case R.id.id_nav_laporan:
                        if(rolePengaturan == 1){
                            startActivity(new Intent(PengaturanActivity.this, new LaporanActivity().getClass()));
                            break;

                        }else {
                            Toast.makeText(PengaturanActivity.this, "Anda Tidak Dapat Menggunakan Fitur Ini !", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    case R.id.id_nav_setting:
                        startActivity(new Intent(PengaturanActivity.this, new PengaturanActivity().getClass()));

                        break;
                }
                return false;
            }
        });

        String gambarProf =(SaveAccount.readDataPembeli(PengaturanActivity.this).getPhoto());
//        Toast.makeText(this,gambarProf, Toast.LENGTH_SHORT).show();
        Glide.with(getApplicationContext()).load("https://workshopjti.com/RecyclickA3/"+gambarProf).thumbnail(0.5f).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.photo_library_48px).into(gambarProfil);


        tNama.setText(SaveAccount.readDataPembeli(PengaturanActivity.this).getNama());
        tUsername.setText(SaveAccount.readDataPembeli(PengaturanActivity.this).getUsername());
        tpass.setText(SaveAccount.readDataPembeli(PengaturanActivity.this).getPass());

        if(rolePengaturan == 1){
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
                    if(rolePengaturan == 1){
                        startActivity(new Intent(PengaturanActivity.this, new TambahKaryawanActivity().getClass()));
                    }else {
                        findViewById(R.id.linear5).setVisibility(View.GONE);

                    }


                }
            });
            findViewById(R.id.linear6).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        startActivity(new Intent(PengaturanActivity.this, new DataKaryawanActivity().getClass()));

                }
            });




        }else {
            findViewById(R.id.linear3).setVisibility(View.GONE);
            findViewById(R.id.linear5).setVisibility(View.GONE);
            findViewById(R.id.linear6).setVisibility(View.GONE);

        }

        findViewById(R.id.linear2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passLogin = tpass.getText().toString();
                String namaUsr = tNama.getText().toString();
                String username = tUsername.getText().toString();
                Intent intent2 = new Intent(PengaturanActivity.this, new EditProfileActivity().getClass());
                intent2.putExtra("PASS",passLogin);
                intent2.putExtra("GAMBARPROF",gambarProf);
                intent2.putExtra("NAMAPROF",namaUsr);
                intent2.putExtra("USERNAMEPROF",username);
                startActivity(intent2);
            }
        });

        findViewById(R.id.linear4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PengaturanActivity.this, KontakActivity.class));
            }
        });

        findViewById(R.id.linear8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogOutDialog();
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
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                SharedPreferences sharedPreferences = getSharedPreferences("PREF_MODEL", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                intent.putExtra("USERNAME", String.valueOf(tUsername));
                startActivity(intent);
//                boolean finish = getIntent().getBooleanExtra("finish", false);
//                if (finish) {
//                    startActivity(new Intent(PengaturanActivity.this, LoginActivity.class));
//                    finish();
//                    return;
//                }



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