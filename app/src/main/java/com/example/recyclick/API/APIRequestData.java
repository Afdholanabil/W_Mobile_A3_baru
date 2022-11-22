package com.example.recyclick.API;


import com.example.recyclick.Model.Login.LoginInfo;
import com.example.recyclick.Model.Register.RegisterInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIRequestData {
    @FormUrlEncoded
    @POST("loginmobile.php")
    Call<LoginInfo> CreateLoginPost(@Field("lgn_user") String username, @Field("lgn_pass")String password);

    @FormUrlEncoded
    @POST("registermobile.php")
    Call<RegisterInfo> CreateRegisterPost(
            @Field("username") String username,
            @Field("password") String pass,
            @Field("nama") String nama,
            @Field("notelepon") String notelepon,
            @Field("kedudukan") int kedudukan
    );


}
