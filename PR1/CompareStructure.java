/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase main que compara la eficiencia de la lista lineal vs tabla hash
 * en la detección de pacientes duplicados.
 */

import java.util.Random;

public class CompareStructure {

    public static void main(String[] args) {
        int N = 10000; // Número de pacientes a generar
        Random rand = new Random();

        // Generar pacientes aleatorios (10 exámenes cada uno, valores 0–1000)
        Patient[] pacientes = new Patient[N];
        for (int i = 0; i < N; i++) {
            int[] exams = new int[10];
            for (int j = 0; j < 10; j++) {
                exams[j] = rand.nextInt(1000);
            }
            pacientes[i] = new Patient(exams);
        }

        // ✅ Forzar al menos un duplicado:
        // copiamos el paciente en la posición 0 al final de la lista
        pacientes[N - 1] = pacientes[0];

        // -------------------------------
        // LISTA LINEAL
        // -------------------------------
        ListStructure lista = new ListStructure(N);
        long start = System.nanoTime();
        for (Patient p : pacientes) {
            lista.addPatient(p);
        }
        int repetidosLista = lista.repetidos();
        long end = System.nanoTime();
        long tiempoLista = end - start;

        // -------------------------------
        // TABLA HASH
        // -------------------------------
        PatientHashTable hashTable = new PatientHashTable();
        start = System.nanoTime();
        for (Patient p : pacientes) {
            hashTable.addPatient(p);
        }
        int repetidosHash = hashTable.getIdenticalPatientsCount();
        end = System.nanoTime();
        long tiempoHash = end - start;

        // -------------------------------
        // RESULTADOS
        // -------------------------------
        System.out.println("=== RESULTADOS ===");
        System.out.println("Pacientes generados: " + N);

        System.out.println("\nLISTA LINEAL");
        System.out.println("Duplicados encontrados: " + repetidosLista);
        System.out.println("Tiempo total: " + tiempoLista / 1e6 + " ms");

        System.out.println("\nTABLA HASH");
        System.out.println("Duplicados encontrados: " + repetidosHash);
        System.out.println("Tiempo total: " + tiempoHash / 1e6 + " ms");
    }
}

