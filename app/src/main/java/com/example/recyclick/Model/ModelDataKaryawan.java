package com.example.recyclick.Model;

public class ModelDataKaryawan {
    private String nama,username,password;
    private int noTelp,kedudukan;

    public ModelDataKaryawan() {

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(int noTelp) {
        this.noTelp = noTelp;
    }

    public int getKedudukan() {
        return kedudukan;
    }

    public void setKedudukan(int kedudukan) {
        this.kedudukan = kedudukan;
    }
}
