package com.example.recyclick.Model.Laporan;

import com.google.gson.annotations.SerializedName;

public class DataItemLengkap {

	@SerializedName("idprd")
	private String idprd;

	@SerializedName("iduser")
	private String iduser;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("totalharga")
	private String totalharga;

	@SerializedName("idtrans")
	private String idtrans;

	@SerializedName("namabr")
	private String namabr;

	@SerializedName("tanggal")
	private String tanggal;

	public void setIdprd(String idprd){
		this.idprd = idprd;
	}

	public String getIdprd(){
		return idprd;
	}

	public void setIduser(String iduser){
		this.iduser = iduser;
	}

	public String getIduser(){
		return iduser;
	}

	public void setJumlah(String jumlah){
		this.jumlah = jumlah;
	}

	public String getJumlah(){
		return jumlah;
	}

	public void setTotalharga(String totalharga){
		this.totalharga = totalharga;
	}

	public String getTotalharga(){
		return totalharga;
	}

	public void setIdtrans(String idtrans){
		this.idtrans = idtrans;
	}

	public String getIdtrans(){
		return idtrans;
	}

	public void setNamabr(String namabr){
		this.namabr = namabr;
	}

	public String getNamabr(){
		return namabr;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}
}