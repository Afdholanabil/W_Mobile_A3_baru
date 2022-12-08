package com.example.recyclick.Model.Laporan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaporanUtama{

	@SerializedName("data")
	private List<DataItemUtama> data;

	@SerializedName("kode")
	private String kode;

	@SerializedName("message")
	private String message;

	public List<DataItemUtama> getData() {
		return data;
	}

	public void setData(List<DataItemUtama> data) {
		this.data = data;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}