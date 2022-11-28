package com.example.recyclick.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.Model.DataKaryawan.KaryawanItem;
import com.example.recyclick.Model.Kategori.KategoriItem;
import com.example.recyclick.R;

import java.util.ConcurrentModificationException;
import java.util.List;

public class AdapterKategori extends RecyclerView.Adapter<AdapterKategori.KategoriViewHolder> {
    Context context;
    List<KategoriItem> data;

    public AdapterKategori(Context context,List<KategoriItem> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public KategoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflat = LayoutInflater.from(context);
        View daftarview = inflat.inflate(R.layout.recycleview_kategori, parent, false);
        AdapterKategori.KategoriViewHolder viewHolder = new AdapterKategori.KategoriViewHolder(daftarview);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull KategoriViewHolder holder, int position) {
        KategoriItem kategoriItem = data.get(position);
        holder.id.setText(kategoriItem.getId());
        holder.nama.setText(kategoriItem.getNamaKategori());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class KategoriViewHolder extends RecyclerView.ViewHolder {
        public TextView id,nama;
        public KategoriViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txt_id);
            nama = itemView.findViewById(R.id.txt_kategori);
            nama.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }

    }
    public interface itemListed{

    }
}
