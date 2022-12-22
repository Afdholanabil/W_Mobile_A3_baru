package com.example.recyclick.Model.DataBarang;

import com.google.gson.annotations.SerializedName;

public class BarangLaris {

        @SerializedName("nama")
        private String nama;

        @SerializedName("harga")
        private int harga;

        @SerializedName("gambardir")
        private String gambardir;

        @SerializedName("rating")
        private int rating;

        @SerializedName("id")
        private String id;

        @SerializedName("stok")
        private int stok;

        @SerializedName("deskripsi")
        private String deskripsi;

        @SerializedName("kategori")
        private int kategori;

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public int getHarga() {
            return harga;
        }

        public void setHarga(int harga) {
            this.harga = harga;
        }

        public String getGambardir() {
            return gambardir;
        }

        public void setGambardir(String gambardir) {
            this.gambardir = gambardir;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getStok() {
            return stok;
        }

        public void setStok(int stok) {
            this.stok = stok;
        }

        public String getDeskripsi() {
            return deskripsi;
        }

        public void setDeskripsi(String deskripsi) {
            this.deskripsi = deskripsi;
        }

        public int getKategori() {
            return kategori;
        }

        public void setKategori(int kategori) {
            this.kategori = kategori;
        }
    }

