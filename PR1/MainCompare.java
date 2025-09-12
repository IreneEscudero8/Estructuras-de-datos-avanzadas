/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase principal para leer archivo, medir tiempos y comparar la implementación con arreglo lineal vs tabla de hash 
 * Formato de input esperado (archivo.txt): n pacientes y sus examenes
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainCompare {
    public static void main(String[] args) {
        String filename = "archivo.txt";

        try (Scanner sc = new Scanner(new File(filename))) {
            int n = sc.nextInt(); // número de pacientes
            System.out.println("Leyendo " + n + " pacientes desde " + filename + " ...");

            List arreglo = new List(n);                  // Arreglo lineal
            PatientHashTable tabla = new PatientHashTable(); // HashTable

            long insertArrayTime = 0L;
            long insertHashTime = 0L;

            // Leer y procesar cada paciente 
            for (int i = 0; i < n; i++) {
                int[] exams = new int[10];
                for (int j = 0; j < 10; j++) {
                    // Asumimos que el archivo está bien formado y tiene suficientes enteros
                    exams[j] = sc.nextInt();
                }

                Patient p = new Patient(exams);

                long t0 = System.nanoTime();
                arreglo.addPatient(p);
                long t1 = System.nanoTime();
                insertArrayTime += (t1 - t0);

                t0 = System.nanoTime();
                tabla.addPatient(p);
                t1 = System.nanoTime();
                insertHashTime += (t1 - t0);
            }

            // Medir detección de duplicados en arreglo 
            long detectStartArray = System.nanoTime();
            int repeatedArray = arreglo.repetidos();
            long detectEndArray = System.nanoTime();
            double detectArrayMs = (detectEndArray - detectStartArray) / 1e6;

            // Medir tiempo de detección en hash 
            long detectStartHash = System.nanoTime();
            int repeatedHash = tabla.getIdenticalPatientsCount();
            long detectEndHash = System.nanoTime();
            double detectHashMs = (detectEndHash - detectStartHash) / 1e6;

            System.out.println("\n=== RESULTADOS ===");

            // Inserciones
            System.out.println("Inserción (arreglo): " + (insertArrayTime / 1e6) + " ms");
            System.out.println("Inserción (hash table): " + (insertHashTime / 1e6) + " ms");

            // Detección de duplicados
            System.out.println("Detección duplicados (arreglo): " + detectArrayMs + " ms");
            System.out.println("Detección duplicados (hash table): " + detectHashMs + " ms");


            System.out.println();
            System.out.println("Arreglo lineal - pacientes únicos: " + (n - repeatedArray));
            System.out.println("Arreglo lineal - pacientes idénticos reportados: " + repeatedArray);

            System.out.println("Hash table   - pacientes únicos: " + tabla.getUniquePatientCount());
            System.out.println("Hash table   - pacientes idénticos reportados: " + repeatedHash);

            System.out.println();
            System.out.print("Conclusión: ");
            if (repeatedHash > 0) {
                System.out.println("se encontraron " + repeatedHash + " pacientes idénticos");
            } else {
                System.out.println("no hay dos pacientes con registros idénticos");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + filename);
        } catch (Exception e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
}
