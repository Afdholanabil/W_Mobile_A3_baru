package com.example.recyclick.Model.Transaksi;

import com.google.gson.annotations.SerializedName;

public class transaksiDataDetailAlamat {

    @SerializedName("idDetran")
    private String idDetran;

    @SerializedName("idKeranjang")
    private String idKeranjang;

    @SerializedName("kodeTransaksi")
    private String kodeTransaksi;

    @SerializedName("tr_idStatus")
    private String idStatus;

    @SerializedName("nama_status")
    private String namaStatus;

    @SerializedName("idProduk")
    private String idProduk;

    @SerializedName("namaProduk")
    private String namaProduk;

    @SerializedName("Qty")
    private int qty;

    @SerializedName("totalHarga")
    private int totalHarga;

    @SerializedName("usernamePbl")
    private String usernamePbl;

    @SerializedName("idAlamat")
    private String idAlamat;

    @SerializedName("kabupaten")
    private String kabupaten;

    @SerializedName("kecamatan")
    private String kecamatan;

    @SerializedName("desa")
    private String desa;

    @SerializedName("kodePos")
    private String kodePos;

    public String getIdDetran() {
        return idDetran;
    }

    public void setIdDetran(String idDetran) {
        this.idDetran = idDetran;
    }

    public String getIdKeranjang() {
        return idKeranjang;
    }

    public void setIdKeranjang(String idKeranjang) {
        this.idKeranjang = idKeranjang;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public String getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(String idStatus) {
        this.idStatus = idStatus;
    }

    public String getNamaStatus() {
        return namaStatus;
    }

    public void setNamaStatus(String namaStatus) {
        this.namaStatus = namaStatus;
    }

    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getUsernamePbl() {
        return usernamePbl;
    }

    public void setUsernamePbl(String usernamePbl) {
        this.usernamePbl = usernamePbl;
    }

    public String getIdAlamat() {
        return idAlamat;
    }

    public void setIdAlamat(String idAlamat) {
        this.idAlamat = idAlamat;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }
}
