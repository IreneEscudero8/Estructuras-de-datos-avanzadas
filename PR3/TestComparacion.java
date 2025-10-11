/**
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Main que compara el tiempo de ejecución de las diversas funciones recursivas, con memoización y dinámicas
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class TestComparacion {

    private static final long LIMITE = 3_000_000_000L; // 3 segundos
    private static long startTime;

    public static void main(String[] args) throws FileNotFoundException {
           Scanner sc = new Scanner(new File("archivo.txt"));

        while (sc.hasNextInt()) {
            int m = sc.nextInt(), n = sc.nextInt(), t = sc.nextInt();
            
            System.out.println("------Tiempo total: " + t + ", hamburguesas de " + m + " y " + n +"------");

            // Medición Recursiva
            startTime = System.nanoTime();
            try {
                int[] res=hamburguesasRec(m, n, t);
                long totalRec = System.nanoTime() - startTime;
                System.out.print("Recursiva: " + totalRec / 1e6 + " ms, con t=" + t+ "   Solucion:");
                for (int i = 0; i < res.length; i++) {
                System.out.print(res[i] + " ");
            }
            } catch (TiempoExcedidoException e) {
                System.out.println("Recursiva: tiempo límite para resolver el problema excedido");
            }

            // Medición Memoización
            startTime = System.nanoTime();
            try {
                 hamburguesasMemo(m, n, t);
                long totalMemo = System.nanoTime() - startTime;
                System.out.println("\nMemoización: " + totalMemo / 1e6 + " ms, con t=" + t+ "   Solucion:");
                //for (int i = 0; i < res.length; i++) {
                //System.out.print(res[i] + " ");
           // }
            } catch (TiempoExcedidoException e) {
                System.out.println("Memoización: tiempo límite para resolver el problema excedido");
            }

            // Medición Dinámica
            startTime = System.nanoTime();
            try {
                hamburguesasDin(m, n, t);
                long totalDin = System.nanoTime() - startTime;
                System.out.println("Dinámica: " + totalDin / 1e6 + " ms, con t=" + t+ "   Solucion:");
                //for (int i = 0; i < res.length; i++) {
                //System.out.print(res[i] + " ");
           // }
            } catch (TiempoExcedidoException e) {
                System.out.println("Dinámica: tiempo límite para resolver el problema excedido");
            }
        }

        sc.close();
    }
    
    // Excepción personalizada
    static class TiempoExcedidoException extends RuntimeException {}

    // --- Funciones ---

   public static int[] hamburguesasRec(int m, int n, int t) {
        if (System.nanoTime() - startTime > LIMITE) {
            throw new TiempoExcedidoException();
        }
        if (t == 0) {
            return new int[]{0, 0, 0, 0}; // {total_hamb, hamb_m, hamb_n, tiempo_restante}
        }
        if (t < 0) {
            return new int[]{-1, 0, 0, Math.abs(t)};
        }    
        // Llamadas recursivas
        int[] first = hamburguesasRec(m, n, t - m);
        int[] second = hamburguesasRec(m, n, t - n);

        if (first[0] == -1 && second[0] == -1) {
            return new int[]{0, 0, 0, t};
        } 

        if (first[0] >= second[0]) {
            return new int[]{first[0] + 1, first[1] + 1, first[2], first[3]};
        } else {
            return new int[]{second[0] + 1, second[1], second[2] + 1, second[3]};
        }
    }


    public static int hamburguesasMemo(int m, int n, int t) {
        if (System.nanoTime() - startTime > LIMITE) {
            throw new TiempoExcedidoException();
        }
        if (t < 0) return -1;
        int[] memo = new int[t + 1];
        Arrays.fill(memo, -2);
        return solveMemo(m, n, t, memo);
    }

    public static int solveMemo(int m, int n, int t, int[] memo) {
        if (System.nanoTime() - startTime > LIMITE) {
            throw new TiempoExcedidoException();
        }
        if (memo[t] != -2) return memo[t];
        if (t == 0) {
            return memo[t] = 0;
        }
        int first = (t >= m) ? solveMemo(m, n, t - m, memo) : -1;
        int second = (t >= n) ? solveMemo(m, n, t - n, memo) : -1;
        if (first == -1 && second == -1) {
            return memo[t] = -1;
        } else {
            return memo[t] = Math.max(first, second) + 1;
        }
    }

    public static void hamburguesasDin(int m, int n, int t) {
        if (System.nanoTime() - startTime > LIMITE) {
            throw new TiempoExcedidoException();
        }
        int[] dp = new int[t + 1];
        int first, second;
        dp[0] = 0;
        for (int i = 1; i <= t; i++) {
            if (System.nanoTime() - startTime > LIMITE) {
                throw new TiempoExcedidoException();
            }
            if (i >= m) {
                first = dp[i - m];
            } else {
                first = -1;
            }
            if (i >= n) {
                second = dp[i - n];
            } else {
                second = -1;
            }
            if (first == -1 && second == -1) {
                dp[i] = -1;
            } else {
                dp[i] = Math.max(first, second) + 1;
            }
        }
        int result = dp[t];
        if (result >= 0) {
            System.out.println(result);
        } else {
            int i = t - 1;
            while (dp[i] == -1) {
                i--;
            }
            result = dp[i];
            System.out.println(result + " " + (t - i));
        }
    }
}
