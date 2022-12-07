package com.example.recyclick.Model.Pembeli;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PembeliData {

    @SerializedName("Username")
    private String userPbl;

    @SerializedName("pass")
    private String pass;

    @SerializedName("nama")
    private String nama;

    @SerializedName("Telp")
    private String telp;

    @SerializedName("JenisKelamin")
    private String jenisKelamin;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("idAlamat")
    private String idAlamat;

    @SerializedName("kab")
    private String kab;

    @SerializedName("kec")
    private String kec;

    @SerializedName("desa")
    private String desa;

    @SerializedName("kodePos")
    private int kodePos;

    @SerializedName("deskripsi")
    private String deskripsi;

    public String getUserPbl() {
        return userPbl;
    }

    public void setUserPbl(String userPbl) {
        this.userPbl = userPbl;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getIdAlamat() {
        return idAlamat;
    }

    public void setIdAlamat(String idAlamat) {
        this.idAlamat = idAlamat;
    }

    public String getKab() {
        return kab;
    }

    public void setKab(String kab) {
        this.kab = kab;
    }

    public String getKec() {
        return kec;
    }

    public void setKec(String kec) {
        this.kec = kec;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public int getKodePos() {
        return kodePos;
    }

    public void setKodePos(int kodePos) {
        this.kodePos = kodePos;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
