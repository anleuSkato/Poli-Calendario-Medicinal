package com.example.policalendariomedicinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "medicamentos.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de medicamentos
        String CREATE_MEDICAMENTOS_TABLE = "CREATE TABLE medicamentos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "dosis TEXT," +
                "hora TEXT," +
                "dias TEXT)";
        db.execSQL(CREATE_MEDICAMENTOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Si la base de datos ya existe, elimínala
        db.execSQL("DROP TABLE IF EXISTS medicamentos");
        onCreate(db);
    }

    // Método para obtener todos los medicamentos
    public List<Medicamento> getAllMedicamentos() {
        List<Medicamento> medicamentos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Consulta para obtener todos los medicamentos
            cursor = db.query("medicamentos", null, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                    String dosis = cursor.getString(cursor.getColumnIndex("dosis"));
                    String hora = cursor.getString(cursor.getColumnIndex("hora"));
                    String dias = cursor.getString(cursor.getColumnIndex("dias"));

                    // Crear objeto Medicamento y agregarlo a la lista
                    medicamentos.add(new Medicamento(nombre, dosis, hora, dias));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return medicamentos;
    }
}
