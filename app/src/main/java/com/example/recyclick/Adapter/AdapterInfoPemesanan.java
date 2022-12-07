package com.example.recyclick.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.DetailPemesananActivity;
import com.example.recyclick.Model.Transaksi.transaksiData;
import com.example.recyclick.R;

import java.util.List;

public class AdapterInfoPemesanan extends RecyclerView.Adapter<AdapterInfoPemesanan.InfoPemesananViewHolder> {
    Context context;
    List<transaksiData> data;

    public AdapterInfoPemesanan(Context context, List<transaksiData> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public InfoPemesananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflat = LayoutInflater.from(context);
        View daftarview = inflat.inflate(R.layout.recycleview_info_tr, parent, false);
        AdapterInfoPemesanan.InfoPemesananViewHolder viewHolder = new AdapterInfoPemesanan.InfoPemesananViewHolder(daftarview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoPemesananViewHolder holder, int position) {
        transaksiData transaksii = data.get(position);
        holder.txtidTran.setText(transaksii.getIdTransaksi());
        holder.txtUsername.setText(transaksii.getUsernamePembeli());
        holder.txtStatus.setText(transaksii.getNamaStatus());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class InfoPemesananViewHolder extends RecyclerView.ViewHolder {
        public TextView txtidTran,txtUsername,txtStatus;
        public LinearLayout lnrContainer;
        public InfoPemesananViewHolder(@NonNull View itemView) {
            super(itemView);
            txtidTran = itemView.findViewById(R.id.txt_idTrans);
            txtUsername = itemView.findViewById(R.id.txt_username);
            txtStatus = itemView.findViewById(R.id.txt_status);
            lnrContainer = itemView.findViewById(R.id.lnr1);

            itemView.findViewById(R.id.lnr1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String idTr = txtidTran.getText().toString();
                    String username = txtUsername.getText().toString();
                    Intent intent = new Intent(context, DetailPemesananActivity.class);
                    intent.putExtra("ID",idTr);
                    intent.putExtra("USERNAME",username);
                    context.startActivity(intent);
                }
            });
        }
    }
}
