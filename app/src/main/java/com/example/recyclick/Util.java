package com.example.recyclick;

import android.text.Html;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.recyclick.Fragment.LoginFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Util {
    public static void pindahFragment(FragmentManager fragmentManager, Fragment fragmentTujuan){
        fragmentManager.beginTransaction().replace(R.id.id_frame_layout,fragmentTujuan).commit();
    }
    public static void pindahFragmentNav(FragmentManager fragmentManager, Fragment fragmentTujuan){
        fragmentManager.beginTransaction().replace(R.id.fragment_container,fragmentTujuan).commit();
    }
    public static void setCustomColorText(TextView mTextViewTarget , String oldText , String coloredText , String coloredHex){
        mTextViewTarget.setText(Html.fromHtml(oldText + "<font color=\"#" +  coloredHex + "\">" + coloredText));
    }
    public static void NotifLogout(FragmentManager fragmentManagerm, Fragment fragment){
        fragmentManagerm.beginTransaction().replace(R.id.container_logout, fragment).commit();
    }
    public static void gantiFragment(FragmentManager fragmentManager, Fragment fragmentTujuan){
        fragmentManager.beginTransaction().replace(R.id.layoutFragment,fragmentTujuan).commit();
    }


}
