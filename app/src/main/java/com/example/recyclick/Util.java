package com.example.recyclick;

import android.content.Context;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.recyclick.Fragment.LoginFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.zip.Inflater;

public class Util {
//    static Context cntx;
//    private static Toast toast = new Toast(context);


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

//    public static void setContext(Context context){
//        cntx = context;
//    }

//    public static void loadingview(){
//
//        LayoutInflater layoutInflater = LayoutInflater.from(cntx.getApplicationContext());
//        View view = layoutInflater.inflate(R.layout.toast_loading, null);
//        view.findViewById(R.id.toast_noConnection);
//        toast.setView(view);
//        toast.setGravity(Gravity.CENTER,0,0);
//        toast.show();
//    }
//
//    public static void loadingCancel(){
//        toast.cancel();
//    }
//

}
