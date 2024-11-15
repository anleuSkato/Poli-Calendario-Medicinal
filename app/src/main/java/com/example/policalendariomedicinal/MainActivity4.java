package com.example.policalendariomedicinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Campos de entrada
        EditText registroField = findViewById(R.id.etRegistroMedicamento);
        EditText dosisField = findViewById(R.id.etDosis);
        EditText horaField = findViewById(R.id.etHora);
        EditText diasField = findViewById(R.id.etDias);

        // Botón para guardar
        Button guardar = findViewById(R.id.btnGuardar);
        guardar.setOnClickListener(view -> {
            String registro = registroField.getText().toString();
            String dosisStr = dosisField.getText().toString();
            String horaStr = horaField.getText().toString();
            String diasStr = diasField.getText().toString();

            // Validaciones
            if (TextUtils.isEmpty(registro)) {
                Toast.makeText(MainActivity4.this, "El registro no puede estar vacío", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(dosisStr)) {
                Toast.makeText(MainActivity4.this, "La dosis no puede estar vacía", Toast.LENGTH_SHORT).show();
                return;
            }
            int dosis = Integer.parseInt(dosisStr); // Convertir a entero después de la validación

            if (TextUtils.isEmpty(horaStr)) {
                Toast.makeText(MainActivity4.this, "La hora no puede estar vacía", Toast.LENGTH_SHORT).show();
                return;
            }
            int hora = Integer.parseInt(horaStr); // Convertir a entero después de la validación

            if (TextUtils.isEmpty(diasStr)) {
                Toast.makeText(MainActivity4.this, "La cantidad de días no puede estar vacía", Toast.LENGTH_SHORT).show();
                return;
            }
            int dias = Integer.parseInt(diasStr); // Convertir a entero después de la validación

            // Si todas las validaciones son correctas, iniciar la siguiente actividad
            Intent intent = new Intent(MainActivity4.this, MainActivity6.class);
            startActivity(intent);
        });
    }
}
