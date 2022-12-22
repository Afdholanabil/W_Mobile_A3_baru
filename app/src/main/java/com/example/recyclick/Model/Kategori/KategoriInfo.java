package com.example.recyclick.Model.Kategori;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KategoriInfo {
    @SerializedName("data")
    private List<KategoriItem> data;

    @SerializedName("message")
    private String pesan;

    @SerializedName("kode")
    private String kondisi;



    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public List<KategoriItem> getData() {
        return data;
    }

    public void setData(List<KategoriItem> data) {
        this.data = data;
    }
}
