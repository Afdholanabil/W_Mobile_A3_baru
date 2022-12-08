package com.example.recyclick.Model.Home;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SearchGetInfo{

	@SerializedName("data")
	private List<DataItemSearch> data;

	@SerializedName("kode")
	private String kode;

	@SerializedName("message")
	private String message;

	public void setData(List<DataItemSearch> data){
		this.data = data;
	}

	public List<DataItemSearch> getData(){
		return data;
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