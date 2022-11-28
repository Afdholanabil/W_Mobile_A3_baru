package com.example.recyclick.Model.DataBarang;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("nama")
	private String nama;

	@SerializedName("harga")
	private String harga;

	@SerializedName("gambardir")
	private String gambardir;

	@SerializedName("rating")
	private String rating;

	@SerializedName("id")
	private String id;

	@SerializedName("stok")
	private String stok;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("kategori")
	private String kategori;

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
	}

	public void setGambardir(String gambardir){
		this.gambardir = gambardir;
	}

	public String getGambardir(){
		return gambardir;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	public String getRating(){
		return rating;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setStok(String stok){
		this.stok = stok;
	}

	public String getStok(){
		return stok;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}
}