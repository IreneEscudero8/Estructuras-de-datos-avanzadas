/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase que lee un archivo de texto donde cada línea representa un paciente con 10 resultados de exámenes.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeerPacientes {
    /**
     * Método de prueba: permite ejecutar la lectura del archivo de pacientes.
     * El nombre del archivo se pasa como argumento o se usa "pacientes.txt" por defecto.
     */
    public static void main(String[] args) {
        // Aquí se define el nombre del archivo de entrada:
        String ruta = (args.length > 0) ? args[0] : "pacientes.txt";

        try {
            List<Patient> pacientes = leerPacientes(new File(ruta));
            // Demo: imprimir lo leído
            for (int i = 0; i < pacientes.size(); i++) {
                System.out.println("Paciente " + (i + 1) + ": " + pacientes.get(i));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + ruta);
        }
    }

    /**
     * Lee un archivo donde cada línea representa un paciente con 10 números enteros.
     * @param archivo archivo de texto con los datos
     * @return lista de objetos Patient
     */
    public static List<Patient> leerPacientes(File archivo) throws FileNotFoundException {
        List<Patient> lista = new ArrayList<>();
        try (Scanner sc = new Scanner(archivo, StandardCharsets.UTF_8.name())) {
            sc.useDelimiter("\\R"); // separa por saltos de línea
            int numLinea = 0;
            while (sc.hasNext()) {
                String linea = sc.next();
                numLinea++;
                if (linea == null) continue;
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) continue;
                String[] tokens = linea.split("\\s+");
                if (tokens.length != 10) {
                    System.err.printf("Línea %d: se esperaban 10 números, hay %d. Se omite.%n",
                                      numLinea, tokens.length);
                    continue;
                }
                int[] datos = new int[10];
                boolean ok = true;

                for (int i = 0; i < 10; i++) {
                    try {
                        datos[i] = Integer.parseInt(tokens[i]);
                    } catch (NumberFormatException ex) {
                        System.err.printf("Línea %d: token '%s' no es un número válido. Se omite la línea.%n",
                                          numLinea, tokens[i]);
                        ok = false;
                        break;
                    }
                }

                if (ok) lista.add(new Patient(datos));
            }
        }
        return lista;
    }
}
