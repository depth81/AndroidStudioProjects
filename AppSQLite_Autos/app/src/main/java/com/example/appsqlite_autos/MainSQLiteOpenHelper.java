package com.example.appsqlite_autos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MainSQLiteOpenHelper extends SQLiteOpenHelper {


    public MainSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tblUsuario(idUsuario text primary key, nombre text, clave text)");
        db.execSQL("CREATE TABLE tblAuto(placa text primary key, marca text, modelo text, valor text, disponible text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE tblUsuario");{
            onCreate(db);
        }
        db.execSQL("DROP TABLE tblAuto");{
            onCreate(db);
        }
    }
}
