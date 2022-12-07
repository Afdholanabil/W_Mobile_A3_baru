package com.example.recyclick.API;


import com.example.recyclick.Model.DataBarang.AddBarang;
import com.example.recyclick.Model.DataBarang.BarangGetInfo;
import com.example.recyclick.Model.DataBarang.BarangInfo;
import com.example.recyclick.Model.DataBarang.DeleteBarang;
import com.example.recyclick.Model.DataBarang.EditBarang;
import com.example.recyclick.Model.DataKaryawan.DeleteKaryawan;
import com.example.recyclick.Model.DataKaryawan.EditKaryawan;
import com.example.recyclick.Model.DataKaryawan.KaryawanGetInfo;
import com.example.recyclick.Model.Kategori.KategoriInfo;
import com.example.recyclick.Model.Login.EditProfil;
import com.example.recyclick.Model.Login.LoginInfo;
import com.example.recyclick.Model.Pembeli.EditPembeli;
import com.example.recyclick.Model.Pembeli.PembeliData;
import com.example.recyclick.Model.Pembeli.PembeliInfo;
import com.example.recyclick.Model.Register.RegisterInfo;
import com.example.recyclick.Model.Transaksi.transaksiDetailAlamatInfo;
import com.example.recyclick.Model.Transaksi.transaksiInfo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIRequestData {
    @FormUrlEncoded
    @POST("loginmobile.php")
    Call<LoginInfo> CreateLoginPost(@Field("lgn_user") String username, @Field("lgn_pass") String password);

    @Multipart
    @POST("registermobile.php")
    Call<RegisterInfo> CreateRegisterPost(
            @Part MultipartBody.Part body,
            @Part("username") RequestBody username,
            @Part("password") RequestBody pass,
            @Part("nama") RequestBody nama,
            @Part("notelepon") RequestBody notelepon,
            @Part("kedudukan") RequestBody kedudukan
    );

    @GET("showData.php")
    Call<BarangGetInfo> getDataBarang();

    @Multipart
    @POST("addProdukMobile.php")
    Call<AddBarang> postDataBarang(
            @Part MultipartBody.Part image,
            @Part("id_produk") RequestBody idprd,
            @Part("nama_produk") RequestBody namaprd,
            @Part("stok") RequestBody stok,
            @Part("harga_produk") RequestBody harga,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("prod_idKategori") RequestBody kategori,
            @Part("prod_idRating") RequestBody rating
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
    Call<DeleteKaryawan> postDeleteKaryawan (@Field("Username") String userKaryawan);

    @FormUrlEncoded
    @POST("editProdukMobile.php")
    Call<EditBarang> postEditBarang (
            @Field("id_produk") String idprd,
            @Field("nama_produk") String namaprd,
            @Field("stok") int stok,
            @Field("harga_produk") int harga,
            @Field("gambar_produk") String gambar,
            @Field("deskripsi") String deskripsi,
            @Field("prod_idKategori") int kategori,
            @Field("prod_idRating") int rating
    );

    @FormUrlEncoded
    @POST("editProfil.php")
    Call<EditProfil> postEditKaryawan(
            @Field("Username") String username,
            @Field("password") String pass,
            @Field("namaLengkap") String nama,
            @Field("noHp") String noHp,
            @Field("User_role") int role
    );

    @GET("showTransaksi.php")
    Call<transaksiInfo> getTransaksiData();

    @GET("showTrStatus.php")
    Call<transaksiInfo> getTransaksiStatusData();

    @GET("showTrDetail.php")
    Call<transaksiDetailAlamatInfo>getTransaksiDetailAlamat();

    @FormUrlEncoded
    @POST("showTrDetail2.php")
    Call<transaksiDetailAlamatInfo>postTrDetail2(
            @Field("kodeTransaksi")String kodeTr
            );

    @FormUrlEncoded
    @POST("showAlamatPbl.php")
    Call<PembeliInfo> getPembeliAlamat(
            @Field("Username") String username
    );

    @FormUrlEncoded
    @POST("editStatusPemesanan.php")
    Call<EditPembeli>postEditPembeli(
            @Field("kodeTransaksi") String kodeTransaksi,
            @Field("tr_idStatus") int idStatus
    );

}
