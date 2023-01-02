package com.example.recyclick.Model.Transaksi;

import com.google.gson.annotations.SerializedName;

public class transaksiData {

    @SerializedName("idTransaksi")
    private String idTransaksi;

    @SerializedName("tanggal_transaksi")
    private String tanggal;

    @SerializedName("total")
    private int total;

    @SerializedName("Username_pembeli")
    private String usernamePembeli;

    @SerializedName("status")
    private int status;

    @SerializedName("id_keranjang")
    private String idKeranjang;

    @SerializedName("id_produk")
    private String idProduk;

    @SerializedName("Qty")
    private int qty;

    @SerializedName("Total_harga")
    private int totalHarga;

    @SerializedName("namaStatus")
    private String namaStatus;

    @SerializedName("pesanPembeli")
    private String pesanPembeli;

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getUsernamePembeli() {
        return usernamePembeli;
    }

    public void setUsernamePembeli(String usernamePembeli) {
        this.usernamePembeli = usernamePembeli;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIdKeranjang() {
        return idKeranjang;
    }

    public void setIdKeranjang(String idKeranjang) {
        this.idKeranjang = idKeranjang;
    }

    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
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

    public String getNamaStatus() {
        return namaStatus;
    }

    public void setNamaStatus(String namaStatus) {
        this.namaStatus = namaStatus;
    }

    public String getPesanPembeli() {
        return pesanPembeli;
    }

    public void setPesanPembeli(String pesanPembeli) {
        this.pesanPembeli = pesanPembeli;
    }
}
