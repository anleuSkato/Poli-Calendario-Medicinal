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

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity6 extends AppCompatActivity {

    private CheckBox checkBoxNotificaciones;

    private static final String PREF_NAME = "mis_preferencias";
    private static final String PREF_NOTIFICACIONES_KEY = "notificaciones_activadas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        // Referencias a los campos de entrada
        EditText etMedicamento = findViewById(R.id.etMedicamento);
        EditText etDosis = findViewById(R.id.etDosis);
        EditText etHora = findViewById(R.id.etHora);
        EditText etDias = findViewById(R.id.etDias);

        // Referencia al CheckBox
        checkBoxNotificaciones = findViewById(R.id.checkbox_notifications);

        // Cargar el estado del CheckBox desde SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean notificacionesActivadas = preferences.getBoolean(PREF_NOTIFICACIONES_KEY, false);
        checkBoxNotificaciones.setChecked(notificacionesActivadas);

        // Listener para el CheckBox
        checkBoxNotificaciones.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(PREF_NOTIFICACIONES_KEY, isChecked);
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

            if (TextUtils.isEmpty(medicamento) || TextUtils.isEmpty(dosis) || TextUtils.isEmpty(hora) || TextUtils.isEmpty(dias)) {
                Toast.makeText(MainActivity6.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Usar DBHelper para insertar los datos
            DBHelper dbHelper = new DBHelper(MainActivity6.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", medicamento);
            values.put("dosis", dosis);
            values.put("hora", hora);
            values.put("dias", dias);

            // Insertar el medicamento en la base de datos
            long id = db.insert("medicamentos", null, values);

            if (id != -1) {
                Toast.makeText(MainActivity6.this, "Medicamento guardado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity6.this, "Error al guardar el medicamento", Toast.LENGTH_SHORT).show();
            }

            db.close();

            // Navegar a MainActivity7
            Intent intent = new Intent(MainActivity6.this, MainActivity8.class);
            startActivity(intent);

        });


        Button regresar = findViewById(R.id.btnRegresar);
        regresar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity6.this, MainActivity8.class);
            startActivity(intent);

        });

    }
}