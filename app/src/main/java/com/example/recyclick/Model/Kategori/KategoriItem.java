package com.example.recyclick.Model.Kategori;

import com.google.gson.annotations.SerializedName;

public class KategoriItem {
    @SerializedName("id")
    private String id;

    @SerializedName("nama")
    private String namaKategori;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }
}
