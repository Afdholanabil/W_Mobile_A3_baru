package com.example.recyclick.Model.DataKaryawan;

import com.google.gson.annotations.SerializedName;

public class KaryawanItem {

    @SerializedName("krUsername")
        private String Username;

    @SerializedName("krpassword")
        private String password;

    @SerializedName("krnama")
        private String namaLengkap;

    @SerializedName("krnoTelepon")
        private String noHp;
    @SerializedName("krRole")
        private String role;
    @SerializedName("gambarkry")
        private String gambarkry;


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGambarkry() {
        return gambarkry;
    }

    public void setGambarkry(String gambarkry) {
        this.gambarkry = gambarkry;
    }
}
