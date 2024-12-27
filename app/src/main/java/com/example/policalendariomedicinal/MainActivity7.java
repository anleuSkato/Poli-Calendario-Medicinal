package com.example.policalendariomedicinal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Cargar medicamentos desde la base de datos
        cargarMedicamentos();

        adapter = new MedicamentoAdapter(medicamentos);
        recyclerView.setAdapter(adapter);

        CalendarView calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            Toast.makeText(MainActivity7.this, "Fecha: " + selectedDate, Toast.LENGTH_SHORT).show();
        });
    }

    private void cargarMedicamentos() {
        medicamentos = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("medicamentos", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String nombre = getColumnValue(cursor, "nombre");
                String dosis = getColumnValue(cursor, "dosis");
                String hora = getColumnValue(cursor, "hora");
                String dias = getColumnValue(cursor, "dias");

                if (nombre != null && dosis != null && hora != null && dias != null) {
                    medicamentos.add(new Medicamento(nombre, dosis, hora, dias));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }

    private String getColumnValue(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex != -1) {
            return cursor.getString(columnIndex);
        } else {
            return null; // Manejar el caso de columna no encontrada
        }
    }
}
