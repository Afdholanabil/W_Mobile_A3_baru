package com.example.recyclick.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.Model.Transaksi.transaksiData;
import com.example.recyclick.Model.Transaksi.transaksiDataDetailAlamat;
import com.example.recyclick.R;

import java.util.List;

public class AdapterDetailPemesanan extends RecyclerView.Adapter<AdapterDetailPemesanan.DetailPemesananaViewHolder> {
    Context context;
    List<transaksiDataDetailAlamat> data;

    public AdapterDetailPemesanan(Context context, List<transaksiDataDetailAlamat> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public DetailPemesananaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflat = LayoutInflater.from(context);
        View daftarview = inflat.inflate(R.layout.recycleview_info_detail_pemesanan, parent, false);
        DetailPemesananaViewHolder viewHolder = new DetailPemesananaViewHolder(daftarview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailPemesananaViewHolder holder, int position) {
        transaksiDataDetailAlamat transaksi = data.get(position);
        holder.tIdTr.setText(transaksi.getIdProduk());
        holder.tnamaBarang.setText(transaksi.getNamaProduk());
        holder.tJumlah.setText(String.valueOf(transaksi.getQty()));
        holder.tTotalHarga.setText(String.valueOf(transaksi.getTotalHarga()));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class DetailPemesananaViewHolder extends RecyclerView.ViewHolder {
       public TextView tIdTr,tnamaBarang,tJumlah,tTotalHarga;
        public DetailPemesananaViewHolder(@NonNull View itemView) {
            super(itemView);
            tIdTr = itemView.findViewById(R.id.txtIdTransaksi);
            tnamaBarang = itemView.findViewById(R.id.txtidBarang);
            tJumlah = itemView.findViewById(R.id.txtQty);
            tTotalHarga = itemView.findViewById(R.id.txtTotal);

        }
    }
}
