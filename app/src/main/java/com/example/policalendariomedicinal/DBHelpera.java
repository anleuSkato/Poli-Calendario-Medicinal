package com.example.policalendariomedicinal;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;


public class DBHelpera extends SQLiteOpenHelper {

    public DBHelpera(Context context) {
        super(context, "medicamentos.db", null, 1); // Especifica el nombre y la versión de la base de datos
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE medicamentos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "dosis TEXT, " +
                "hora TEXT, " +
                "dias TEXT, " +
                "fecha TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar la tabla si ya existe
        db.execSQL("DROP TABLE IF EXISTS medicamentos");
        onCreate(db);
    }
}
