package com.example.recyclick;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.recyclick.Model.Login.LoginData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SaveDataBarang {
    private static final String LIST_BARANG = "listBarang";

//    public static void writeDataUser(Context context, LoginData loginData){
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(loginData);
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString(LIST_USER, jsonString);
//        editor.apply();
//    }
//
//    public static LoginData readDataPembeli(Context context){
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
//        String jsonString = pref.getString(LIST_USER, "");
//        Gson gson = new Gson();
//        Type type = new TypeToken<LoginData>() {}.getType();
//        LoginData loginData = gson.fromJson(jsonString, type);
//        return loginData;
//    }
}
