package com.example.recyclick.Model.Transaksi;

import com.example.recyclick.Model.DataBarang.DataItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class transaksiInfo {
    @SerializedName("data")
    private List<transaksiData> data;

    @SerializedName("kode")
    private String kode;

    @SerializedName("message")
    private String message;

    public List<transaksiData> getData() {
        return data;
    }

    public void setData(List<transaksiData> data) {
        this.data = data;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKode() {
        return kode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
