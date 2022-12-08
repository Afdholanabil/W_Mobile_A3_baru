package com.example.recyclick.Model.Kategori;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KategoriInfo {
        @SerializedName("pesan")
    private String pesan;

    @SerializedName("kondisi")
    private String kondisi;

    @SerializedName("data")
    private List<KategoriItem> data;

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
