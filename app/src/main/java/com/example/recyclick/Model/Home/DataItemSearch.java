package com.example.recyclick.Model.Home;

import com.google.gson.annotations.SerializedName;

import org.intellij.lang.annotations.JdkConstants;

public class DataItemSearch {

	@SerializedName("nama")
	private String nama;

	@SerializedName("harga")
	private int harga;

	@SerializedName("id")
	private String id;

	@SerializedName("stok")
	private int stok;

	@SerializedName("gambardir")
	private String gambar;

	@SerializedName("deskripsi")
	private String deskripsi ;

	@SerializedName("kategori")
	private int kategori;

	@SerializedName("rating")
	private int rating;

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

	public String getGambar() {
		return gambar;
	}

	public void setGambar(String gambar) {
		this.gambar = gambar;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public int getKategori() {
		return kategori;
	}

	public void setKategori(int kategori) {
		this.kategori = kategori;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
