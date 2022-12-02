package com.example.appsqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class usuario {
    SQLiteDatabase db = null;
    Context contex1 = null;
    String tableName = "usuario";

    public usuario(Context contex1, SQLiteDatabase db){
        this.contex1 = contex1;
        this.db = db;
    }

    public void nuevo(String nombre, String direccion, int telefono){
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("direccion", direccion);
        valores.put("telefono", telefono);
        db.insert(tableName, null, valores);
    }

    public Cursor consulta(){
        Cursor cursor1 = db.query(tableName, null, null, null, null, null,null);
        return cursor1;
    }
}
