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

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(MainActivity7.this, "Fecha: " + selectedDate, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarMedicamentos() {
        medicamentos = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("medicamentos", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String dosis = cursor.getString(cursor.getColumnIndex("dosis"));
                String hora = cursor.getString(cursor.getColumnIndex("hora"));
                String dias = cursor.getString(cursor.getColumnIndex("dias"));
                medicamentos.add(new Medicamento(nombre, dosis, hora, dias));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
}
