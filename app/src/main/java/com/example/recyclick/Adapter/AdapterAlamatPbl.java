package com.example.recyclick.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.Model.Pembeli.PembeliData;
import com.example.recyclick.R;
import com.example.recyclick.detailAlamatActivity;

import java.util.List;

public class AdapterAlamatPbl extends RecyclerView.Adapter<AdapterAlamatPbl.PembeliViewHolder> {
   Context context;
   List<PembeliData> data;


    public AdapterAlamatPbl(Context context, List<PembeliData> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PembeliViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflat = LayoutInflater.from(context);
        View daftarview = inflat.inflate(R.layout.recycleview_alamat_pbl, parent, false);
        AdapterAlamatPbl.PembeliViewHolder viewHolder = new AdapterAlamatPbl.PembeliViewHolder(daftarview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PembeliViewHolder holder, int position) {
        PembeliData pembeliData = data.get(position);
        holder.kab.setText(pembeliData.getKab());
        holder.kec.setText(pembeliData.getKec());
        holder.desa.setText(pembeliData.getDesa());
        holder.idAlamat.setText(pembeliData.getIdAlamat());
        holder.idPembeli.setText(pembeliData.getUserPbl());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PembeliViewHolder extends RecyclerView.ViewHolder {
        public TextView kab,kec,desa,tDetailAlamat,idAlamat,idPembeli;
        public PembeliViewHolder(@NonNull View itemView) {
            super(itemView);
            kab = itemView.findViewById(R.id.txtKab);
            kec = itemView.findViewById(R.id.txtKec);
            desa = itemView.findViewById(R.id.txtDesa);
            tDetailAlamat = itemView.findViewById(R.id.txtDetailAlamat);
            idAlamat = itemView.findViewById(R.id.txtidAlamat);
            idPembeli = itemView.findViewById(R.id.txtIdPbl);
            tDetailAlamat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = idAlamat.getText().toString();
                    String User = idPembeli.getText().toString();
                    Intent intent = new Intent(context, detailAlamatActivity.class);
                    intent.putExtra("IDALAMAT",id);
                    intent.putExtra("USERNAME",User);
                    context.startActivity(intent);
                }
            });
        }
    }
}
