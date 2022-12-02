package com.example.appsqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHeper extends SQLiteOpenHelper {
    public SQLiteHeper(Context context1, String nameDB, SQLiteDatabase.CursorFactory factory, int version){
        super(context1, nameDB, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario (idU INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, direccion TEXT, telefono INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
