package com.example.recyclick.Model.Pembeli;

import com.google.gson.annotations.SerializedName;

public class EditPembeli {

    @SerializedName("pesan")
    private String pesan;

    @SerializedName("kondisi")
    private boolean kondisi;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public boolean isKondisi() {
        return kondisi;
    }

    public void setKondisi(boolean kondisi) {
        this.kondisi = kondisi;
    }
}
