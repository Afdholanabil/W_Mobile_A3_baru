package com.example.recyclick.Model.Pembeli;

import com.example.recyclick.Model.DataBarang.DataItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PembeliInfo {

    @SerializedName("data")
    private List<PembeliData> data;

    @SerializedName("kode")
    private String kode;

    @SerializedName("message")
    private String message;

    public List<PembeliData> getData() {
        return data;
    }

    public void setData(List<PembeliData> data) {
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
