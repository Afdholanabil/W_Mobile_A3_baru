package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclick.Fragment.DataKaryawanFragment;
import com.example.recyclick.Fragment.LoginFragment;
import com.example.recyclick.Fragment.TambahBarangFragment;
import com.example.recyclick.Fragment.TambahKaryawanFragment;

public class PengaturanActivity extends AppCompatActivity {
//    private static PengaturanActivity singleInstance;
//
//    private boolean isLoggingOut;
//
//    private PengaturanActivity() {
//    }
//
//    public static PengaturanActivity getSingleInstance() {
//        if (singleInstance == null) {
//            singleInstance = new PengaturanActivity();
//        }
//        return singleInstance;
//    }
//
//    public boolean isLoggingOut() {
//        return isLoggingOut;
//    }
//
//    public void setLoggingOut(boolean isLoggingOut) {
//        this.isLoggingOut = isLoggingOut;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        findViewById(R.id.linear8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogOutDialog();
            }
        });
        findViewById(R.id.linear3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PengaturanActivity.this, new ContainerFragment().getClass());
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.linear5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.gantiFragment(getSupportFragmentManager(), new TambahKaryawanFragment());

            }
        });
        findViewById(R.id.linear6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container_logout,new DataKaryawanFragment()).commit();
            }
        });

    }
    private void showLogOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PengaturanActivity.this, R.style.AlertDialog);
        View view = LayoutInflater.from(PengaturanActivity.this).inflate(
                R.layout.layout_logout_dialog,(ConstraintLayout)findViewById(R.id.layoutDialogContainer));
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textJudul)).setText("Apakah Yakin Untuk LogOut ?");
        ((TextView) view.findViewById(R.id.textMessage)).setText("Klik Logout untuk Logout");
        ((Button) view.findViewById(R.id.btnOut)).setText("LogOut");
        ((Button) view.findViewById(R.id.btnKembali)).setText("Kembali");
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_help_24);

        AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btnOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PengaturanActivity.getSingleInstance().setLoggingOut(true);
                Intent intent = new Intent(PengaturanActivity.this, new LoginActivity().getClass());
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();



            }
        });
        view.findViewById(R.id.btnKembali).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}