/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase main que simula llegadas en tiempo real de pacientes y compara tiempos de ambas estructuras
*/

import java.util.Random;

public class TriageSystem {

    public static void main(String[] args) throws InterruptedException{
        int N = 1000; // Numero de pacientes a generar
        Random rand = new Random();
        Patient[] pacientes = new Patient[N];

        // Generar pacientes con prioridad aleatoria (1–10)
        for (int i = 0; i < N; i++) {
            int prioridad = rand.nextInt(10) + 1; // 1–10
            pacientes[i] = new Patient("Paciente" + (i + 1), prioridad, i + 1);
        }

        DblyLnkSctr lista = new DblyLnkSctr();
        PatientBST bst = new PatientBST();

        // Tiempo acumulado
        long insertListaTime = 0, searchListaTime = 0, deleteListaTime = 0;
        long insertBSTTime = 0, searchBSTTime = 0, deleteBSTTime = 0;

        int i = 0; // índice

        while (i < N) {
            Thread.sleep(1); // cada iteración = 1 ms
            double prob = rand.nextDouble(); // [0,1)

            // Insert con probabilidad 1/60
            if (prob <= (1.0 / 60.0)) {
                // Lista
                long start = System.nanoTime();
                lista.addPatient(pacientes[i]);
                long end = System.nanoTime();
                insertListaTime += (end - start);

                // BST
                start = System.nanoTime();
                bst.insert(pacientes[i]);
                end = System.nanoTime();
                insertBSTTime += (end - start);

                i++; 
            }

            // Search con probabilidad 1/120
            if (!lista.isEmpty() && rand.nextDouble() < (1.0 / 120.0)) {
                //Buscar paciente con id = i
                long start = System.nanoTime();
                lista.findById(i); 
                long end = System.nanoTime();
                searchListaTime += (end - start);
            }

            if (!bst.isEmpty() && rand.nextDouble() < (1.0 / 120.0)) {
                //Buscar por prioridad (1–10)
                int pr = rand.nextInt(10) + 1;
                long start = System.nanoTime();
                bst.search(pr);
                long end = System.nanoTime();
                searchBSTTime += (end - start);
            }

            // Delete con probabilidad 1/120
            if (!lista.isEmpty() && rand.nextDouble() < (1.0 / 120.0)) {
                long start = System.nanoTime();
                lista.removeFirst();
                long end = System.nanoTime();
                deleteListaTime += (end - start);
            }

            if (!bst.isEmpty() && rand.nextDouble() < (1.0 / 120.0)) {
                long start = System.nanoTime();
                bst.removeFirst();
                long end = System.nanoTime();
                deleteBSTTime += (end - start);
            }
        }

        // Resultados
        System.out.println("\nRESULTADOS LISTA DOBLEMENTE LIGADA ");
        System.out.println("Insert: " + insertListaTime / 1e6 + " ms");
        System.out.println("Search: " + searchListaTime / 1e6 + " ms");
        System.out.println("Delete: " + deleteListaTime / 1e6 + " ms");

        System.out.println("\nRESULTADOS BST ");
        System.out.println("Insert: " + insertBSTTime / 1e6 + " ms");
        System.out.println("Search: " + searchBSTTime / 1e6 + " ms");
        System.out.println("Delete: " + deleteBSTTime / 1e6 + " ms");
    }
}
