// ...existing code...
package PR4;
// ...existing code...
package PR4;

public class dinamico {

    // Calcula la distancia de edición con costos parametrizados:
    // delta = costo de inserción o borrado
    // alfa  = costo de sustitución cuando los caracteres son diferentes
    public static int distanciaEdicion(String a, String b, int delta, int alfa) {
        if (a == null) a = "";
        if (b == null) b = "";
        if (delta < 0) delta = 0;
        if (alfa < 0) alfa = 0;

        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m + 1][n + 1];

        // casos base: transformar prefijos vacíos
        for (int i = 0; i <= m; i++) dp[i][0] = i * delta;
        for (int j = 0; j <= n; j++) dp[0][j] = j * delta;

        // rellena la tabla considerando los 3 casos: borrado, inserción, sustitución
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int costoSust = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : alfa;
                int borrar = dp[i - 1][j] + delta;       // borrar carácter de a
                int insertar = dp[i][j - 1] + delta;     // insertar carácter en a
                int sustituir = dp[i - 1][j - 1] + costoSust; // sustituir carácter
                dp[i][j] = Math.min(Math.min(borrar, insertar), sustituir);
            }
        }
        return dp[m][n];
    }
    

    // Sobrecarga por compatibilidad: costes por defecto delta=1, alfa=1
    public static int distanciaEdicion(String a, String b) {
        return distanciaEdicion(a, b, 1, 1);
    }
}