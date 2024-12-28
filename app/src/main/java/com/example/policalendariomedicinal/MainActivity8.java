package com.example.policalendariomedicinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity8 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MedicamentoAdapter adapter;
    private List<Medicamento> medicamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);  // Layout para la actividad 8

        // Configuración del RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar DBHelper y cargar medicamentos
        DBHelper dbHelper = new DBHelper(this);
        medicamentos = dbHelper.getAllMedicamentos();

        // Configurar el adaptador con la lista de medicamentos
        adapter = new MedicamentoAdapter(medicamentos);
        recyclerView.setAdapter(adapter);

        // Configurar el CalendarView
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            Toast.makeText(MainActivity8.this, "Fecha seleccionada: " + selectedDate, Toast.LENGTH_SHORT).show();
        });

        // Configurar el botón para regresar
        Button btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent para ir a la actividad 6
                Intent intent = new Intent(MainActivity8.this, MainActivity6.class);
                startActivity(intent);
                finish();  // Para cerrar la actividad actual (MainActivity8) si no deseas que quede en la pila de actividades
            }
        });
    }
}
