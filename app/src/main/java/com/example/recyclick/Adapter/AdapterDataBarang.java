package com.example.recyclick.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.DataBarangActivity;
import com.example.recyclick.HomeActivity;
import com.example.recyclick.Model.DataBarang.DataItem;
import com.example.recyclick.Model.DataBarang.DeleteBarang;
import com.example.recyclick.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDataBarang extends RecyclerView.Adapter<AdapterDataBarang.BarangViewHolder> {
    Context cntx;
    List<DataItem> data;

    public AdapterDataBarang(Context cntx, List<DataItem> item) {
        this.cntx = cntx;
        this.data = item;
    }

    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflat = LayoutInflater.from(context);
        View daftarview = inflat.inflate(R.layout.receycleview_barang, parent, false);
        BarangViewHolder viewhold = new BarangViewHolder(daftarview);
        return viewhold;
    }

    @Override
    public void onBindViewHolder(@NonNull BarangViewHolder holder, int position) {
        DataItem barang = data.get(position);

        holder.id.setText(barang.getId());
        holder.nama.setText(barang.getNama());
        holder.harga.setText(String.valueOf(barang.getHarga()));
        holder.deskripsi.setText(barang.getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class BarangViewHolder extends RecyclerView.ViewHolder {
        public TextView id, nama, deskripsi, harga;

        public BarangViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txt_id);
            nama = itemView.findViewById(R.id.txt_judul);
            harga = itemView.findViewById(R.id.txt_harga);
            deskripsi = itemView.findViewById(R.id.txt_des);
            itemView.findViewById(R.id.br_edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            itemView.findViewById(R.id.br_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String idItem = id.getText().toString();
                    DataBarangActivity.dba.hapusDataBarang(idItem);
                }
            });
        }

    }
}
