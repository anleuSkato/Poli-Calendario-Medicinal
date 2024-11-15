package com.example.policalendariomedicinal;

import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity5 extends AppCompatActivity {

    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Campos de entrada
        EditText emailField = findViewById(R.id.etcorreo);
        EditText passwordField = findViewById(R.id.passwordField);

        // Botón para enviar los datos
        Button enviar = findViewById(R.id.btnGuardar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                // Validar email
                if (!isValidEmail(email)) {
                    Toast.makeText(MainActivity5.this, "Correo electrónico inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar contraseña no vacía
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity5.this, "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Si todo está bien, iniciar la nueva actividad
                Intent intent = new Intent(MainActivity5.this, MainActivity4.class);
                startActivity(intent);
            }
        });

        // Lógica para mostrar/ocultar la contraseña
        ImageButton togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);
        togglePasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    passwordField.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    togglePasswordVisibility.setImageResource(R.drawable.ic_visibility_off);
                } else {
                    passwordField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    togglePasswordVisibility.setImageResource(R.drawable.ic_visibility);
                }
                isPasswordVisible = !isPasswordVisible;
                passwordField.setSelection(passwordField.getText().length());
            }
        });

    }

    // Método para validar el formato del correo
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
