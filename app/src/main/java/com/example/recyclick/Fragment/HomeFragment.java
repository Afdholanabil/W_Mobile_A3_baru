package com.example.recyclick.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclick.Koneksi.dbHelper;
import com.example.recyclick.R;
import com.example.recyclick.databinding.FragmentHomeBinding;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String waktu;
    Cursor cursor;
    private TextView greeting;
    public dbHelper dbhelp;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        FragmentHomeBinding fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

//        fragmentHomeBinding.textHalo
//        greeting = (TextView) getView().findViewById(R.id.greeting);
//        dbHelper = new Database_Tb_user(getActivity());
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        cursor = db.rawQuery("SELECT nama FROM user WHERE email = '"+getActivity().getIntent().getStringExtra("email")+"'",null);
//        cursor.moveToFirst();
//        if (cursor.getCount()>0){
//            cursor.moveToPosition(0);
//            greeting.setText("Selamat"+waktu+""+cursor.getString(0).toString());
//        }

        // Inflate the layout for this fragment

        return fragmentHomeBinding.getRoot();

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setGreeting();
    }


    public void setGreeting() {
        //set waktu
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);


        if (hour <= 6 || hour <= 11) {
            waktu = " pagi ";
        } else if (hour <= 17) {
            waktu = " Siang  ";
        } else if (hour <= 24) {
            waktu = " Malam ";
        }

        greeting = (TextView) getView().findViewById(R.id.textHalo);
        dbhelp = new dbHelper(getActivity());
        SQLiteDatabase db = dbhelp.getReadableDatabase();
        cursor = db.rawQuery("SELECT name FROM Admin WHERE username = '" + getActivity().getIntent().getStringExtra("username") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(2);
            greeting.setText("Selamat" + waktu + "" + cursor.getString(0).toString());
        }

    }
}