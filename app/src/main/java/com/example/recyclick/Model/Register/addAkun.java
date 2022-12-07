package com.example.recyclick.Model.Register;

import com.google.gson.annotations.SerializedName;

public class addAkun {
    @SerializedName("pesan")
    private String pesan;

    @SerializedName("kondisi")
    private boolean kondisi;

    public void setPesan(String pesan){
        this.pesan = pesan;
    }

    public String getPesan(){
        return pesan;
    }

    public void setKondisi(boolean kondisi){
        this.kondisi = kondisi;
    }

    public boolean isKondisi(){
        return kondisi;
    }
}
