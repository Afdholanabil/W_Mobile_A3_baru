package com.example.recyclick.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.LaporanLengkapActivity;
import com.example.recyclick.Model.Laporan.DataItemLengkap;
import com.example.recyclick.R;

import java.util.List;

public class AdapterLaporan extends RecyclerView.Adapter<AdapterLaporan.laporanViewHolder> {

    List<DataItemLengkap> item;
    int itemlenght;

    public AdapterLaporan(List<DataItemLengkap> item) {
        this.item = item;
        if (item != null) {
            itemlenght = item.size();
        }
    }

    @NonNull
    @Override
    public laporanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflat = LayoutInflater.from(context);
        View daftarview = inflat.inflate(R.layout.recycleview_laporan, parent, false);
        laporanViewHolder viewhold = new laporanViewHolder(daftarview);
        return viewhold;
    }

    @Override
    public void onBindViewHolder(@NonNull laporanViewHolder holder, int position) {
        DataItemLengkap itemlaporan = item.get(position);

        holder.idtr.setText(itemlaporan.getIdtrans());
        holder.idpbl.setText(itemlaporan.getIduser());
        holder.idbr.setText(itemlaporan.getIdprd());
        holder.nama.setText(itemlaporan.getNamabr());
        holder.jml.setText(itemlaporan.getJumlah());
        holder.ttlharga.setText(itemlaporan.getTotalharga());
        holder.tgl.setText(itemlaporan.getTanggal());
        int len = itemlenght - position;
        holder.numtrans.setText("Pembelian Ke-" + len);
    }

    @Override
    public int getItemCount() {
        if (item != null) {
            LaporanLengkapActivity.LLA.nullAlertNotif(false);
            return item.size();
        }
        LaporanLengkapActivity.LLA.nullAlertNotif(true);
        return 0;
    }

    public class laporanViewHolder extends RecyclerView.ViewHolder {
        TextView idtr, idbr, idpbl, nama, tgl, jml, ttlharga, numtrans;

        public laporanViewHolder(View itemView) {
            super(itemView);
            idtr = itemView.findViewById(R.id.rlc_idtransaksi);
            idpbl = itemView.findViewById(R.id.rlc_idpembeli);
            idbr = itemView.findViewById(R.id.rlc_idbarang);
            nama = itemView.findViewById(R.id.rlc_namapembeli);
            jml = itemView.findViewById(R.id.rlc_jumlah);
            ttlharga = itemView.findViewById(R.id.rlc_harga);
            tgl = itemView.findViewById(R.id.rlc_tanggal);
            numtrans = itemView.findViewById(R.id.titlenumber);
        }
    }
}
