package com.example.recyclick.Model.Laporan;

import com.google.gson.annotations.SerializedName;

public class LineChartDataItem {

        @SerializedName("nilai")
        private String nilai;

        @SerializedName("tanggal")
        private String tanggal;

        public void setNilai(String nilai){
            this.nilai = nilai;
        }

        public String getNilai(){
            return nilai;
        }

        public void setTanggal(String tanggal){
            this.tanggal = tanggal;
        }

        public String getTanggal(){
            return tanggal;
        }
    }

