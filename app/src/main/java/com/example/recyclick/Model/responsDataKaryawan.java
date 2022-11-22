package com.example.recyclick.Model;

import java.util.List;

public class responsDataKaryawan {
    private int kode;
    private String message;
    private List<ModelDataKaryawan> data;

    public int getKode(){
        return kode;
    }
    public void setKode(int kode){
        this.kode = kode;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public List<ModelDataKaryawan> getData(){
        return data;
    }
    public void setData(List<ModelDataKaryawan> data){
        this.data = data;
    }
}
