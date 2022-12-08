package com.example.recyclick.Model.Home;

import com.google.gson.annotations.SerializedName;

public class DataItemSearch {

	@SerializedName("nama")
	private String nama;

	@SerializedName("harga")
	private int harga;

	@SerializedName("id")
	private String id;

	@SerializedName("stok")
	private int stok;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setHarga(int harga){
		this.harga = harga;
	}

	public int getHarga(){
		return harga;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setStok(int stok){
		this.stok = stok;
	}

	public int getStok(){
		return stok;
	}
}