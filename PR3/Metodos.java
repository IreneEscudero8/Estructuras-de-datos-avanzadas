/**
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase Metodos con los tres enfoques para resolver el problema,
 * ajustada para devolver el desglose completo de la solución.
 */
import java.util.Arrays;

public class Metodos {

    public int[] hamburguesasRec(int m, int n, int t) {
        // Caso base: Se alcanzó el objetivo exactamente.
        if (t == 0) {
            return new int[]{0, 0, 0, 0};
        }
        // Caso base: Se pasó del objetivo, ruta inválida.
        if (t < 0) {
            return new int[]{-1, -1, -1, -1}; // Señal de ruta inválida
        }

        // Se exploran las dos posibilidades: usar 'm' o usar 'n'.
        int[] resM = hamburguesasRec(m, n, t - m);
        int[] resN = hamburguesasRec(m, n, t - n);

        // Si la ruta de 'm' fue válida, se actualiza su conteo.
        if (resM[0] != -1) { // total en índice 0
            resM[0]++; // Aumenta el total de hamburguesas
            resM[1]++; // Aumenta el contador de m
        }

        // Si la ruta de 'n' fue válida, se actualiza su conteo.
        if (resN[0] != -1) { // total en índice 0
            resN[0]++; // Aumenta el total de hamburguesas
            resN[2]++; // Aumenta el contador de n
        }

        // Se elige el mejor resultado entre las dos rutas.
        return elegirMejor(resM, resN, t);
    }

    /**
     * Resuelve el problema usando recursión con memoización.
     * @return int[] {total_hamburguesas, m_usadas, n_usadas, sobrante}
     */
    public int[] hamburguesasMemo(int m, int n, int t) {
        // El arreglo de memoización ahora guarda 4 valores por cada 't'.
        int[][] memo = new int[t + 1][4];
        for (int[] fila : memo) {
            Arrays.fill(fila, -2); // -2 indica "no calculado"
        }
        return solveConMemo(m, n, t, memo);
    }

    private int[] solveConMemo(int m, int n, int t, int[][] memo) {
        if (t == 0) return new int[]{0, 0, 0, 0};
        if (t < 0) return new int[]{-1, -1, -1, -1};
        if (memo[t][0] != -2) return memo[t]; // Si ya está calculado, se retorna.

        int[] resM = solveConMemo(m, n, t - m, memo);
        int[] resN = solveConMemo(m, n, t - n, memo);

        if (resM[0] != -1) {
            // [total+1, m+1, n, sobrante]
            resM = new int[]{resM[0] + 1, resM[1] + 1, resM[2], resM[3]};
        }
        if (resN[0] != -1) {
            // [total+1, m, n+1, sobrante]
            resN = new int[]{resN[0] + 1, resN[1], resN[2] + 1, resN[3]};
        }

        // Se guarda el mejor resultado en la tabla y se retorna.
        memo[t] = elegirMejor(resM, resN, t);
        return memo[t];
    }

    /**
     * Resuelve el problema usando programación dinámica.
     * @return int[] {total_hamburguesas, m_usadas, n_usadas, sobrante}
     */
    public int[] hamburguesaDin(int m, int n, int t) {
        // dp[i] almacenará la mejor solución para 'i': [total, m_usadas, n_usadas]
        int[][] dp = new int[t + 1][3];
        for (int[] fila : dp) {
            Arrays.fill(fila, -1); // -1 indica "no alcanzable"
        }
        dp[0] = new int[]{0, 0, 0}; // Caso base

        for (int i = 1; i <= t; i++) {
            int[] opcionM = new int[]{-1, -1, -1};
            int[] opcionN = new int[]{-1, -1, -1};

            // Evaluar la opción de agregar una hamburguesa 'm'
            if (i >= m && dp[i - m][0] != -1) { // total en índice 0
                opcionM[0] = dp[i - m][0] + 1; // total
                opcionM[1] = dp[i - m][1] + 1; // m_usadas
                opcionM[2] = dp[i - m][2];     // n_usadas
            }

            // Evaluar la opción de agregar una hamburguesa 'n'
            if (i >= n && dp[i - n][0] != -1) { // total en índice 0
                opcionN[0] = dp[i - n][0] + 1; // total
                opcionN[1] = dp[i - n][1];     // m_usadas
                opcionN[2] = dp[i - n][2] + 1; // n_usadas
            }

            // Elegir la mejor opción (la que da más hamburguesas)
            if (opcionM[0] > opcionN[0]) {
                dp[i] = opcionM;
            } else {
                dp[i] = opcionN;
            }
        }

        // Buscar el resultado final
        if (dp[t][0] != -1) {
            // Se encontró una solución exacta.
            return new int[]{dp[t][0], dp[t][1], dp[t][2], 0};
        } else {
            // No hay solución exacta, buscar hacia atrás.
            int i = t;
            while (i >= 0 && dp[i][0] == -1) {
                i--;
            }
            if (i < 0) return new int[]{0, 0, 0, t};
            return new int[]{dp[i][0], dp[i][1], dp[i][2], t - i};
        }
    }
    
    /**
     * Función auxiliar para comparar dos resultados y elegir el mejor.
     * Criterio: 1. Minimizar sobrante. 2. Maximizar total de hamburguesas.
     */
    private int[] elegirMejor(int[] res1, int[] res2, int tActual) {
        boolean v1 = res1[0] != -1; // total en índice 0
        boolean v2 = res2[0] != -1; // total en índice 0

        if (v1 && v2) { // Ambas rutas son válidas
            // Prioridad: El que tenga menos sobrante (índice 3).
            if (res1[3] < res2[3]) return res1;
            if (res2[3] < res1[3]) return res2;
            // Si el sobrante es el mismo, el que tenga más hamburguesas (índice 0).
            return (res1[0] > res2[0]) ? res1 : res2;
        } else if (v1) { // Solo la ruta 1 es válida
            return res1;
        } else if (v2) { // Solo la ruta 2 es válida
            return res2;
        } else { // Ninguna ruta es válida
            // sobrante es el tiempo actual
            return new int[]{0, 0, 0, tActual};
        }
    }
}