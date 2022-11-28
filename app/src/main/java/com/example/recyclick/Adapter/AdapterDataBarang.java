package com.example.recyclick.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.DataBarangActivity;
import com.example.recyclick.Model.DataBarang.DataItem;
import com.example.recyclick.R;
import com.example.recyclick.editBarangActivity;

import java.util.List;

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

    public class BarangViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView id, nama, deskripsi, harga;

        public BarangViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txt_id);
            nama = itemView.findViewById(R.id.txt_judul);
            harga = itemView.findViewById(R.id.txt_harga);
            deskripsi = itemView.findViewById(R.id.txt_des);
//            this.onNoteListener = onNoteListener;
//            itemView.setOnClickListener(this);
            itemView.findViewById(R.id.br_edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(cntx, new editBarangActivity().getClass());
                    cntx.startActivity(intent);

                }
            });
            itemView.findViewById(R.id.br_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext(), R.style.AlertDialog);
                    View view2 = LayoutInflater.from(view.getRootView().getContext()).inflate(
                            R.layout.layout_hapus_data, null);
                    builder.setView(view2);
                    ((TextView) view2.findViewById(R.id.textJudul)).setText("Apakah Anda Yakin Untuk Meng-HAPUS Produk ini ?");
                    ((TextView) view2.findViewById(R.id.textMessage)).setText("Klik Hapus untuk Menghapus produk !");
                    ((Button) view2.findViewById(R.id.btnHapus)).setText("Hapus");
                    ((Button) view2.findViewById(R.id.btnKembali)).setText("Kembali");
                    ((ImageView) view2.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_help_24);

                    AlertDialog alertDialog = builder.create();

                    view2.findViewById(R.id.btnHapus).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String idItem = id.getText().toString();
                            DataBarangActivity.dba.hapusDataBarang(idItem);
                            alertDialog.dismiss();


                        }
                    });
                    view2.findViewById(R.id.btnKembali).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                    if (alertDialog.getWindow() != null) {
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }
                    alertDialog.show();

                }
            });
        }

        @Override
        public void onClick(View view) {
//            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
}
