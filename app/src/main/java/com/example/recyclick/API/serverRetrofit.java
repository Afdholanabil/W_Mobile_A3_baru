package com.example.recyclick.API;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class serverRetrofit {

public static Retrofit retrofit;
    public static final String url ="http://172.16.110.10/W-web/Tugas/";
    public static final String url2 ="https://workshopjti.com/RecyclickA3/AndroidAPI/";
    public static Retrofit koneksiRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    //lisk url data json
                    .baseUrl(url2)
                    //converter agar data json dapat dikenali di java
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}