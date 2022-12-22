package com.example.recyclick.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.Model.Kategori.KategoriEditData;
import com.example.recyclick.Model.Kategori.KategoriItem;
import com.example.recyclick.R;
import com.example.recyclick.TambahBarangActivity;
import com.example.recyclick.editBarangActivity;

import java.util.List;

public class AdapterKategoriEB extends RecyclerView.Adapter<AdapterKategoriEB.kategoriebViewHolder>{

    private List<KategoriEditData> item;

    public AdapterKategoriEB(List<KategoriEditData> item) {
        this.item = item;
    }

    @NonNull
    @Override
    public kategoriebViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflat = LayoutInflater.from(context);
        View daftarview = inflat.inflate(R.layout.recyleview_kategoritb, parent, false);
        kategoriebViewHolder kvh = new kategoriebViewHolder(daftarview);
        return kvh;
    }

    @Override
    public void onBindViewHolder(@NonNull kategoriebViewHolder holder, int position) {
        KategoriEditData kategori = item.get(position);
        holder.text.setText(kategori.getNamaKategori());
        holder.id = kategori.getId();
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class kategoriebViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        int id;
        public kategoriebViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.recy_kgrtext);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String namakategori = text.getText().toString();
                    editBarangActivity.eba.setkategori(id, namakategori);
                    editBarangActivity.eba.closepopkategori();


                }
            });
        }
    }
}
