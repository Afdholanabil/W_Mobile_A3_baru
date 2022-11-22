package com.example.recyclick.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclick.Notifikasi.GagalLogin;
import com.example.recyclick.Notifikasi.NotifLogOut;
import com.example.recyclick.R;
import com.example.recyclick.Util;
import com.example.recyclick.databinding.FragmentPengaturanBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PengaturanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PengaturanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PengaturanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PengaturanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PengaturanFragment newInstance(String param1, String param2) {
        PengaturanFragment fragment = new PengaturanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPengaturanBinding fragmentPengaturanBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_pengaturan,container,false);
        fragmentPengaturanBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        fragmentPengaturanBinding.btnakunKaryawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.pindahFragment(getActivity().getSupportFragmentManager(), new DataKaryawanFragment());
            }
        });
        fragmentPengaturanBinding.btnTambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.pindahFragment(getActivity().getSupportFragmentManager(), new TambahBarangFragment());
            }
        });
        fragmentPengaturanBinding.btnTambahKaryawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.pindahFragment(getActivity().getSupportFragmentManager(), new TambahKaryawanFragment());
            }
        });
        fragmentPengaturanBinding.btnEditProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // Inflate the layout for this fragment
        return fragmentPengaturanBinding.getRoot();
    }

//    private void showLogOutDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialog);
//        View view = LayoutInflater.from(getActivity()).inflate(
//                R.layout.layout_logout_dialog.(ConstraintLayout),R.id.layoutDialogContainer));
//
//    }

    public void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        // set title dialog
        alertDialogBuilder.setTitle("Keluar dari aplikasi ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik LOGOUT untuk Keluar !")
                .setIcon(R.drawable.logo300x169)
                .setCancelable(false)
                .setPositiveButton("LogOut",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        Util.pindahFragment(getActivity().getSupportFragmentManager(), new LoginFragment());
                    }
                })
                .setNegativeButton("Kembali",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

}