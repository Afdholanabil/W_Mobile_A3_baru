package com.example.recyclick.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recyclick.Model.Home.DataItemSearch;
import com.example.recyclick.R;
import com.example.recyclick.detailBarangActiviy;

import java.security.PublicKey;
import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.SearchViewHolder> {
    Context context;
    List<DataItemSearch> item;

    public AdapterSearch(Context context,List<DataItemSearch> item){
        this.context = context;
        this.item = item;
    }
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater iflat = LayoutInflater.from(context);
        View view = iflat.inflate(R.layout.recycleview_search, parent, false);
        SearchViewHolder search = new SearchViewHolder(view);
        return search;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        try{
            DataItemSearch barang = item.get(position);

            holder.judulsearch.setText(barang.getNama());
            holder.idsearch.setText(barang.getId());
            holder.stoksearch.setText(String.valueOf(barang.getStok()));
            holder.hargasearch.setText(String.valueOf(barang.getHarga()));
            holder.deskripsi.setText(barang.getDeskripsi());
            holder.kategori.setText(String.valueOf(barang.getKategori()));
            holder.rating.setText(String.valueOf(barang.getRating()));
            holder.urlGambar = barang.getGambar();

            Glide.with(context).load("https://workshopjti.com/RecyclickA3/"+barang.getGambar()).thumbnail(0.5f).centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.photo_library_48px).into(holder.gambar);
        }catch (Exception e){
            Log.d(e.getMessage(), "onBindViewHolder: ");
        }

    }

    @Override
    public int getItemCount() {
        return (item != null) ? item.size() : 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        public TextView judulsearch, idsearch, stoksearch, hargasearch,kategori,rating,deskripsi;
        public LinearLayout con;
        public ImageView gambar;
        String urlGambar;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            judulsearch = itemView.findViewById(R.id.recysrh_judul);
//            judulsearch = itemView.findViewById(R.id.jurul);
            idsearch = itemView.findViewById(R.id.recysrh_id);
            stoksearch = itemView.findViewById(R.id.recysrh_stok);
            hargasearch = itemView.findViewById(R.id.recysrh_harga);
            kategori = itemView.findViewById(R.id.kat);
            rating = itemView.findViewById(R.id.rat);
            gambar = itemView.findViewById(R.id.imgSearch);
            deskripsi = itemView.findViewById(R.id.desk);
            con = itemView.findViewById(R.id.container);

            con.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String idBr = idsearch.getText().toString();
                    String namaBr = judulsearch.getText().toString();
                    int hargaBr  = Integer.parseInt(hargasearch.getText().toString());
                    String deskBr = deskripsi.getText().toString();
                    int stokBr = Integer.parseInt(stoksearch.getText().toString());
                    int ratingbr = Integer.parseInt(kategori.getText().toString());
                    int jenisBr = Integer.parseInt(rating.getText().toString());
//
                    Intent intent = new Intent(context, detailBarangActiviy.class);
                    Toast.makeText(context, urlGambar, Toast.LENGTH_SHORT).show();
                    intent.putExtra("ID",idBr);
                    intent.putExtra("NAMAPROD",namaBr);
                    intent.putExtra("HARGAPROD",hargaBr);
                    intent.putExtra("DESKPROD",deskBr);
                    intent.putExtra("STOKPROD",stokBr);
                    intent.putExtra("RATINGPROD",ratingbr);
                    intent.putExtra("JENISPROD",jenisBr);
                    intent.putExtra("GAMBARPROD",urlGambar);
                    context.startActivity(intent);
                }
            });
        }
    }
}
