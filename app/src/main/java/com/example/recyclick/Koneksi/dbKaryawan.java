package com.example.recyclick.Koneksi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbKaryawan extends SQLiteOpenHelper {
    private static final String DB_NAME = "recyclick3.db";
    private static final int VERSION = 1;

    public dbKaryawan(Context context){
        super(context,DB_NAME,null,VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  karyawan (idbarang text primary key, nama text, jenis text, stok int, deskripsi text)");
        db.execSQL("INSERT INTO barang(idbarang, nama, jenis, stok, deskripsi) VALUES('br001', 'udeng', 'adat', '5', 'lorem ipsum')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
