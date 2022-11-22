package com.example.recyclick.Adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.Model.ModelDataBarang;
import com.example.recyclick.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.BarangViewHolder> {
    private List<ModelDataBarang> barangList = new ArrayList<>();

    public AdapterBarang(List<ModelDataBarang> barangList){
        this.barangList = barangList;

    }

    @NonNull
    @Override
    public AdapterBarang.BarangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_barang,parent,true);
        BarangViewHolder barangViewHolder = new BarangViewHolder(view);
        return barangViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterBarang.BarangViewHolder holder, int position) {
        holder.txt_idBarang.setText(barangList.get(position).getNama());
        holder.txt_hargaBarang.setText(barangList.get(position).getHarga());

    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }

    public static class BarangViewHolder extends RecyclerView.ViewHolder {
        TextView txt_idBarang;
        TextView txt_hargaBarang;
        public BarangViewHolder(View itemView) {
            super(itemView);

            txt_idBarang = (TextView) itemView.findViewById(R.id.txt_idBarang);
            txt_hargaBarang = (TextView) itemView.findViewById(R.id.txt_hargaBarang);

        }
    }
    
    public void onAttachedToRecycleView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}
