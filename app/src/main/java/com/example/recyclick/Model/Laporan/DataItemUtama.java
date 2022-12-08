package com.example.recyclick.Model.Laporan;

import com.google.gson.annotations.SerializedName;

public class DataItemUtama {

	@SerializedName("barangTerjual")
	private int barangTerjual;

	@SerializedName("pendapatanSebulan")
	private int pendapatanSebulan;

	@SerializedName("pendapatanSetahun")
	private int pendapatanSetahun;

	@SerializedName("barangDiantar")
	private int barangDiantar;

	public int getBarangTerjual() {
		return barangTerjual;
	}

	public void setBarangTerjual(int barangTerjual) {
		this.barangTerjual = barangTerjual;
	}

	public int getPendapatanSebulan() {
		return pendapatanSebulan;
	}

	public void setPendapatanSebulan(int pendapatanSebulan) {
		this.pendapatanSebulan = pendapatanSebulan;
	}

	public int getPendapatanSetahun() {
		return pendapatanSetahun;
	}

	public void setPendapatanSetahun(int pendapatanSetahun) {
		this.pendapatanSetahun = pendapatanSetahun;
	}

	public int getBarangDiantar() {
		return barangDiantar;
	}

	public void setBarangDiantar(int barangDiantar) {
		this.barangDiantar = barangDiantar;
	}
}