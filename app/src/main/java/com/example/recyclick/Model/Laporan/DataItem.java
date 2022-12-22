package com.example.recyclick.Model.Laporan;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("tr_totalHarga")
	private String trTotalHarga;

	@SerializedName("kodeTr")
	private String kodeTr;

	@SerializedName("tr_username")
	private String trUsername;

	@SerializedName("namaProd")
	private String namaProd;

	@SerializedName("idProd")
	private String idProd;

	@SerializedName("Qty")
	private String qty;

	@SerializedName("tanggal")
	private String tanggal;

	public void setTrTotalHarga(String trTotalHarga){
		this.trTotalHarga = trTotalHarga;
	}

	public String getTrTotalHarga(){
		return trTotalHarga;
	}

	public void setKodeTr(String kodeTr){
		this.kodeTr = kodeTr;
	}

	public String getKodeTr(){
		return kodeTr;
	}

	public void setTrUsername(String trUsername){
		this.trUsername = trUsername;
	}

	public String getTrUsername(){
		return trUsername;
	}

	public void setNamaProd(String namaProd){
		this.namaProd = namaProd;
	}

	public String getNamaProd(){
		return namaProd;
	}

	public void setIdProd(String idProd){
		this.idProd = idProd;
	}

	public String getIdProd(){
		return idProd;
	}

	public void setQty(String qty){
		this.qty = qty;
	}

	public String getQty(){
		return qty;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}
}