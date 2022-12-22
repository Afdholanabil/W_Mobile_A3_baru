package com.example.recyclick.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.Model.Kategori.KategoriItem;
import com.example.recyclick.R;
import com.example.recyclick.TambahBarangActivity;

import java.util.List;

public class AdapterKategoriTB extends RecyclerView.Adapter<AdapterKategoriTB.kategoritbViewholder> {

    private List<KategoriItem> item;
    public AdapterKategoriTB(List<KategoriItem> item){
        this.item = item;
    }
    @NonNull
    @Override
    public kategoritbViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflat = LayoutInflater.from(context);
        View daftarview = inflat.inflate(R.layout.recyleview_kategoritb, parent, false);
        kategoritbViewholder kvh = new kategoritbViewholder(daftarview);
        return kvh;
    }

    @Override
    public void onBindViewHolder(@NonNull kategoritbViewholder holder, int position) {
        KategoriItem kategori = item.get(position);
        holder.text.setText(kategori.getNamaKategori());
        holder.id = kategori.getId();

    }

    @Override
    public int getItemCount() {
        return (item != null)? item.size() : 0;
    }

    public class kategoritbViewholder extends RecyclerView.ViewHolder {
        TextView text;
        int id;
        public kategoritbViewholder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.recy_kgrtext);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String namakategori = text.getText().toString();
                    TambahBarangActivity.tba.setkategori(id, namakategori);
                    TambahBarangActivity.tba.closepopkategori();
                }
            });
        }
    }
}
