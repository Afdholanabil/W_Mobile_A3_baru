package com.example.recyclick.Model.Laporan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LineChartGetInfo {

        @SerializedName("data")
        private List<LineChartDataItem> data;

        @SerializedName("kode")
        private String kode;

        @SerializedName("message")
        private String message;

        public void setData(List<LineChartDataItem> data){
            this.data = data;
        }

        public List<LineChartDataItem> getData(){
            return data;
        }

        public void setKode(String kode){
            this.kode = kode;
        }

        public String getKode(){
            return kode;
        }

        public void setMessage(String message){
            this.message = message;
        }

        public String getMessage(){
            return message;
        }
    }

