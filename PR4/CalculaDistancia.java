import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase que calcula las distancias de edición entre dos cadenas utilizando
 * tres enfoques: recursivo, memoización y programación dinámica
 * Lee dos archivos de texto y calcula las distancias entre ellos.
 */

public class CalculaDistancia {

    private final int delta;
    private final int alfa;

    private static long startTime;
    private static final long TIME_LIMIT_NANO = 10_000_000_000L; // 10 segundos

    static class TiempoExcedidoException extends RuntimeException {
        public TiempoExcedidoException(String message) {
            super(message);
        }
    }

    public CalculaDistancia(int costoHueco, int costoSustitucion) {
        this.delta = costoHueco;
        this.alfa = costoSustitucion;
    }

    public int editDistanceRecursivo(String s1, String s2) {
        startTime = System.nanoTime();
        return editDistanceHelper(s1, s2, s1.length(), s2.length());
    }

    private int editDistanceHelper(String s1, String s2, int m, int n) {
        if (System.nanoTime() - startTime > TIME_LIMIT_NANO) {
            throw new TiempoExcedidoException("Tiempo límite excedido");
        }

        if (m == 0) return n * delta;
        if (n == 0) return m * delta;

        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return editDistanceHelper(s1, s2, m - 1, n - 1);
        }

        int insertCost = delta + editDistanceHelper(s1, s2, m, n - 1);
        int deleteCost = delta + editDistanceHelper(s1, s2, m - 1, n);
        int replaceCost = alfa + editDistanceHelper(s1, s2, m - 1, n - 1);

        return Math.min(replaceCost, Math.min(insertCost, deleteCost));
    }
    
    public int editDistanceMemoizado(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] memo = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                memo[i][j] = -1;
            }
        }
        startTime = System.nanoTime();
        return editDistanceMemoHelper(s1, s2, m, n, memo);
    }

    private int editDistanceMemoHelper(String s1, String s2, int m, int n, int[][] memo) {
        if (System.nanoTime() - startTime > TIME_LIMIT_NANO) {
            throw new TiempoExcedidoException("Tiempo límite excedido");
        }

        if (memo[m][n] != -1) {
            return memo[m][n];
        }

        if (m == 0) {
            return memo[m][n] = n * delta;
        }
        if (n == 0) {
            return memo[m][n] = m * delta;
        }

        int result;
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            result = editDistanceMemoHelper(s1, s2, m - 1, n - 1, memo);
        } else {
            int insertCost = delta + editDistanceMemoHelper(s1, s2, m, n - 1, memo);
            int deleteCost = delta + editDistanceMemoHelper(s1, s2, m - 1, n, memo);
            int replaceCost = alfa + editDistanceMemoHelper(s1, s2, m - 1, n - 1, memo);
            result = Math.min(replaceCost, Math.min(insertCost, deleteCost));
        }

        return memo[m][n] = result;
    }

    public int editDistanceDP(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j * delta;
                } else if (j == 0) {
                    dp[i][j] = i * delta;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insertCost = delta + dp[i][j - 1];
                    int deleteCost = delta + dp[i - 1][j];
                    int replaceCost = alfa + dp[i - 1][j - 1];
                    dp[i][j] = Math.min(replaceCost, Math.min(insertCost, deleteCost));
                }
            }
        }
        return dp[m][n];
    }
    public static void main(String[] args) {
        
        // --- Cambiar aquí los nombres de los archivos para la prueba ---
        String archivoA = "texto1.txt";
        String archivoB = "texto2.txt";

        final int costoHueco = 1;
        final int costoSustitucion = 2;

        try {
            String texto1 = new String(Files.readAllBytes(Paths.get(archivoA)), StandardCharsets.UTF_8);
            String texto2 = new String(Files.readAllBytes(Paths.get(archivoB)), StandardCharsets.UTF_8);

            System.out.println("Documento A: " + archivoA + " (" + texto1.length() + " caracteres)");
            System.out.println("Documento B: " + archivoB + " (" + texto2.length() + " caracteres)");
            System.out.println("--------------------------------------------------");

            CalculaDistancia calculador = new CalculaDistancia(costoHueco, costoSustitucion);

            System.out.println("1. Programación Dinámica (Iterativa):");
            long startDP = System.nanoTime();
            int distDP_AB = calculador.editDistanceDP(texto1, texto2);
            long timeDP_AB = (System.nanoTime() - startDP) / 1_000_000;
            System.out.println("   D(A,B): " + distDP_AB + " (Tiempo: " + timeDP_AB + " ms)");

            startDP = System.nanoTime();
            int distDP_BA = calculador.editDistanceDP(texto2, texto1);
            long timeDP_BA = (System.nanoTime() - startDP) / 1_000_000;
            System.out.println("   D(B,A): " + distDP_BA + " (Tiempo: " + timeDP_BA + " ms)\n");

            System.out.println("2. Recursión con Memoización:");
            try {
                long startMemo = System.nanoTime();
                int distMemo_AB = calculador.editDistanceMemoizado(texto1, texto2);
                long timeMemo_AB = (System.nanoTime() - startMemo) / 1_000_000;
                 System.out.println("   D(A,B): " + distMemo_AB + " (Tiempo: " + timeMemo_AB + " ms)");
            } catch (TiempoExcedidoException | StackOverflowError e) {
                System.out.println("   D(A,B): tiempo límite para resolver el problema excedido");
            }
            try {
                 long startMemo = System.nanoTime();
                int distMemo_BA = calculador.editDistanceMemoizado(texto2, texto1);
                long timeMemo_BA = (System.nanoTime() - startMemo) / 1_000_000;
                System.out.println("   D(B,A): " + distMemo_BA + " (Tiempo: " + timeMemo_BA + " ms)\n");
            } catch (TiempoExcedidoException | StackOverflowError e) {
                System.out.println("   D(B,A): tiempo límite para resolver el problema excedido\n");
            }

            System.out.println("3. Recursión Pura:");
            try {
                long startRec = System.nanoTime();
                int distRec_AB = calculador.editDistanceRecursivo(texto1, texto2);
                long timeRec_AB = (System.nanoTime() - startRec) / 1_000_000;
                System.out.println("   D(A,B): " + distRec_AB + " (Tiempo: " + timeRec_AB + " ms)");
            } catch (TiempoExcedidoException | StackOverflowError e) {
                System.out.println("   D(A,B): tiempo límite para resolver el problema excedido");
            }
             try {
                long startRec = System.nanoTime();
                int distRec_BA = calculador.editDistanceRecursivo(texto2, texto1);
                long timeRec_BA = (System.nanoTime() - startRec) / 1_000_000;
                System.out.println("   D(A,B): " + distRec_BA + " (Tiempo: " + timeRec_BA + " ms)");
            } catch (TiempoExcedidoException | StackOverflowError e) {
                System.out.println("   D(B,A): tiempo límite para resolver el problema excedido");
            }

        } catch (IOException e) {
            System.err.println("Error al leer uno de los archivos:  " + e.getMessage());
        }
    }
}