package com.example.recyclick.Model.Kategori;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KategoriEditInfo {
    @SerializedName("data")
    private List<KategoriEditData> data;

    @SerializedName("message")
    private String pesan;

    @SerializedName("kode")
    private String kondisi;


    public List<KategoriEditData> getData() {
        return data;
    }

    public void setData(List<KategoriEditData> data) {
        this.data = data;
    }

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
}
