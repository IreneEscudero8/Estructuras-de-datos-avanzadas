/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Lector del archivo de pacientes:
 *  - Línea 1: n (1 <= n <= 100_000)
 *  - Siguientes n líneas: exactamente 10 enteros separados por espacio o tab, cada uno en [0, 10_000_000]
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeerPacientes {

    public static void main(String[] args) {
        String ruta = (args.length > 0) ? args[0] : "pacientes.txt";
        try {
            List<int[]> pacientes = leerPacientesInt(new File(ruta));
            // Demo: imprime tamaño y el primero
            System.out.println("Total leídos: " + pacientes.size());
            if (!pacientes.isEmpty()) {
                int[] p = pacientes.get(0);
                System.out.print("Primer paciente: ");
                for (int v : p) System.out.print(v + " ");
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + ruta);
        } catch (IllegalArgumentException e) {
            System.err.println("Error de formato: " + e.getMessage());
        }
    }

    /** Lee y devuelve pacientes como arreglos de 10 enteros (cumpliendo el enunciado). */
    public static List<int[]> leerPacientesInt(File archivo) throws FileNotFoundException {
        try (Scanner sc = new Scanner(archivo, StandardCharsets.UTF_8.name())) {
            // --- 1) Leer n ---
            if (!sc.hasNextLine()) {
                throw new IllegalArgumentException("Archivo vacío: falta la línea de n.");
            }
            String lineaN = sc.nextLine().trim();
            if (lineaN.isEmpty()) {
                throw new IllegalArgumentException("La primera línea (n) está vacía.");
            }
            int n;
            try {
                n = Integer.parseInt(lineaN);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("La primera línea no es un entero válido para n: '" + lineaN + "'");
            }
            if (n < 1 || n > 100_000) {
                throw new IllegalArgumentException("n fuera de rango [1, 100000]: " + n);
            }

            List<int[]> lista = new ArrayList<>(n);

            // --- 2) Leer exactamente n líneas de datos ---
            final int MIN = 0, MAX = 10_000_000;
            for (int lineaIdx = 1; lineaIdx <= n; lineaIdx++) {
                if (!sc.hasNextLine()) {
                    throw new IllegalArgumentException("Faltan líneas de pacientes: se esperaban " + n +
                                                       ", pero el archivo terminó en la línea " + (lineaIdx));
                }
                String linea = sc.nextLine().trim();

                // El enunciado no menciona comentarios ni líneas vacías: exigimos exactamente 10 enteros.
                String[] tokens = linea.split("\\s+");  // espacios o tabs
                if (tokens.length != 10) {
                    throw new IllegalArgumentException("Línea " + (lineaIdx + 1) +
                        ": se esperaban 10 enteros, hay " + tokens.length);
                }

                int[] datos = new int[10];
                for (int i = 0; i < 10; i++) {
                    int v;
                    try {
                        v = Integer.parseInt(tokens[i]);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Línea " + (lineaIdx + 1) + ": token '" +
                                                           tokens[i] + "' no es un entero válido");
                    }
                    if (v < MIN || v > MAX) {
                        throw new IllegalArgumentException("Línea " + (lineaIdx + 1) + ": valor " + v +
                                                           " fuera de rango [" + MIN + "," + MAX + "]");
                    }
                    datos[i] = v;
                }
                lista.add(datos);
            }

            // Si hay líneas extra, el enunciado no las contempla: puedes ignorarlas o lanzar error.
            // Aquí advertimos por stderr si existe contenido adicional.
            if (sc.hasNextLine()) {
                System.err.println("Advertencia: el archivo tiene líneas extra más allá de n. Se ignorarán.");
            }
            return lista;
        }
    }

    /** Overload opcional: si quieres objetos Patient en lugar de int[]. */
    public static List<Patient> leerPacientes(File archivo) throws FileNotFoundException {
        List<int[]> crudos = leerPacientesInt(archivo);
        List<Patient> lista = new ArrayList<>(crudos.size());
        for (int[] a : crudos) lista.add(new Patient(a));
        return lista;
    }
}
