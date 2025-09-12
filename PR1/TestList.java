/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Random;

public class TestList {
    public static void main(String[] args) {
        int totalPatientes = 15;
        ListStructure lista = new ListStructure(totalPatientes);
        
        // Creamos los 3 grupos de datos de exámenes que sabemos que se repetiran 5, 4 y 3 veces
        int[] GrupoCincoIguales = generaExamen();
        int[] GrupoTresIguales = generaExamen();
        int[] GrupoCuatroIguales = generaExamen();
        
        // Creamos los 3 conjuntos de exámenes que serán únicos para cada paciente.
        int[] examenUnicoUno = generaExamen();
        int[] examenUnicoDos = generaExamen();
        int[] examenUnicoTres = generaExamen();

        // Agregamos los 15 pacientes
        for (int i = 0; i < totalPatientes; i++) {
            int[] examenes;
            if (i < 5) { // Grupo de 5
                examenes = GrupoCincoIguales;
            } else if (i < 8) { // Grupo de 3
                examenes = GrupoTresIguales;
            } else if (i < 12) { // Grupo de 4
                examenes = GrupoCuatroIguales;
            } else if (i == 12) { // Paciente único 1
                examenes = examenUnicoUno;
            } else if (i == 13) { // Paciente único 2
                examenes = examenUnicoDos;
            } else { // Paciente único 3
                examenes = examenUnicoTres;
            }
            lista.addPatient(new Patient(examenes));
        }

        // Función repetidos()
        int total = lista.repetidos();

        if (total == 0) {
            System.out.println("no hay dos pacientes con registros identicos");
        } else {
            System.out.println("se encontraron " + total + " pacientes identicos");
        }
    }
   //funcion que genera examenes al azar
    private static int[] generaExamen() {
        Random random = new Random();
        int[] examenes = new int[10];
        for (int i = 0; i < 10; i++) {
            examenes[i] = random.nextInt(100);
        }
        return examenes;
    }
}
