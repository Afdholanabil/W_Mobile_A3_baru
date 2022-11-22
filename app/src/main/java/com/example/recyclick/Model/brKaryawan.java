package com.example.recyclick.Model;

public class brKaryawan {
    private String nama,username,password;
    private int noTelp,kedudukan;

    public brKaryawan(String username, String password,String nama, int noTelp, int kedudukan) {
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.noTelp = noTelp;
        this.kedudukan = kedudukan;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getNoTelp() {
        return noTelp;
    }
    public int getKedudukan(){
        return kedudukan;
    }

}
