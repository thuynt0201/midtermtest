package com.nguyenthuthuy.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public void insertData(String code, String name, String des, Double count ,String scheldule, Double price, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO TOUR VALUES (NULL, ?, ?, ?, ?, ?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, code);
        statement.bindString(2, name);
        statement.bindString(3, des);
        statement.bindDouble(4, count);
        statement.bindString(5, scheldule);
        statement.bindDouble(6, price);
        statement.bindBlob(7, image);
        statement.executeInsert();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
