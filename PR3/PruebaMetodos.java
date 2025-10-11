import java.util.Arrays;

public class PruebaMetodos {

    public static void main(String[] args) {
        Metodos solucionador = new Metodos();
        int m = 4;
        int n = 9;

        // --- Bloque 1: Pruebas de Correctitud de Resultados ---
        System.out.println("### PRUEBAS DE CORRECTITUD ###");
        int t = 100;

        System.out.println("\nResolviendo para m=" + m + ", n=" + n + ", t=" + t);
        System.out.println("Formato: [m's, n's, total, sobrante]");
        
        System.out.println("--- 1. Recursivo ---");
        int[] resRec = solucionador.hamburguesasRec(m, n, t);
        System.out.println("Resultado: " + Arrays.toString(resRec));

        System.out.println("\n--- 2. Memoización ---");
        int[] resMemo = solucionador.hamburguesasMemo(m, n, t);
        System.out.println("Resultado: " + Arrays.toString(resMemo));

        System.out.println("\n--- 3. Dinámico ---");
        int[] resDin = solucionador.hamburguesaDin(m, n, t);
        System.out.println("Resultado: " + Arrays.toString(resDin));

        

        // --- Bloque 2: Pruebas de Rendimiento ---
        System.out.println("\n\n### PRUEBAS DE RENDIMIENTO ###");
        // Se establece el valor de 't' para el cual se medirá el tiempo
        int tParaMedir = 41;
        System.out.println("Midiendo tiempos para m=" + m + ", n=" + n + ", t=" + tParaMedir);
        
        // Se crea una copia final de la variable para usarla en la expresión lambda
        final int tFinal = tParaMedir;
        int repeticiones = 5;

        double tiempoRec = medirPromedio(() -> solucionador.hamburguesasRec(m, n, tFinal), repeticiones);
        double tiempoMemo = medirPromedio(() -> solucionador.hamburguesasMemo(m, n, tFinal), repeticiones);
        double tiempoDin = medirPromedio(() -> solucionador.hamburguesaDin(m, n, tFinal), repeticiones);

        System.out.println("\n=== PROMEDIO DE TIEMPOS DE EJECUCIÓN (en segundos) ===");
        System.out.printf("Recursivo:   %.8f s%n", tiempoRec);
        System.out.printf("Memoización: %.8f s%n", tiempoMemo);
        System.out.printf("Dinámico:    %.8f s%n", tiempoDin);
    }

    /**
     * Mide el promedio de tiempo de ejecución de una función.
     */
    private static double medirPromedio(Runnable funcion, int repeticiones) {
        double total = 0;
        for (int i = 0; i < repeticiones; i++) {
            System.gc(); // Sugiere limpiar memoria entre pruebas
            long inicio = System.nanoTime();
            funcion.run();
            long fin = System.nanoTime();
            total += (fin - inicio);
        }
        return (total / repeticiones) / 1_000_000_000.0;
    }
}