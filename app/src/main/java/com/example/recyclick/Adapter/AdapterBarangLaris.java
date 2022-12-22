package com.example.recyclick.Adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recyclick.Model.DataBarang.BarangLaris;
import com.example.recyclick.Model.DataBarang.DataItem;
import com.example.recyclick.R;
import com.example.recyclick.detailBarangActiviy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterBarangLaris extends RecyclerView.Adapter<AdapterBarangLaris.LarisViewHolder> {
    Context context;
    List<DataItem> data;

    public AdapterBarangLaris(Context context, List<DataItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public LarisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflat = LayoutInflater.from(context);
        View daftarview = inflat.inflate(R.layout.recycleview_barang_laris, parent, false);
        LarisViewHolder viewHolder = new LarisViewHolder(daftarview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LarisViewHolder holder, int position) {
        try{
            DataItem barang = data.get(position);
            holder.id.setText(barang.getId());
            holder.nama.setText(barang.getNama());
            holder.harga.setText(String.valueOf(barang.getHarga()));
            holder.rating.setText(String.valueOf(barang.getRating()));
            holder.stok.setText(String.valueOf(barang.getStok()));
            holder.jenis.setText(String.valueOf(barang.getKategori()));
            holder.deskripsi.setText(barang.getDeskripsi());

            holder.urlgambarLaris =barang.getGambardir();

            Glide.with(context).load(barang.getGambardir()).thumbnail(0.5f).centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.photo_library_48px).into(holder.gambarBarangLaris);


        }catch (Exception e){
            Log.d(e.getMessage(), "onBindViewHolderLaris: ");
        }
    }

    @Override
    public int getItemCount() {
        return (data == null) ? 0 : data.size();
    }

    public class LarisViewHolder extends RecyclerView.ViewHolder {
        TextView id, nama, deskripsi, harga,jenis,stok,rating;
        public CardView container;
        public ImageView gambarBarangLaris;
        String urlgambarLaris;
        public LarisViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txtIdlaris);
            nama = itemView.findViewById(R.id.txtNamaProdlaris);
            deskripsi = itemView.findViewById(R.id.txtDeskLaris);
            harga = itemView.findViewById(R.id.txtHargaLaris);
            stok = itemView.findViewById(R.id.txtStokLaris);
            rating = itemView.findViewById(R.id.txtRatingLaris);
            jenis = itemView.findViewById(R.id.txtJenisLaris);
            gambarBarangLaris = itemView.findViewById(R.id.imgBarangLaris);
            container = itemView.findViewById(R.id.crd1Laris);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   String idBr = id.getText().toString();
                   String namaBr = nama.getText().toString();
                   int hargaBr = Integer.parseInt(harga.getText().toString());
                   int stokBr = Integer.parseInt(stok.getText().toString());
                   int jenisBr = Integer.parseInt(jenis.getText().toString());
                   int ratingbr = Integer.parseInt(rating.getText().toString());
                   String deskBr = deskripsi.getText().toString();

                    Intent intent = new Intent(context, detailBarangActiviy.class);
                    Toast.makeText(context, urlgambarLaris, Toast.LENGTH_SHORT).show();
                    intent.putExtra("ID",idBr);
                    intent.putExtra("NAMAPROD",namaBr);
                    intent.putExtra("HARGAPROD",hargaBr);
                    intent.putExtra("DESKPROD",deskBr);
                    intent.putExtra("STOKPROD",stokBr);
                    intent.putExtra("RATINGPROD",ratingbr);
                    intent.putExtra("JENISPROD",jenisBr);
                    intent.putExtra("GAMBARPROD",urlgambarLaris);
                    context.startActivity(intent);
                }
            });
        }
    }
}
