package com.example.recyclick.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.Fragment.DataBarangFragment;
import com.example.recyclick.Fragment.TambahBarangFragment;
import com.example.recyclick.Koneksi.dbHelper;
import com.example.recyclick.Koneksi.dbKaryawan;
import com.example.recyclick.Model.brKaryawan;
import com.example.recyclick.R;
import com.example.recyclick.Util;

import org.w3c.dom.Text;

import java.util.List;

public class karyawanAdapter extends RecyclerView.Adapter<karyawanAdapter.myViewHolder> {
List<brKaryawan> daftar ;
dbHelper dbitem;

    public karyawanAdapter(List<brKaryawan> daftar) {
        this.daftar = daftar;
    }

    @NonNull
    @Override
    public karyawanAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View daftarView = inflater.inflate(R.layout.recycleview_karyawan,parent,false);
        myViewHolder viewHolder = new myViewHolder(daftarView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        brKaryawan karyawan = daftar.get(position);
        holder.usernameKaryawan.setText(karyawan.getUsername());
        holder.pass.setText(karyawan.getPassword());
        holder.nama.setText(karyawan.getNama());
        holder.noTelp.setText(String.valueOf(karyawan.getNoTelp()));
        holder.kedudukan.setText(String.valueOf(karyawan.getKedudukan()));
    }

    @Override
    public int getItemCount() {
        return daftar.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {
        public TextView nama,noTelp,usernameKaryawan,pass,kedudukan;
        public CardView edit,hapus;


        public myViewHolder(View itemview){
            super(itemview);
            dbitem = new dbHelper(itemview.getContext());
            usernameKaryawan =itemview.findViewById(R.id.txt_username);
            nama = itemview.findViewById(R.id.txt_nama);
            noTelp = itemview.findViewById(R.id.txt_telp);
            pass = itemview.findViewById(R.id.txt_pass);
            kedudukan = itemview.findViewById(R.id.txt_kedudukan);
            edit = itemview.findViewById(R.id.br_edit);
            itemview.findViewById(R.id.br_edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            itemview.findViewById(R.id.br_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String userKaryawan = usernameKaryawan.getText().toString();
                    SQLiteDatabase db = dbitem.getWritableDatabase();
                    db.execSQL("DELETE FROM Admin WHERE username = '"+ userKaryawan +"'");
                    Toast.makeText(view.getContext(), "Data Karyawan Berhasil Dihapus dengan Username = " + userKaryawan, Toast.LENGTH_SHORT).show();

                }
            });
        }


    }
}
