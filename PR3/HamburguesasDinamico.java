import java.util.Arrays;

/**
 * Solución con Programación Dinámica 
 * Construye la solución desde el tiempo 0 hasta el tiempo t.
 */
public class HamburguesasDinamico {

    public int maxHamburguesasDP(int t, int n, int m) {
        // El array 'arreglo' guardará la solución óptima para cada minuto de 0 a t.
        int[] arreglo = new int[t + 1];
        
        // Llenar el array para marcar las soluciones no encontradas.
        Arrays.fill(arreglo, -1);
        
        // Caso base: En 0 minutos, se comen 0 hamburguesas.
        arreglo[0] = 0;

        // Bucle que construye la solución para cada minuto i, desde 1 hasta t.
        for (int i = 1; i <= t; i++) {
            // Opción 1: Llegar a 'i' desde 'i-n'.
            if (i >= n && arreglo[i - n] != -1) {
                arreglo[i] = Math.max(arreglo[i], arreglo[i - n] + 1);
            }
            // Opción 2: Llegar a 'i' desde 'i-m'.
            if (i >= m && arreglo[i - m] != -1) {
                arreglo[i] = Math.max(arreglo[i], arreglo[i - m] + 1);
            }
        }
        
        // El resultado final es la solución calculada para el tiempo total 't'.
        return arreglo[t];
    }
}