import java.io.File;
import java.util.Scanner;

public class TestComparacion {
    public static void main(String[] args) throws Exception {
        ProcesadorArbol procesador = new ProcesadorArbol();
        Scanner sc = new Scanner(new File("arboles.txt"));

        long totalRecPeso = 0, totalItPeso = 0;
        long totalRecAltura = 0, totalItAltura = 0;
        long totalRecCalles = 0, totalItCalles = 0;
        long totalRecRuta = 0, totalItRuta = 0;

        while (sc.hasNextLine()) {
            String linea = sc.nextLine().trim();
            if (linea.isEmpty()) continue;

            NodoArbol arbol = procesador.leerArbol(linea);

            // Peso
            long start = System.nanoTime();
            procesador.pesoTotalRecursivo(arbol);
            long end = System.nanoTime();
            totalRecPeso += (end - start);

            start = System.nanoTime();
            procesador.pesoTotalIterativo(arbol);
            end = System.nanoTime();
            totalItPeso += (end - start);

            // Altura
            start = System.nanoTime();
            procesador.alturaArbol(arbol);
            end = System.nanoTime();
            totalRecAltura += (end - start);

            start = System.nanoTime();
            procesador.alturaIterativa(arbol);
            end = System.nanoTime();
            totalItAltura += (end - start);

            // Calles
            start = System.nanoTime();
            procesador.callesTotalesRecursivo(arbol);
            end = System.nanoTime();
            totalRecCalles += (end - start);

            start = System.nanoTime();
            procesador.callesTotalesIterativo(arbol);
            end = System.nanoTime();
            totalItCalles += (end - start);

            // Ruta
            start = System.nanoTime();
            procesador.obtenerRutaRecursiva(arbol);
            end = System.nanoTime();
            totalRecRuta += (end - start);

            start = System.nanoTime();
            procesador.obtenerRutaIterativa(arbol);
            end = System.nanoTime();
            totalItRuta += (end - start);
        }

        // Resultados globales
        System.out.println("=== RESULTADOS PROMEDIO ===");
        System.out.println("Peso Rec: " + totalRecPeso / 1e6 + " ms, Iter: " + totalItPeso / 1e6 + " ms");
        System.out.println("Altura Rec: " + totalRecAltura / 1e6 + " ms, Iter: " + totalItAltura / 1e6 + " ms");
        System.out.println("Calles Rec: " + totalRecCalles / 1e6 + " ms, Iter: " + totalItCalles / 1e6 + " ms");
        System.out.println("Ruta Rec: " + totalRecRuta / 1e6 + " ms, Iter: " + totalItRuta / 1e6 + " ms");
    }
}
