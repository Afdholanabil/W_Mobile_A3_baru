package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.recyclick.Adapter.barangAdapter;
import com.example.recyclick.Fragment.TambahBarangFragment;
import com.example.recyclick.Koneksi.dbBarang;
import com.example.recyclick.Model.brData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DataBarangActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<brData> listdata = new ArrayList<>();
    dbBarang data;
    FloatingActionButton tambahData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_barang);
        data = new dbBarang(getApplicationContext());
        recyclerView = (RecyclerView)findViewById(R.id.rcy_barang);
        tambahData = (FloatingActionButton) findViewById(R.id.btn_addBarang);
        tambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity();

            }
        });
        showDataBarang();
    }
    public void showDataBarang(){
        brData barang = null;
        SQLiteDatabase db = data.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM barang", null);
//        String []daftar = new String[cr.getCount()];
        cr.moveToFirst();
        for(int i = 0; i< cr.getCount(); i++){
            cr.moveToPosition(i);
            String id = cr.getString(0).toString();
            String nama = cr.getString(1).toString();
            String kategori = cr.getString(2).toString();
            int stok = cr.getInt(3);
            String deskripsi = cr.getString(4).toString();
            barang = new brData(id, nama, kategori, deskripsi, stok);
            listdata.add(barang);
        }

        barangAdapter adapter = new barangAdapter(listdata);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}