package com.example.recyclick.API;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class serverRetrofit {

public static Retrofit retrofit;
    public static final String url ="http://192.168.1.9/W-web/Tugas/";
    public static final String url2 ="http://172.16.109.19:8080/belajarphp/Tugas/";
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
