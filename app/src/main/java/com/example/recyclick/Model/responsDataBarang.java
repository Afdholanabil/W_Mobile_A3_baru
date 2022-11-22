package com.example.recyclick.Model;

import java.util.List;

public class responsDataBarang {
    private int kode;
    private String message;
    private List<ModelDataBarang> data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ModelDataBarang> getData() {
        return data;
    }

    public void setData(List<ModelDataBarang> data) {
        this.data = data;
    }
}
