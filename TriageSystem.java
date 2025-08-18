/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase clase tipo main que simula llegadas en tiempo real de 1000 pacientes con un tiempo entre arribos de 1 minuto 
*/

import java.util.Random;

public class TriageSystem {

       public static void main(String[] args) {
        DblyLnkSctr lista = new DblyLnkSctr();
        Random rand = new Random();
       
        boolean verificador;
        Patient person;
        LnrDblNd<Patient> nodo ;
        int N = 1000, id;
        Patient[] pacientes = new Patient[N];

        // Generar pacientes prioridad aleatoria (1–10)
        for (int i = 0; i < N; i++) {
            int prioridad = rand.nextInt(10) + 1; // 1–10
            pacientes[i] = new Patient("Paciente" + (i+1), prioridad, i+1);
            System.out.println("CREADO: " + pacientes[i].toString());
        }
        
        //Medir insert
        long startInsert = System.nanoTime();
        for (int i = 0; i < N; i++) {
            verificador=lista.addPatient(pacientes[i]);
            System.out.println("Agregado: " + verificador);
            try {
                Thread.sleep(rand.nextInt(2)); // simular tiempo entre arribos (0-1 ms ~ 1 minuto simulado)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endInsert = System.nanoTime();
        System.out.println("Tiempo total Insert: " + (endInsert - startInsert)/1e6 + " ms");

        //Medir search
        long startSearch = System.nanoTime();
        for (int i = 0; i < N; i++) {
           id=rand.nextInt(N) + 1;
           nodo=lista.findById(id); // buscar id aleatorio
           if (nodo != null) {
           System.out.println("Paciente encontrado: Id= " + id);
           } else {
             System.out.println("Paciente no encontrado");}
        }
        
        long endSearch = System.nanoTime();
        System.out.println("Tiempo total Search: " + (endSearch - startSearch)/1e6 + " ms");

        // Eliminar todos los pacientes según prioridad (removeFirst) 
        long startDelete = System.nanoTime();
        while (!lista.isEmpty()) {
            person = lista.removeFirst();
            System.out.println("Atendido: Id=" + person.getId()); 
        }
        long endDelete = System.nanoTime();
        System.out.println("Tiempo total Delete (por prioridad): " + (endDelete - startDelete)/1e6 + " ms");
    }
}

    
