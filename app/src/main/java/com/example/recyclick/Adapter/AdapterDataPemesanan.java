package com.example.recyclick.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.DataPemesananActivity;
import com.example.recyclick.DetailPemesananActivity;
import com.example.recyclick.HomeActivity;
import com.example.recyclick.Model.Transaksi.transaksiData;
import com.example.recyclick.R;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterDataPemesanan extends RecyclerView.Adapter<AdapterDataPemesanan.PemesananViewHolder> {
    Context context;
    List<transaksiData> data;

    public AdapterDataPemesanan(Context context, List<transaksiData> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PemesananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflat = LayoutInflater.from(context);
        View daftarview = inflat.inflate(R.layout.recycleview_pemesanan, parent, false);
        AdapterDataPemesanan.PemesananViewHolder viewHolder = new AdapterDataPemesanan.PemesananViewHolder(daftarview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PemesananViewHolder holder, int position) {
        transaksiData transaksi = data.get(position);
        holder.idTrans.setText(transaksi.getIdTransaksi());
        holder.username.setText(transaksi.getUsernamePembeli());
        holder.tanggal.setText(transaksi.getTanggal());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PemesananViewHolder extends RecyclerView.ViewHolder {
        public TextView idTrans,username,nama,tanggal;
        public CardView proses,antar,terima,container;
        public PemesananViewHolder(@NonNull View itemView) {
            super(itemView);
            idTrans = itemView.findViewById(R.id.txtIdTransaksi);
            username = itemView.findViewById(R.id.txtUsername);
            nama = itemView.findViewById(R.id.txt_nama);
            tanggal = itemView.findViewById(R.id.txt_tanggal);
            proses = itemView.findViewById(R.id.btn_proses);
            antar = itemView.findViewById(R.id.btn_antar);
            terima = itemView.findViewById(R.id.btn_terima);
            container = itemView.findViewById(R.id.containerPemesanan);

            itemView.findViewById(R.id.containerPemesanan).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String idtran = idTrans.getText().toString();
                    String userPbl = username.getText().toString();
                    Intent intent = new Intent(context, DetailPemesananActivity.class);
                    intent.putExtra("ID",idtran);
                    intent.putExtra("USERNAME",userPbl);
                    context.startActivity(intent);
                }
            });

            itemView.findViewById(R.id.btn_proses).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String kodetran = idTrans.getText().toString();
                    int nilaiProses = 1;
                    Toast.makeText(context, "Status diUpdate menjadi Proses !", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(context, HomeActivity.class);
                    intent1.putExtra("KODETR",kodetran);
                    intent1.putExtra("STATUSPROSES",nilaiProses);
                    DataPemesananActivity.dba.ubahProsesStatus(kodetran,nilaiProses);
                    context.startActivity(intent1);


                }
            });

            itemView.findViewById(R.id.btn_antar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String kodetranAntar = idTrans.getText().toString();
                    int nilaiAntar = 2;
                    Toast.makeText(context, "Status diUpdate menjadi DiAntar !", Toast.LENGTH_SHORT).show();

                    Intent intent2 = new Intent(context, HomeActivity.class);
                    intent2.putExtra("KODETR",kodetranAntar);
                    intent2.putExtra("STATUSANTAR",nilaiAntar);
                    DataPemesananActivity.dba.ubahAntarStatus(kodetranAntar,nilaiAntar);
                    context.startActivity(intent2);
                }

            });

            itemView.findViewById(R.id.btn_terima).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String idTran = idTrans.getText().toString();
                    int nilaiTerima = 3;

                    Intent intent3 = new Intent(context,HomeActivity.class);
                    intent3.putExtra("KODETR",idTran);
                    intent3.putExtra("STATUSTERIMA",nilaiTerima);
                    DataPemesananActivity.dba.ubahTerimaStatus(idTran,nilaiTerima);
                    context.startActivity(intent3);
                    Toast.makeText(context, "Status diUpdate menjadi DiTerima !", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}


