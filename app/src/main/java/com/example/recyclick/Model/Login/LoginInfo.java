package com.example.recyclick.Model.Login;

import com.google.gson.annotations.SerializedName;

public class LoginInfo{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("kondisi")
	private int kondisi;

	@SerializedName("data")
	private LoginData data;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setKondisi(int kondisi){
		this.kondisi = kondisi;
	}

	public int getKondisi(){
		return kondisi;
	}

	public void setData(LoginData data){
		this.data = data;
	}

	public LoginData getData(){
		return data;
	}
}