package com.example.recyclick.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.Fragment.DataBarangFragment;
import com.example.recyclick.Fragment.LoginFragment;
import com.example.recyclick.Koneksi.dbBarang;
import com.example.recyclick.Model.brData;
import com.example.recyclick.R;
import com.example.recyclick.Util;

import java.util.List;

public class barangAdapter extends RecyclerView.Adapter<barangAdapter.myViewHolder>  {
    List<brData> daftar;
    dbBarang dbitem;

    public barangAdapter(List<brData> daftar) {
        this.daftar = daftar;
    }


    @NonNull
    @Override
    public barangAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflat = LayoutInflater.from(context);
        View daftarview = inflat.inflate(R.layout.receycleview_barang, parent, false);
        myViewHolder viewhold = new myViewHolder(daftarview);
        return viewhold;

    }

    @Override
    public void onBindViewHolder(@NonNull barangAdapter.myViewHolder holder, int position) {
        brData barang = daftar.get(position);

        holder.id.setText(barang.getId());
        holder.nama.setText(barang.getNama());
        holder.stok.setText(String.valueOf(barang.getStok()));
        holder.deskripsi.setText(barang.getDeskripsi());

    }

    @Override
    public int getItemCount() {
        return daftar.size();

    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        public TextView id, nama, jenis, stok, deskripsi;
        public CardView edit, hapus;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            dbitem = new dbBarang(itemView.getContext());
            id = itemView.findViewById(R.id.txt_id);
            nama = itemView.findViewById(R.id.txt_judul2);
            stok = itemView.findViewById(R.id.txt_harga2);
            deskripsi = itemView.findViewById(R.id.txt_des2);
            edit = itemView.findViewById(R.id.br_edit);
            itemView.findViewById(R.id.br_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String idItem = id.getText().toString();
                    SQLiteDatabase db = dbitem.getWritableDatabase();
                    db.execSQL("DELETE FROM barang WHERE idbarang = '" + idItem + "'");
                    Toast.makeText(view.getContext(), "Barang berhasil di hapus id : " + idItem, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

}

