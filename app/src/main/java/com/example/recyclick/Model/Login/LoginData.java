package com.example.recyclick.Model.Login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("nama")
	private String nama;

	@SerializedName("username")
	private String username;

	@SerializedName("password")
	private String pass;

	@SerializedName("role")
	private int userRole;

	@SerializedName("photo")
	private String photo;

	@SerializedName("noHp")
	private int noHp;

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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getNoHp() {
		return noHp;
	}

	public void setNoHp(int noHp) {
		this.noHp = noHp;
	}
}