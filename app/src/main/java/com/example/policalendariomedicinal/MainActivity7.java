package com.example.policalendariomedicinal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity7 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MedicamentoAdapter adapter;
    private List<Medicamento> medicamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        // Log para depuración
        Log.d("MainActivity7", "onCreate ejecutado");

        // Configuración del RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar lista de medicamentos
        medicamentos = new ArrayList<>();

        // Cargar medicamentos desde la base de datos
        cargarMedicamentos();

        // Configurar el adaptador
        adapter = new MedicamentoAdapter(medicamentos);
        recyclerView.setAdapter(adapter);

        // Configurar el CalendarView
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            Toast.makeText(MainActivity7.this, "Fecha seleccionada: " + selectedDate, Toast.LENGTH_SHORT).show();
        });
    }

    // Método para cargar los medicamentos desde la base de datos
    private void cargarMedicamentos() {
        // Inicializar DBHelper
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            // Consulta para obtener todos los medicamentos
            cursor = db.query("medicamentos", null, null, null, null, null, null);

            // Verificar si hay medicamentos en el cursor
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    // Obtener los índices de las columnas
                    int indexNombre = cursor.getColumnIndex("nombre");
                    int indexDosis = cursor.getColumnIndex("dosis");
                    int indexHora = cursor.getColumnIndex("hora");
                    int indexDias = cursor.getColumnIndex("dias");

                    // Verificar que los índices sean válidos
                    if (indexNombre >= 0 && indexDosis >= 0 && indexHora >= 0 && indexDias >= 0) {
                        // Leer datos del cursor de manera segura
                        String nombre = cursor.getString(indexNombre);
                        String dosis = cursor.getString(indexDosis);
                        String hora = cursor.getString(indexHora);
                        String dias = cursor.getString(indexDias);

                        // Crear objeto Medicamento y añadirlo a la lista
                        medicamentos.add(new Medicamento(nombre, dosis, hora, dias));
                    } else {
                        Log.e("MainActivity7", "Una o más columnas no se encontraron en la base de datos.");
                    }
                } while (cursor.moveToNext());
            } else {
                Log.d("MainActivity7", "No se encontraron medicamentos en la base de datos.");
            }
        } catch (Exception e) {
            Log.e("MainActivity7", "Error al cargar medicamentos desde la base de datos", e);
        } finally {
            // Cerrar cursor y base de datos
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }
    }
}
