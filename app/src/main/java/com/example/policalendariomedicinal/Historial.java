package com.example.policalendariomedicinal;

public class Historial {
    private String fecha;
    private String hora;
    private String dosisTomada;

    public Historial(String fecha, String hora, String dosisTomada) {
        this.fecha = fecha;
        this.hora = hora;
        this.dosisTomada = dosisTomada;
    }

    // Getters y setters
    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getDosisTomada() {
        return dosisTomada;
    }
}

