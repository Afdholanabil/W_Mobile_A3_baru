package com.example.recyclick.Notifikasi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclick.Fragment.LoginFragment;
import com.example.recyclick.Fragment.PengaturanFragment;
import com.example.recyclick.R;
import com.example.recyclick.Util;
import com.example.recyclick.databinding.FragmentNotifLogoutBinding;
import com.example.recyclick.databinding.FragmentNotifLogoutBinding;

/**
 * A simple {@link DialogFragment} subclass.
 * Use the {@link NotifLogOut#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotifLogOut extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotifLogOut() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GagalLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static NotifLogOut newInstance(String param1, String param2) {
        NotifLogOut fragment = new NotifLogOut();
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
//        Util.NotifLogout(getActivity().getSupportFragmentManager(), new PengaturanFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentNotifLogoutBinding fragmentNotifLogoutBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_notif_logout,container,false);
        fragmentNotifLogoutBinding.btnSetuju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.pindahFragment(getActivity().getSupportFragmentManager(), new LoginFragment());
            }
        });

        fragmentNotifLogoutBinding.btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        // Inflate the layout for this fragment
        return fragmentNotifLogoutBinding.getRoot();
    }
//    public void showDialog(){
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
//
//        // set title dialog
//        alertDialogBuilder.setTitle("Keluar dari aplikasi?");
//
//        // set pesan dari dialog
//        alertDialogBuilder
//                .setMessage("Klik Ya untuk keluar!")
//                .setIcon(R.mipmap.ic_launcher)
//                .setCancelable(false)
//                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        // jika tombol diklik, maka akan menutup activity ini
//                        Util.pindahFragment(getActivity().getSupportFragmentManager(), new LoginFragment());
//                    }
//                })
//                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // jika tombol ini diklik, akan menutup dialog
//                        // dan tidak terjadi apa2
//                        dialog.cancel();
//                    }
//                });
//
//        // membuat alert dialog dari builder
//        AlertDialog alertDialog = alertDialogBuilder.create();
//
//        // menampilkan alert dialog
//        alertDialog.show();
//    }
    }
//    @SuppressLint("ResourceType")
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setMessage(R.id.txt_judul)
//                .setPositiveButton(R.id.btn_setuju, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Util.pindahFragment(getActivity().getSupportFragmentManager(), new LoginFragment());
//                    }
//                })
//                .setNegativeButton(R.id.btn_kembali, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dismiss();
//
//                    }
//                });
//        return builder.create();
//
//    }
