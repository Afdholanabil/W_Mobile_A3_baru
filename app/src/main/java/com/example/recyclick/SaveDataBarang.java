package com.example.recyclick;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.recyclick.Model.Login.LoginData;
import com.example.recyclick.Model.Pembeli.PembeliData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class SaveDataBarang {
//    private static final String LIST_ALAMAT = "listalamat";
//
//    public static void writeDataAlamat(Context context, List<PembeliData> pembeliData){
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(pembeliData);
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString(LIST_ALAMAT, jsonString);
//        editor.apply();
//    }
//
//    public static PembeliData readDataAlamat(Context context){
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
//        String jsonString = pref.getString(LIST_ALAMAT, "");
//        Gson gson = new Gson();
//        Type type = new TypeToken<Collection<PembeliData>>() {}.getType();
//        PembeliData loginData = gson.fromJson(jsonString, type);
//        return loginData;
//    }
}
