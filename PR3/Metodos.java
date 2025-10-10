import java.util.Arrays;

public class Metodos {

    // Método recursivo
    public int[] hamburguesasRec(int m, int n, int t) {
        // Caso base: tiempo exacto
        if (t == 0) return new int[]{0, 0};
        // Caso base: tiempo negativo -> no válido
        if (t < 0) return new int[]{-1, t};

        int[] best = {-1, t}; // [maxHamburguesas, sobrante]

        // Intentar hacer una hamburguesa de tipo m
        if (t >= m) {
            int[] first = hamburguesasRec(m, n, t - m);
            if (first[0] != -1 && first[1] == 0) {
                first[0]++;
                return first; // si ya es exacto, devolver
            } else if (first[0] != -1 && first[1] < best[1]) {
                best = new int[]{first[0] + 1, first[1]};
            }
        }

        // Intentar hacer una hamburguesa de tipo n
        if (t >= n) {
            int[] second = hamburguesasRec(m, n, t - n);
            if (second[0] != -1 && second[1] == 0) {
                second[0]++;
                return second;
            } else if (second[0] != -1 && second[1] < best[1]) {
                best = new int[]{second[0] + 1, second[1]};
            }
        }

        // Si no hay combinación exacta, devolver el mejor intento
        if (best[0] == -1) return new int[]{0, t}; // nada posible
        return best;
    }

    // Método con memoización
    public int[] hamburguesasMemo(int m, int n, int t) {
        int[][] memo = new int[t + 1][2];
        for (int[] arr : memo) Arrays.fill(arr, -2);
        return solveConMemo(m, n, t, memo);
    }

    public int[] solveConMemo(int m, int n, int t, int[][] memo) {
        if (t < 0) return new int[]{-1, t};
        if (t == 0) return new int[]{0, 0};
        if (memo[t][0] != -2) return memo[t];

        int[] best = {-1, t};

        // Opción 1: usar m
        if (t >= m) {
            int[] first = solveConMemo(m, n, t - m, memo);
            if (first[0] != -1 && first[1] == 0) {
                memo[t] = new int[]{first[0] + 1, 0};
                return memo[t];
            } else if (first[0] != -1 && first[1] < best[1]) {
                best = new int[]{first[0] + 1, first[1]};
            }
        }

        // Opción 2: usar n
        if (t >= n) {
            int[] second = solveConMemo(m, n, t - n, memo);
            if (second[0] != -1 && second[1] == 0) {
                memo[t] = new int[]{second[0] + 1, 0};
                return memo[t];
            } else if (second[0] != -1 && second[1] < best[1]) {
                best = new int[]{second[0] + 1, second[1]};
            }
        }

        // Guardar el mejor resultado posible
        if (best[0] == -1) best = new int[]{0, t};
        memo[t] = best;
        return best;
    }

    // Método dinámico (solo ajustado para devolver arreglo)
    public int[] hamburguesaDin(int m, int n, int t) {
        int[] dp = new int[t + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 1; i <= t; i++) {
            int best = -1;
            if (i >= m && dp[i - m] != -1)
                best = Math.max(best, dp[i - m] + 1);
            if (i >= n && dp[i - n] != -1)
                best = Math.max(best, dp[i - n] + 1);
            dp[i] = best;
        }

        if (dp[t] != -1) {
            return new int[]{dp[t], 0};
        } else {
            int i = t - 1;
            while (i >= 0 && dp[i] == -1) i--;
            return new int[]{dp[i], t - i};
        }
    }
}
