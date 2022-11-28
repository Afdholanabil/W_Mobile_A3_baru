package com.example.recyclick.API;


import com.example.recyclick.Model.DataBarang.AddBarang;
import com.example.recyclick.Model.DataBarang.BarangGetInfo;
import com.example.recyclick.Model.DataBarang.BarangInfo;
import com.example.recyclick.Model.DataBarang.DeleteBarang;
import com.example.recyclick.Model.DataKaryawan.DeleteKaryawan;
import com.example.recyclick.Model.DataKaryawan.KaryawanGetInfo;
import com.example.recyclick.Model.Kategori.KategoriInfo;
import com.example.recyclick.Model.Login.LoginInfo;
import com.example.recyclick.Model.Register.RegisterInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @FormUrlEncoded
    @POST("loginmobile.php")
    Call<LoginInfo> CreateLoginPost(@Field("lgn_user") String username, @Field("lgn_pass") String password);

    @FormUrlEncoded
    @POST("registermobile.php")
    Call<RegisterInfo> CreateRegisterPost(
            @Field("username") String username,
            @Field("password") String pass,
            @Field("nama") String nama,
            @Field("notelepon") String notelepon,
            @Field("kedudukan") int kedudukan
    );

    @GET("showData.php")
    Call<BarangGetInfo> getDataBarang();

    @FormUrlEncoded
    @POST("addProdukMobile.php")
    Call<AddBarang> postDataBarang(
            @Field("idproduk") String idprd,
            @Field("namaproduk") String namaprd,
            @Field("stok") int stok,
            @Field("harga") int harga,
            @Field("gambarproduk") String gambar,
            @Field("deskripsi") String deskripsi,
            @Field("kategori") int kategori,
            @Field("rating") int rating
    );

    @FormUrlEncoded
    @POST("deleteProdukMobile.php")
    Call<DeleteBarang> postDeleteBarang(@Field("idproduk") String idPrd);


    @GET("showKaryawan.php")
    Call<KaryawanGetInfo> getKaryawanData();

    @GET("showKategori.php")
    Call<KategoriInfo> getKategoriData();

    @FormUrlEncoded
    @POST("deleteKaryawanMobile.php")
    Call<DeleteKaryawan> postDeleteKaryawan (@Field("username") String userKaryawan);


}
