package com.example.recyclick.Model.DataBarang;

import com.google.gson.annotations.SerializedName;

public class Getidproduk {

    @SerializedName("id_tersedia")
    private String idTersedia;

    @SerializedName("kode")
    private String kode;

    public void setIdTersedia(String idTersedia){
        this.idTersedia = idTersedia;
    }

    public String getIdTersedia(){
        return idTersedia;
    }

    public void setKode(String kode){
        this.kode = kode;
    }

    public String getKode(){
        return kode;
    }
}
