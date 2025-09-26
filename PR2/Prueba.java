import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) {
        ProcesadorArbol procesador = new ProcesadorArbol();
        File archivo = new File("arboles.txt");

        try (Scanner lectorArchivo = new Scanner(archivo)) {
            while (lectorArchivo.hasNextLine()) {
                String linea = lectorArchivo.nextLine();
                if (linea.trim().isEmpty()) {
                    continue; // Ignorar líneas en blanco
                }
                
                System.out.println("Procesando arbol: " + linea);
                NodoArbol arbol = procesador.leerArbol(linea);

                if (arbol == null) {
                    System.out.println("Error al leer el árbol desde la línea: " + linea);
                    continue;
                }

                //---------------------- Versión Recursiva --------------------------------
                int pesoRecursivo = procesador.pesoTotalRecursivo(arbol);
                int altura = procesador.alturaArbol(arbol);
                int callesRecursivas = procesador.callesTotalesRecursivo(arbol) - altura;
                String rutaOptimaRecursiva = procesador.obtenerRutaRecursiva(arbol);

                System.out.println("Versión Recursiva:");
                System.out.println("Ruta seguida: " + rutaOptimaRecursiva);
                System.out.println("Número de calles visitadas: " + callesRecursivas);
                System.out.println("Peso total: " + pesoRecursivo);

                //--------------------- Versión Iterativa --------------------------------
                int pesoIterativo = procesador.pesoTotalIterativo(arbol);
                int callesTotalesIterativas = procesador.callesTotalesIterativo(arbol);
                int alturaIterativa = procesador.alturaIterativa(arbol);
                int callesOptimasIterativas = callesTotalesIterativas - alturaIterativa;
                String rutaOptimaIterativa = procesador.obtenerRutaIterativa(arbol);

                System.out.println("Versión Iterativa:");
                System.out.println("Ruta seguida: " + rutaOptimaIterativa);
                System.out.println("Número de calles visitadas: " + callesOptimasIterativas);
                System.out.println("Peso total: " + pesoIterativo);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo arboles.txt.");
            e.printStackTrace();
        }
    }
}