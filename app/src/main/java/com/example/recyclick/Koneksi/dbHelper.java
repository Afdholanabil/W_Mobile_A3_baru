package com.example.recyclick.Koneksi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "recyclick.db";
    private static final int DB_VERSION = 1;

    public dbHelper (Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Admin (username text primary key, password text , name text, noTelepon text, kedudukan text)");

        db.execSQL("INSERT INTO Admin(username, password, name, noTelepon, kedudukan) VALUES('Nabil', 'hadir', 'Afdholanabil', '0857', 'programer')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Admin");
        onCreate(db);
    }
    public boolean insertData(String username, String password, String name, String noTelepon, String kedudukan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("password",password);
        cv.put("name",name);
        cv.put("noTelepon",noTelepon);
        cv.put("kedudukan",kedudukan);
        long result = db.insert("Admin",null,cv);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }
    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Admin WHERE username = ?", new String[]{username});
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Admin WHERE username =? and password =?",new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
}
