import java.util.Arrays;

public class Metodos {

    public int HamburguesasRec(int m, int n, int t) {
        if (t == 0) return 0;
        int first = (t >= m) ? HamburguesasRec(m, n, t - m) : -1;
        int second = (t >= n) ? HamburguesasRec(m, n, t - n) : -1;
        if (first == -1 && second == -1) return -1;
        return Math.max(first, second) + 1;
    }

    public int solveConMemo(int m, int n, int t) {
        if (t < 0) return -1;
        int[] memo = new int[t + 1];
        Arrays.fill(memo, -2);
        return HamburguesasMemo(m, n, t, memo);
    }

    public int HamburguesasMemo(int m, int n, int t, int[] memo) {
        if (memo[t] != -2) return memo[t];
        if (t == 0) {
            return memo[t] = 0;
        }
        int first = (t >= m) ? HamburguesasMemo(m, n, t - m, memo) : -1;
        int second = (t >= n) ? HamburguesasMemo(m, n, t - n, memo) : -1;
        if (first == -1 && second == -1) {
            return memo[t] = -1;
        } else {
            return memo[t] = Math.max(first, second) + 1;
        }
    }

    public void HamburguesaDin(int m, int n, int t) {
        int[] dp = new int[t + 1];
        int first, second;
        dp[0] = 0;
        for (int i = 1; i <= t; i++) {
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
