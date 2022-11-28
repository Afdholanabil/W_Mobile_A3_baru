package com.example.recyclick.Model.DataKaryawan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KaryawanGetInfo {

    @SerializedName("data")
    private List<KaryawanItem> data;

    @SerializedName("kode")
    private String kode;

    @SerializedName("message")
    private String message;

    public List<KaryawanItem> getData() {
        return data;
    }

    public void setData(List<KaryawanItem> data) {
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
