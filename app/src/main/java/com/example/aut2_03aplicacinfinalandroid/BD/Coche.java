package com.example.aut2_03aplicacinfinalandroid.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Coche {
    private String modelo;
    private String matricula;
    private long id;
    private static Bd_conexion bd;
    private Context context;

    public Coche (String modelo, String matricula, Context context){
        this.modelo = modelo;
        this.matricula = matricula;
        this.context = context;
        bd = new Bd_conexion(context);
    }

    public Coche (String modelo, String matricula, long id, Context context){
        this.modelo = modelo;
        this.matricula = matricula;
        this.id = id;
        this.context = context;
        bd = new Bd_conexion(context);
    }

    public void guardarCoche(){
        SQLiteDatabase sqlite = bd.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("modelo", this.modelo);
        cv.put("matricula", this.matricula);
        sqlite.insert("coches",null,cv);
    }

    public void eliminarCoche(){
        SQLiteDatabase sqlite = bd.getWritableDatabase();
        String[] arg = {String.valueOf(this.matricula)};
        sqlite.delete("coches","matricula = ?", arg);
    }

    public void actualizarCoche(){
        SQLiteDatabase sqlite = bd.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("modelo", this.modelo);
        cv.put("matricula", this.matricula);
        String[] arg = {String.valueOf(this.matricula)};
        sqlite.update("coches", cv, "matricula = ?", arg);
    }

    public static ArrayList<Coche> obtenerCoche(Context context){
        bd = new Bd_conexion(context);
        ArrayList<Coche> coches = new ArrayList<Coche>();
        SQLiteDatabase sqlite = bd.getWritableDatabase();
        String[] columnas = {"modelo", "matricula", "id"};
        Cursor cursor = sqlite.query("coches", columnas, null, null, null, null, null);
        if(cursor==null){
            return coches;
        }
        if (!cursor.moveToFirst()){
            return coches;
        }

        do {
            String modelo = cursor.getString(0);
            String matricula = cursor.getString(1);
            long id = cursor.getLong(2);
            Coche coche = new Coche(modelo, matricula, id, null);
            coches.add(coche);
        }while (cursor.moveToNext());



        return coches;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "modelo='" + modelo + '\'' +
                ", matricula='" + matricula + '\'' +
                ", id=" + id +
                '}';
    }
}
