package com.example.recyclick.Notifikasi;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.recyclick.R;

public class loadingToast {
    private Context cntx;
    Toast toast = new Toast(cntx);

    public loadingToast(Context cntx){
        this.cntx = cntx;
    }

    public void loadinShow(){
        LayoutInflater inf = LayoutInflater.from(cntx);
        View view = inf.inflate(R.layout.toast_loading, null);
        view.findViewById(R.id.toast_noConnection);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
        toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
    }
    public void loadingCancel(){
        toast.cancel();
    }
}
