package com.example.recyclick.Model.Laporan;

import com.google.gson.annotations.SerializedName;

public class BarChartDataItem {
    @SerializedName("nilai")
    private String nilai;

    @SerializedName("bulan")
    private int bulan;

    public void setNilai(String nilai){
        this.nilai = nilai;
    }

    public String getNilai(){
        return nilai;
    }

    public void setBulan(int bulan){
        this.bulan = bulan;
    }

    public int getBulan(){
        return bulan;
    }
}
