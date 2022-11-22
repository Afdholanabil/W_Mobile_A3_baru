package com.example.recyclick.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.recyclick.BaseActivity;
import com.example.recyclick.HomeActivity;
import com.example.recyclick.Koneksi.dbHelper;
import com.example.recyclick.Notifikasi.GagalLogin;
import com.example.recyclick.R;
import com.example.recyclick.SplashScreen;
import com.example.recyclick.Util;
import com.example.recyclick.databinding.FragmentLoginBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    dbHelper db;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        db = new dbHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLoginBinding fragmentLoginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
        fragmentLoginBinding.btnMsk.setEnabled(false);

        fragmentLoginBinding.btnMsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String usr = fragmentLoginBinding.inputText.getText().toString();
                    String pass = fragmentLoginBinding.inputPass.getText().toString();
                    if(usr.equals("") || pass.equals("")){
                        Util.pindahFragment(getActivity().getSupportFragmentManager(), new GagalLogin());

                    }else {
                        Boolean checkUserPass =db.checkUsernamePassword(usr,pass);
                        if(checkUserPass == true){

                            Util.pindahFragment(getActivity().getSupportFragmentManager(), new BottomNavFragment());
                        }else {
                            Util.pindahFragment(getActivity().getSupportFragmentManager(), new GagalLogin());
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        TextWatcher loginTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String inputUsername = fragmentLoginBinding.inputText.getText().toString();
                String inputPass = fragmentLoginBinding.inputPass.getText().toString();

                fragmentLoginBinding.btnMsk.setEnabled(!inputUsername.isEmpty() && !inputPass.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        fragmentLoginBinding.inputText.addTextChangedListener(loginTextWatcher);
        fragmentLoginBinding.inputPass.addTextChangedListener(loginTextWatcher);

        fragmentLoginBinding.textRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.pindahFragment(getActivity().getSupportFragmentManager(), new RegisterFragment());
            }
        });
        Util.setCustomColorText(fragmentLoginBinding.textRegist,"Sudah Punya Akun ? ","Dafar","76BA99");
        // Inflate the layout for this fragment
        return fragmentLoginBinding.getRoot();
    }
}