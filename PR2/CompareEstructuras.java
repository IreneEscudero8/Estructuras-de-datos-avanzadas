import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase auxiliar para almacenar los 8 resultados de tiempo para una prueba con un N específico.
 */
class ResultadoPrueba {
    int numHojas;
    double tiempoPesoIter, tiempoPesoRec;
    double tiempoAlturaIter, tiempoAlturaRec;
    double tiempoCallesIter, tiempoCallesRec;
    double tiempoRutaIter, tiempoRutaRec;
}

public class CompareEstructuras {

    // Genera un árbol binario aleatorio con N hojas
    public static NodoArbol generarArbolAleatorio(int N, Random rand) {
        if (N == 1) {
            return new NodoArbol(rand.nextInt(99) + 1); // Hojas con valores del 1 al 99
        }
        int izquierda = rand.nextInt(N - 1) + 1;
        int derecha = N - izquierda;
        NodoArbol izq = generarArbolAleatorio(izquierda, rand);
        NodoArbol der = generarArbolAleatorio(derecha, rand);
        return new NodoArbol(izq, der);
    }

    public static void main(String[] args) {
        int[] tamanosDePrueba = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 255};
        
        Random rand = new Random();
        ProcesadorArbol proc = new ProcesadorArbol();
        
        // Lista para almacenar los objetos con los resultados de cada prueba
        List<ResultadoPrueba> listaDeResultados = new ArrayList<>();

        // 1. FASE DE EJECUCIÓN Y RECOLECCIÓN DE DATOS
        for (int n : tamanosDePrueba) {
            NodoArbol raiz = generarArbolAleatorio(n, rand);
            ResultadoPrueba resActual = new ResultadoPrueba();
            resActual.numHojas = n;
            
            long start, end;

            // Medición de Peso
            start = System.nanoTime(); proc.pesoTotalIterativo(raiz); end = System.nanoTime();
            resActual.tiempoPesoIter = (end - start) / 1e6;
            
            start = System.nanoTime(); proc.pesoTotalRecursivo(raiz); end = System.nanoTime();
            resActual.tiempoPesoRec = (end - start) / 1e6;

            // Medición de Altura
            start = System.nanoTime(); proc.alturaIterativa(raiz); end = System.nanoTime();
            resActual.tiempoAlturaIter = (end - start) / 1e6;
            
            start = System.nanoTime(); proc.alturaArbol(raiz); end = System.nanoTime();
            resActual.tiempoAlturaRec = (end - start) / 1e6;

            // Medición de Calles
            start = System.nanoTime(); proc.callesTotalesIterativo(raiz); end = System.nanoTime();
            resActual.tiempoCallesIter = (end - start) / 1e6;

            start = System.nanoTime(); proc.callesTotalesRecursivo(raiz); end = System.nanoTime();
            resActual.tiempoCallesRec = (end - start) / 1e6;
            
            // Medición de Ruta
            start = System.nanoTime(); proc.obtenerRutaIterativa(raiz); end = System.nanoTime();
            resActual.tiempoRutaIter = (end - start) / 1e6;

            start = System.nanoTime(); proc.obtenerRutaRecursiva(raiz); end = System.nanoTime();
            resActual.tiempoRutaRec = (end - start) / 1e6;
            
            listaDeResultados.add(resActual);
        }

        // 2. FASE DE IMPRESIÓN DETALLADA
        System.out.println("=========================================");
        System.out.println("=== LISTA FINAL DE TIEMPOS DE EJECUCIÓN ===");
        System.out.println("=========================================");
        
        for (ResultadoPrueba res : listaDeResultados) {
            System.out.println("--- Resultados para N = " + res.numHojas + " Hojas ---");
            System.out.printf("Peso:   Iterativo = %.4f ms | Recursivo = %.4f ms\n", res.tiempoPesoIter, res.tiempoPesoRec);
            System.out.printf("Altura: Iterativo = %.4f ms | Recursivo = %.4f ms\n", res.tiempoAlturaIter, res.tiempoAlturaRec);
            System.out.printf("Calles: Iterativo = %.4f ms | Recursivo = %.4f ms\n", res.tiempoCallesIter, res.tiempoCallesRec);
            System.out.printf("Ruta:   Iterativo = %.4f ms | Recursivo = %.4f ms\n", res.tiempoRutaIter, res.tiempoRutaRec);
            System.out.println(); // Añade un espacio para mayor legibilidad
        }
    }
}
