package com.example.recyclick.Model;

public class brData {
    private String id, nama, jenis, deskripsi;

    public brData(String id, String nama, String jenis, String deskripsi, int stok) {
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        this.deskripsi = deskripsi;
        this.stok = stok;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {
        return jenis;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public int getStok() {
        return stok;
    }

    private int stok;
}
