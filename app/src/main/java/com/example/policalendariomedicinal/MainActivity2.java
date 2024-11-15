package com.example.policalendariomedicinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false; // Para controlar la visibilidad del segundo campo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Campos de entrada
        EditText emailField = findViewById(R.id.etcorreo);
        EditText passwordField = findViewById(R.id.passwordField);
        EditText confipasswordField = findViewById(R.id.confipasswordField);

        // Botón para cambiar de actividad
        Button guardar = findViewById(R.id.btnGuardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                String confirmPassword = confipasswordField.getText().toString();

                // Validar correo
                if (!isValidEmail(email)) {
                    Toast.makeText(MainActivity2.this, "Correo inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar que la contraseña no esté vacía
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity2.this, "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar que las contraseñas coincidan
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(MainActivity2.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Si todo está bien, cambiar de actividad
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        // Lógica para mostrar/ocultar la contraseña principal
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

        // Lógica para mostrar/ocultar la confirmación de contraseña
        ImageButton toggleConfirmPasswordVisibility = findViewById(R.id.toggleConfirmPasswordVisibility);
        toggleConfirmPasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConfirmPasswordVisible) {
                    confipasswordField.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    toggleConfirmPasswordVisibility.setImageResource(R.drawable.ic_visibility_off);
                } else {
                    confipasswordField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    toggleConfirmPasswordVisibility.setImageResource(R.drawable.ic_visibility);
                }
                isConfirmPasswordVisible = !isConfirmPasswordVisible;
                confipasswordField.setSelection(confipasswordField.getText().length());
            }
        });
    }

    // Método para validar el formato del correo
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
