package com.example;

//Clase que representa la ficha de un paciente en la sección de Emergencias.
public class Paciente implements Comparable<Paciente> {

//nombre del paciente
    private String nombre;

// síntoma o condición del paciente
    private String sintoma;

// código de emergencia (A-E), donde A es el más grave y E el menos grave
    private char codigoEmergencia;

// Constructor para crear un nuevo paciente con su nombre, síntoma y código de emergencia.
    public Paciente(String nombre, String sintoma, char codigoEmergencia) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.codigoEmergencia = codigoEmergencia;
    }

//retorna el nombre del paciente
    public String getNombre() {
        return nombre;
    }

//reortna el síntoma o condición del paciente
    public String getSintoma() {
        return sintoma;
    }

//retorna el código de emergencia del paciente
    public char getCodigoEmergencia() {
        return codigoEmergencia;
    }

// Método para comparar dos pacientes según su código de emergencia
    @Override
    public int compareTo(Paciente otro) {
        return Character.compare(this.codigoEmergencia, otro.codigoEmergencia);
    }

//representación en texto del paciente
    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + codigoEmergencia;
    }
}
