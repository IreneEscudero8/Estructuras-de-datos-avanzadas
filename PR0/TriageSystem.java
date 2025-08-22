/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase clase tipo main que simula llegadas en tiempo real de 1000 pacientes y compara tiempos de ambas estructuras
*/

import java.util.Random;

public class TriageSystem {

    public static void main(String[] args) {
        int N = 1000;
        Random rand = new Random();
        Patient[] pacientes = new Patient[N];

        // Generar pacientes prioridad aleatoria (1–10)
        for (int i = 0; i < N; i++) {
            int prioridad = rand.nextInt(10) + 1; // 1–10
            pacientes[i] = new Patient("Paciente" + (i + 1), prioridad, i + 1);
        }

        // LISTA DOBLEMENTE LIGADA
        DblyLnkSctr lista = new DblyLnkSctr();

        // Insert
        long startInsertLista = System.nanoTime();//contador de tiempo
        for (int i = 0; i < N; i++) {
            lista.addPatient(pacientes[i]);
        }
        long endInsertLista = System.nanoTime();//para el contador

        // Search
        long startSearchLista = System.nanoTime();
        for (int i = 0; i < N; i++) {
            int id = rand.nextInt(N) + 1;
            lista.findById(id);
        }
        long endSearchLista = System.nanoTime();

        // Delete
        long startDeleteLista = System.nanoTime();
        while (!lista.isEmpty()) {
            lista.removeFirst();
        }
        long endDeleteLista = System.nanoTime();

        //ÁRBOL BINARIO DE BÚSQUEDA
        PatientBST bst = new PatientBST();

        // Insert
        long startInsertBST = System.nanoTime();
        for (int i = 0; i < N; i++) {
            bst.insert(pacientes[i]);
        }
        long endInsertBST = System.nanoTime();

        // Search(usamos prioridad aleatoria porque el BST busca por prioridad)
        long startSearchBST = System.nanoTime();
        for (int i = 0; i < N; i++) {
            int pr = rand.nextInt(10) + 1; // prioridades entre 1 y 10
            bst.search(pr);
        }
        long endSearchBST = System.nanoTime();

        // Delete
        long startDeleteBST = System.nanoTime();
        for (int i = 1; i < N; i++) {
            bst.removeFirst(); 
        }
        long endDeleteBST = System.nanoTime();

        // RESULTADOS
        System.out.println("\nRESULTADOS LISTA DOBLEMENTE LIGADA ");
        System.out.println("Insert: " + (endInsertLista - startInsertLista) / 1e6 + " ms");// se divide para que sea mas facil leer ms
        System.out.println("Search: " + (endSearchLista - startSearchLista) / 1e6 + " ms");
        System.out.println("Delete: " + (endDeleteLista - startDeleteLista) / 1e6 + " ms");

        System.out.println("\nRESULTADOS BST ");
        System.out.println("Insert: " + (endInsertBST - startInsertBST) / 1e6 + " ms");
        System.out.println("Search: " + (endSearchBST - startSearchBST) / 1e6 + " ms");
        System.out.println("Delete: " + (endDeleteBST - startDeleteBST) / 1e6 + " ms");
    }
}
