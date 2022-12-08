package com.example.recyclick.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.Model.Home.DataItemSearch;
import com.example.recyclick.R;

import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.SearchViewHolder> {
    List<DataItemSearch> item;

    public AdapterSearch(List<DataItemSearch> item){
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
        DataItemSearch barang = item.get(position);

        holder.judulsearch.setText(barang.getNama());
        holder.idsearch.setText(barang.getId());
        holder.stoksearch.setText(String.valueOf(barang.getStok()));
        holder.hargasearch.setText(String.valueOf(barang.getHarga()));

    }

    @Override
    public int getItemCount() {
        return (item != null) ? item.size() : 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        public TextView judulsearch, idsearch, stoksearch, hargasearch;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            judulsearch = itemView.findViewById(R.id.recysrh_judul);
//            judulsearch = itemView.findViewById(R.id.jurul);
            idsearch = itemView.findViewById(R.id.recysrh_id);
            stoksearch = itemView.findViewById(R.id.recysrh_stok);
            hargasearch = itemView.findViewById(R.id.recysrh_harga);
        }
    }
}
