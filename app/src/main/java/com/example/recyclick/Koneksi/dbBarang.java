package com.example.recyclick.Koneksi;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbBarang extends SQLiteOpenHelper {
    private static final String DB_NAME = "recyclick2.db";
    private static final int VERSION = 1;

    public dbBarang(Context context){
        super(context,DB_NAME,null,VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE barang (idbarang text primary key, nama text, jenis text, stok int, deskripsi text)");
        db.execSQL("INSERT INTO barang(idbarang, nama, jenis, stok, deskripsi) VALUES('br001', 'udeng', 'adat', '5', 'lorem ipsum')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertDataBarang(String idbarang, String nama, String jenis, int stok, String deskripsi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("idbarang", idbarang);
        cv.put("nama", nama);
        cv.put("jenis", jenis);
        cv.put("stok", stok);
        cv.put("deskripsi", deskripsi);
        long result = db.insert("barang", null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


}
