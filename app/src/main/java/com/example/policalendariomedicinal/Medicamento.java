package com.example.policalendariomedicinal;

public class Medicamento {
    private String nombre;
    private String dosis;
    private String hora;
    private String dias;

    public Medicamento(String nombre, String dosis, String hora, String dias) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.hora = hora;
        this.dias = dias;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDosis() {
        return dosis;
    }

    public String getHora() {
        return hora;
    }

    public String getDias() {
        return dias;
    }
}

