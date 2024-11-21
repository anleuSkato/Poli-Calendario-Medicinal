package com.example.policalendariomedicinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "medicamentos.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_MEDICAMENTOS = "CREATE TABLE medicamentos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, dosis TEXT, hora TEXT, dias TEXT)";
        db.execSQL(CREATE_TABLE_MEDICAMENTOS);

        String CREATE_TABLE_HISTORIAL = "CREATE TABLE historial (id INTEGER PRIMARY KEY AUTOINCREMENT, medicamento_id INTEGER, fecha TEXT, hora TEXT, dosis_tomada TEXT,  FOREIGN KEY(medicamento_id) REFERENCES medicamentos(id));";
        db.execSQL(CREATE_TABLE_HISTORIAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS medicamentos");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS historial");
        onCreate(db);
    }
}
