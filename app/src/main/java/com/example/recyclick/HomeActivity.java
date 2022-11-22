package com.example.recyclick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.recyclick.Fragment.DataBarangFragment;
import com.example.recyclick.Fragment.HomeFragment;
import com.example.recyclick.Fragment.LaporanPenjualanFragment;
import com.example.recyclick.Fragment.PengaturanFragment;
import com.example.recyclick.Koneksi.dbHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    String waktu,j;
    Cursor cursor;
    private TextView greeting;
    public dbHelper dbhelp;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbhelp = new dbHelper(getApplicationContext());
        greeting = (TextView)findViewById(R.id.textHalo);
        setGreeting();

        navigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.id_nav_home:
                        startActivity(new Intent(HomeActivity.this, new HomeActivity().getClass()));
                        break;
                    case R.id.id_nav_edit:
                        startActivity(new Intent(HomeActivity.this, new DataBarangActivity().getClass()));
                        break;
                    case R.id.id_nav_laporan:
                        startActivity(new Intent(HomeActivity.this, new LoginActivity().getClass()));
                        break;
                    case R.id.id_nav_setting:
                        startActivity(new Intent(HomeActivity.this, new PengaturanActivity().getClass()));
                        break;
                }
                return false;
            }
        });
    }
    public void setGreeting() {
        //set waktu
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);


        if (hour <= 6 || hour <= 11) {
            waktu = " pagi ";
        } else if (hour <= 17) {
            waktu = " Siang  ";
        } else if (hour <= 24) {
            waktu = " Malam ";
        }

        greeting = (TextView) findViewById(R.id.textHalo);
        SQLiteDatabase db = dbhelp.getWritableDatabase();
        cursor = db.rawQuery("SELECT name FROM Admin WHERE username = '" + getIntent().getStringExtra("name") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            greeting.setText("Selamat" + waktu + "" + cursor.getString(0).toString());
        }

    }

}