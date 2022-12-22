package com.example.recyclick.Model.DataBarang;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BarangLarisInfo {
    @SerializedName("data")
    private List<BarangLaris> data;

    @SerializedName("kode")
    private String kode;

    @SerializedName("message")
    private String message;

    public List<BarangLaris> getData() {
        return data;
    }

    public void setData(List<BarangLaris> data) {
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
