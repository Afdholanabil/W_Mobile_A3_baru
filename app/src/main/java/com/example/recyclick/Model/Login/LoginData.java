package com.example.recyclick.Model.Login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("nama")
	private String nama;

	@SerializedName("username")
	private String username;

	@SerializedName("")

	public void setNama(String nama){
		this.nama = nama;
	}

	public LoginData(String nama, String username) {
		this.nama = nama;
		this.username = username;
	}

	public String getNama(){
		return nama;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}