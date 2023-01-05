package com.example.recyclick.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recyclick.DataBarangActivity;
import com.example.recyclick.Model.DataBarang.DataItem;
import com.example.recyclick.R;
import com.example.recyclick.SaveAccount;
import com.example.recyclick.detailBarangActiviy;
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

        try {
            DataItem barang = data.get(position);
            holder.id.setText(barang.getId());
            holder.nama.setText(barang.getNama());
            holder.harga.setText(String.valueOf(barang.getHarga()));
            holder.deskripsi.setText(barang.getDeskripsi());
            holder.jenis.setText(String.valueOf(barang.getKategori()));
            holder.stok.setText(String.valueOf(barang.getStok()));
            holder.rating.setText(String.valueOf(barang.getRating()));
            holder.urlgambar = barang.getGambardir();
            Glide.with(cntx).load("https://workshopjti.com/RecyclickA3/"+barang.getGambardir()).thumbnail(0.5f).centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.photo_library_48px).into(holder.gambarBarang);
        } catch (Exception e) {
            Log.d(e.getMessage(), "onBindViewHolder: ");
        }

    }

    @Override
    public int getItemCount() {
        int a;
        if (data != null) {
            a = data.size();
            DataBarangActivity.dbanew.hideteksKosong();
        } else {
            a = 0;
            DataBarangActivity.dbanew.setTeksDataKosong();
        }
        return a;
    }

    public class BarangViewHolder extends RecyclerView.ViewHolder{
        public TextView id, nama, deskripsi, harga, jenis, stok, gambar, rating;
        public CardView container;
        public ImageView gambarBarang;
        public String urlgambar;
        int roleDataBarang;

        @SuppressLint("ResourceAsColor")
        public BarangViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txt_id);
            nama = itemView.findViewById(R.id.txt_judul);
            harga = itemView.findViewById(R.id.txt_harga);
            gambarBarang = itemView.findViewById(R.id.img2);
            deskripsi = itemView.findViewById(R.id.txt_des);
            jenis = itemView.findViewById(R.id.txt_jenis);
            stok = itemView.findViewById(R.id.txt_stok);
            rating = itemView.findViewById(R.id.txt_rating);
            container = itemView.findViewById(R.id.crdContainer);

            roleDataBarang = SaveAccount.readDataPembeli(cntx).getUserRole();

            if (roleDataBarang == 1) {
                container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String idBr = id.getText().toString();
                        String namaBr = nama.getText().toString();
                        int hargaBr = Integer.parseInt(harga.getText().toString());
                        String deskBr = deskripsi.getText().toString();
                        int stokBr = Integer.parseInt(stok.getText().toString());
                        int jenisBr = Integer.parseInt(jenis.getText().toString());
                        int ratingbr = Integer.parseInt(rating.getText().toString());

                        Intent intent = new Intent(cntx, detailBarangActiviy.class);
                        Toast.makeText(cntx, urlgambar, Toast.LENGTH_SHORT).show();
                        intent.putExtra("ID", idBr);
                        intent.putExtra("NAMAPROD", namaBr);
                        intent.putExtra("HARGAPROD", hargaBr);
                        intent.putExtra("DESKPROD", deskBr);
                        intent.putExtra("STOKPROD", stokBr);
                        intent.putExtra("RATINGPROD", ratingbr);
                        intent.putExtra("JENISPROD", jenisBr);
                        intent.putExtra("GAMBARPROD", urlgambar);
                        cntx.startActivity(intent);
                    }
                });

//            this.onNoteListener = onNoteListener;
//            itemView.setOnClickListener(this);
                itemView.findViewById(R.id.br_edit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String idBr = id.getText().toString();
                        String namaBr = nama.getText().toString();
                        String hargaBr = harga.getText().toString();
                        String deskBr = deskripsi.getText().toString();
                        String stokBr = stok.getText().toString();
                        String jenisBr = jenis.getText().toString();
                        Intent intent = new Intent(cntx, new editBarangActivity().getClass());
                        intent.putExtra("ID", idBr);
                        intent.putExtra("NAMAPROD", namaBr);
                        intent.putExtra("HARGAPROD", hargaBr);
                        intent.putExtra("DESKPROD", deskBr);
                        intent.putExtra("STOKPROD", stokBr);
                        intent.putExtra("JENISPROD", jenisBr);
                        intent.putExtra("GAMBARPROD", urlgambar);
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
//                    ((ImageView) view2.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_help_24);

                        AlertDialog alertDialog = builder.create();

                        view2.findViewById(R.id.btnHapus).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String idItem = id.getText().toString();
                                DataBarangActivity.dbanew.hapusDataBarang(idItem, urlgambar);
                                alertDialog.dismiss();
                                cntx.startActivity(new Intent(cntx, DataBarangActivity.class));
                                DataBarangActivity.dbanew.tampilDataBarang();

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

            }else {
                itemView.findViewById(R.id.br_delete).setOnClickListener(null);
                itemView.findViewById(R.id.br_delete).setBackgroundColor(R.color.grey2);

                container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String idBr = id.getText().toString();
                        String namaBr = nama.getText().toString();
                        int hargaBr = Integer.parseInt(harga.getText().toString());
                        String deskBr = deskripsi.getText().toString();
                        int stokBr = Integer.parseInt(stok.getText().toString());
                        int jenisBr = Integer.parseInt(jenis.getText().toString());
                        int ratingbr = Integer.parseInt(rating.getText().toString());

                        Intent intent = new Intent(cntx, detailBarangActiviy.class);
                        Toast.makeText(cntx, urlgambar, Toast.LENGTH_SHORT).show();
                        intent.putExtra("ID", idBr);
                        intent.putExtra("NAMAPROD", namaBr);
                        intent.putExtra("HARGAPROD", hargaBr);
                        intent.putExtra("DESKPROD", deskBr);
                        intent.putExtra("STOKPROD", stokBr);
                        intent.putExtra("RATINGPROD", ratingbr);
                        intent.putExtra("JENISPROD", jenisBr);
                        intent.putExtra("GAMBARPROD", urlgambar);
                        cntx.startActivity(intent);
                    }
                });

//            this.onNoteListener = onNoteListener;
//            itemView.setOnClickListener(this);
                itemView.findViewById(R.id.br_edit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String idBr = id.getText().toString();
                        String namaBr = nama.getText().toString();
                        String hargaBr = harga.getText().toString();
                        String deskBr = deskripsi.getText().toString();
                        String stokBr = stok.getText().toString();
                        String jenisBr = jenis.getText().toString();
                        Intent intent = new Intent(cntx, new editBarangActivity().getClass());
                        intent.putExtra("ID", idBr);
                        intent.putExtra("NAMAPROD", namaBr);
                        intent.putExtra("HARGAPROD", hargaBr);
                        intent.putExtra("DESKPROD", deskBr);
                        intent.putExtra("STOKPROD", stokBr);
                        intent.putExtra("JENISPROD", jenisBr);
                        intent.putExtra("GAMBARPROD", urlgambar);
                        cntx.startActivity(intent);

                    }
                });
            }
        }

    }
}
