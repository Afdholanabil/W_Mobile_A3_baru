package com.example.recyclick.API;


import com.example.recyclick.Model.DataBarang.AddBarang;
import com.example.recyclick.Model.DataBarang.BarangGetInfo;
import com.example.recyclick.Model.DataBarang.BarangInfo;
import com.example.recyclick.Model.DataBarang.BarangLaris;
import com.example.recyclick.Model.DataBarang.BarangLarisInfo;
import com.example.recyclick.Model.DataBarang.DeleteBarang;
import com.example.recyclick.Model.DataBarang.EditBarang;
import com.example.recyclick.Model.DataBarang.Getidproduk;
import com.example.recyclick.Model.DataKaryawan.DeleteKaryawan;
import com.example.recyclick.Model.DataKaryawan.EditKaryawan;
import com.example.recyclick.Model.DataKaryawan.KaryawanGetInfo;
import com.example.recyclick.Model.Home.SearchGetInfo;
import com.example.recyclick.Model.Kategori.KategoriEditInfo;
import com.example.recyclick.Model.Kategori.KategoriInfo;
import com.example.recyclick.Model.Laporan.BarChartGetInfo;
import com.example.recyclick.Model.Laporan.LaporanLengkap;
import com.example.recyclick.Model.Laporan.LaporanUtama;
import com.example.recyclick.Model.Laporan.LineChartGetInfo;
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
    Call<LoginInfo> CreateLoginPost(
            @Field("lgn_user") String username,
            @Field("lgn_pass") String password);

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
            @Part("stok") int stok,
            @Part("harga_produk") int harga,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("prod_idKategori") int kategori,
            @Part("prod_idRating") int rating
    );

    @FormUrlEncoded
    @POST("deleteProdukMobile.php")
    Call<DeleteBarang> postDeleteBarang(
            @Field("id_produk") String idPrd,
        @Field("gambar_produk") String gambarl
                );


    @GET("showKaryawan.php")
    Call<KaryawanGetInfo> getKaryawanData();

    //file php berbeda
    @GET("showKategori.php")
    Call<KategoriInfo> getKategoriData();

    @GET("showKategori.php")
    Call<KategoriEditInfo> getKatEdit();


    @FormUrlEncoded
    @POST("deleteKaryawanMobile.php")
    Call<DeleteKaryawan> postDeleteKaryawan(
            @Field("Username") String userKaryawan,
            @Field("photo_user")String photo
    );


    @Multipart
    @POST("editProfil.php")
    Call<EditProfil> postEditKaryawan(
            @Part MultipartBody.Part image,
            @Part("Username") RequestBody username,
            @Part("password") RequestBody pass,
            @Part("namaLengkap") RequestBody nama,
            @Part("noHp") RequestBody noHp,
            @Part("photo_user") RequestBody gambar,
            @Part("isnull") RequestBody isNull
    );

    @FormUrlEncoded
    @POST("editProfil.php")
    Call<EditProfil> postEditKaryawannoImg(
            @Field("Username") String username,
            @Field("password") String pass,
            @Field("namaLengkap") String nama,
            @Field("noHp") String noHp,
            @Field("photo_user") String gambar,
            @Field("isnull") String isNull
    );

    @GET("showTransaksi.php")
    Call<transaksiInfo> getTransaksiData();



    @GET("showTrStatus.php")
    Call<transaksiInfo> getTransaksiStatusData();

    @FormUrlEncoded
    @POST("showPesanTr.php")
    Call<transaksiInfo> getTransaksiPesan(
            @Field("Username") String usernamePesan
    );

    @FormUrlEncoded
    @POST("showTrDetail2.php")
    Call<transaksiDetailAlamatInfo> postTrDetail2(
            @Field("kodeTransaksi") String kodeTr
    );

    @FormUrlEncoded
    @POST("showAlamatPbl.php")
    Call<PembeliInfo> getPembeliAlamat(
            @Field("Username") String username
    );

    @FormUrlEncoded
    @POST("editStatusPemesanan.php")
    Call<EditPembeli> postEditPembeli(
            @Field("kodeTransaksi") String kodeTransaksi,
            @Field("tr_idStatus") int idStatus
    );

    @GET("showlaporan.php")
    Call<LaporanLengkap> getInfoLaporan();

    @GET("showInfoLaporanMobile.php")
    Call<LaporanUtama> getInfoLaporanUtama();

    @FormUrlEncoded
    @POST("searchMobile.php")
    Call<SearchGetInfo> getInfoSearch(@Field("searched") String searched);

    @FormUrlEncoded
    @POST("searchMobile.php")
    Call<BarangGetInfo> getInfoSrcLive(
            @Field("searched") String key
    );


    @Multipart
    @POST("editProdukMobile.php")
    Call<EditBarang> postEditBarangWithImg(
            @Part MultipartBody.Part image,
            @Part("idproduk") RequestBody id,
            @Part("namaproduk") RequestBody namaproduk,
            @Part("stok") int stok,
            @Part("harga") int harga,
            @Part("gambarproduk") RequestBody gambar,
            @Part("deskripsi") RequestBody desk,
            @Part("kategori") int kgr,
            @Part("rating") int rating,
            @Part("isnull") RequestBody isnull
    );

    @FormUrlEncoded
    @POST("editProdukMobile.php")
    Call<EditBarang> postEditBarangNoImg(
            @Field("idproduk") String idprd,
            @Field("namaproduk") String namaprd,
            @Field("stok") int stok,
            @Field("harga") int harga,
            @Field("gambarproduk") String gambar,
            @Field("deskripsi") String deskripsi,
            @Field("kategori") int kategori,
            @Field("rating") int rating,
            @Field("isnull") String isnull
    );

    @GET("showProdukLaris.php")
    Call<BarangGetInfo> getBarangLaris();

    @FormUrlEncoded
    @POST("searchKry.php")
    Call<KaryawanGetInfo> getSrcLiveKry(
            @Field("searched") String key
    );

    @FormUrlEncoded
    @POST("searchPemesanan.php")
    Call<transaksiInfo> getSrcLivePemesanan(
            @Field("searched") String key
    );

    @FormUrlEncoded
    @POST("ShowChartOnWeekMobile.php")
    Call<LineChartGetInfo> postGetChartData(@Field("monday") String senin);

    @GET("showIdproduk.php")
    Call<Getidproduk> getidprodukincrement();

    @FormUrlEncoded
    @POST("searchLaporan.php")
    Call<LaporanLengkap> getInfoSearchlaporan(@Field("searched") String searched);

    @FormUrlEncoded
    @POST("showProdukKat.php")
    Call<BarangGetInfo> getProdukWithKat(
            @Field("idKategori") String idKat
    );

    @GET("showChartOnMonth.php")
    Call<BarChartGetInfo> getBarChartData();






}
