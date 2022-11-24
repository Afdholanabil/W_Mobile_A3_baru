package com.example.recyclick.Model.DataBarang;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class BarangGetInfo {

    @SerializedName("data")
    private List<DataItem> data;

    @SerializedName("kode")
    private String kode;

    @SerializedName("message")
    private String message;

    public void setData(List<DataItem> data) {
        this.data = data;
    }

    public List<DataItem> getData() {
        return data;
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