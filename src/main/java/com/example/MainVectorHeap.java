package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainVectorHeap {
    public static void main(String[] args) {
        VectorHeap<Paciente> colaPrioridad = new VectorHeap<>();

        System.out.println("sistema de emergencias hospital");
        System.out.println("vectorHeap propio");

        // Leer pacientes desde el archivo
        System.out.println("cargando pacientes desde 'pacientes.txt'\n");

        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    // Separar por comas: nombre, sintoma, codigo
                    String[] partes = linea.split(",");
                    if (partes.length == 3) {
                        String nombre = partes[0].trim();
                        String sintoma = partes[1].trim();
                        char codigo = partes[2].trim().charAt(0);
                        Paciente p = new Paciente(nombre, sintoma, codigo);
                        colaPrioridad.add(p);
                        System.out.println("  + ingresado: " + p);
                    } else {
                        System.out.println("formato incorrecto: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("no se pudo leer 'pacientes.txt'. " + e.getMessage());
            return;
        }

        System.out.println("\ntotal de pacientes en espera: " + colaPrioridad.size());
        System.out.println("orden de atencion (por prioridad):");

        int turno = 1;
        while (!colaPrioridad.isEmpty()) {
            Paciente atendido = colaPrioridad.remove();
            System.out.println("turno " + turno + ": " + atendido);
            turno++;
        }

        System.out.println("todos los pacientes han sido atendidos");
        
    }
}
