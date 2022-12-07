package com.example.recyclick.Model.Transaksi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class transaksiDetailAlamatInfo {
    @SerializedName("data")
    private List<transaksiDataDetailAlamat> data;

    @SerializedName("kode")
    private String kode;

    @SerializedName("message")
    private String message;

    public List<transaksiDataDetailAlamat> getData() {
        return data;
    }

    public void setData(List<transaksiDataDetailAlamat> data) {
        this.data = data;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
