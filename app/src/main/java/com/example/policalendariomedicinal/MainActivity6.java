package com.example.policalendariomedicinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity6 extends AppCompatActivity {

    private CheckBox checkBoxNotificaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main6);

        // Configurar sistema de insets para barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencias a los campos de entrada
        EditText etMedicamento = findViewById(R.id.etMedicamento);
        EditText etDosis = findViewById(R.id.etDosis);
        EditText etHora = findViewById(R.id.etHora);
        EditText etDias = findViewById(R.id.etDias);

        // Referencia al CheckBox
        checkBoxNotificaciones = findViewById(R.id.checkbox_notifications);

        // Cargar el estado del CheckBox desde SharedPreferences
        SharedPreferences preferences = getSharedPreferences("mis_preferencias", MODE_PRIVATE);
        boolean notificacionesActivadas = preferences.getBoolean("notificaciones_activadas", false);
        checkBoxNotificaciones.setChecked(notificacionesActivadas);

        // Listener para el CheckBox
        checkBoxNotificaciones.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("notificaciones_activadas", isChecked);
            editor.apply();

            if (isChecked) {
                Toast.makeText(MainActivity6.this, "Notificaciones activadas", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity6.this, "Notificaciones desactivadas", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón para guardar los datos
        Button guardar = findViewById(R.id.btnGuardar);
        guardar.setOnClickListener(view -> {
            String medicamento = etMedicamento.getText().toString();
            String dosis = etDosis.getText().toString();
            String hora = etHora.getText().toString();
            String dias = etDias.getText().toString();

            // Validaciones
            if (TextUtils.isEmpty(medicamento)) {
                Toast.makeText(MainActivity6.this, "El medicamento no puede estar vacío", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(dosis)) {
                Toast.makeText(MainActivity6.this, "La dosis no puede estar vacía", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(hora)) {
                Toast.makeText(MainActivity6.this, "La hora no puede estar vacía", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(dias)) {
                Toast.makeText(MainActivity6.this, "Los días no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Aquí puedes guardar los datos en una base de datos o hacer otra lógica
            Toast.makeText(MainActivity6.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();

            // Navegar a otra actividad
            Intent intent = new Intent(MainActivity6.this, MainActivity7.class);
            startActivity(intent);
        });

        // Botón para regresar
        Button regresar = findViewById(R.id.btnRegresar);
        regresar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity6.this, MainActivity7.class);
            startActivity(intent);
        });
    }
}
