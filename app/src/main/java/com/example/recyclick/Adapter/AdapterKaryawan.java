package com.example.recyclick.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclick.DataBarangActivity;
import com.example.recyclick.DataKaryawanActivity;
import com.example.recyclick.EditKaryawanActivity;
import com.example.recyclick.EditProfileActivity;
import com.example.recyclick.Model.DataKaryawan.KaryawanItem;
import com.example.recyclick.R;
import com.example.recyclick.editBarangActivity;

import java.util.List;

public class AdapterKaryawan extends RecyclerView.Adapter<AdapterKaryawan.KaryawanViewHolder> {
    Context context;
    List<KaryawanItem> data;

    public AdapterKaryawan(Context context, List<KaryawanItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public KaryawanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflat = LayoutInflater.from(context);
        View daftarview = inflat.inflate(R.layout.recycleview_karyawan, parent, false);
        AdapterKaryawan.KaryawanViewHolder viewHolder = new AdapterKaryawan.KaryawanViewHolder(daftarview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KaryawanViewHolder holder, int position) {
        KaryawanItem karyawanItem = data.get(position);
        holder.username.setText(karyawanItem.getUsername());
        holder.nama.setText(karyawanItem.getNamaLengkap());
        holder.noTelp.setText(karyawanItem.getNoHp());
        holder.pass.setText(karyawanItem.getPassword());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class KaryawanViewHolder extends RecyclerView.ViewHolder {
        public TextView username,nama,noTelp,pass;
        public KaryawanViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.txt_username);
            nama = itemView.findViewById(R.id.txt_nama);
            noTelp= itemView.findViewById(R.id.txt_telp);
            pass = itemView.findViewById(R.id.txt_pass);
            itemView.findViewById(R.id.br_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext(), R.style.AlertDialog);
                    View view2 = LayoutInflater.from(view.getRootView().getContext()).inflate(
                            R.layout.layout_hapus_data, null);
                    builder.setView(view2);
                    ((TextView) view2.findViewById(R.id.textJudul)).setText("Apakah Anda Yakin Untuk Meng-HAPUS Karyawan ini ?");
                    ((TextView) view2.findViewById(R.id.textMessage)).setText("Klik Hapus untuk Menghapus Data !");
                    ((Button) view2.findViewById(R.id.btnHapus)).setText("Hapus");
                    ((Button) view2.findViewById(R.id.btnKembali)).setText("Kembali");
                    ((ImageView) view2.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_help_24);

                    AlertDialog alertDialog = builder.create();

                    view2.findViewById(R.id.btnHapus).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String idItem = username.getText().toString();
                            DataKaryawanActivity.dba.hapusDataKaryawan(idItem);
                            alertDialog.dismiss();

                        }
                    });
                    view2.findViewById(R.id.btnKembali).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                    if (alertDialog.getWindow() != null) {
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }
                    alertDialog.show();
                }
            });
            itemView.findViewById(R.id.br_edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String idKr = username.getText().toString();
                    String namaKr = nama.getText().toString();
                    String telp = noTelp.getText().toString();
                    String passKr = pass.getText().toString();
                    Intent intent = new Intent(context, new EditKaryawanActivity().getClass());
                    intent.putExtra("USER",idKr);
                    intent.putExtra("NAMA",namaKr);
                    intent.putExtra("TELP", telp);
                    intent.putExtra("PASS",passKr);
                    context.startActivity(intent);
                }
            });
        }
    }
}
