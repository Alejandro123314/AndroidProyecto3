package com.example.aut2_03aplicacinfinalandroid.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Bd_conexion extends SQLiteOpenHelper {

    private static String nombreBD = "vehiculos";
    private static String tablaCoches = "coches";
    private static  int version = 1;

    public Bd_conexion(Context context){
        super(context,nombreBD,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, modelo text, matricula text)",tablaCoches));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
