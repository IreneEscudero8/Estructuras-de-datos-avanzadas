import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * - delta (d) = 1 (Costo de hueco: inserción o eliminación)
 * - alfa (a) = 2 (Costo de sustitución / mismatch)
 */
public class CalculaDistancia {

    // Costos definidos
    private final int delta; 
    private final int alfa;  

    public CalculaDistancia(int costoHueco, int costoSustitucion) {
        this.delta = costoHueco;
        this.alfa = costoSustitucion;
    }

    public int editDistanceRecursivo(String s1, String s2) {
        return editDistanceHelper(s1, s2, s1.length(), s2.length());
    }

    private int editDistanceHelper(String s1, String s2, int m, int n) {
        // Caso base: una cadena está vacía
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

    public int editDistanceDP(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                
                if (i == 0) {
                    dp[i][j] = j * delta; 
                }
                
                else if (j == 0) {
                    dp[i][j] = i * delta; 
                }
                
                else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; 
                }
                
                else {
                    int insertCost = delta + dp[i][j - 1];   
                    int deleteCost = delta + dp[i - 1][j];   
                    int replaceCost = alfa + dp[i - 1][j - 1]; 
                    
                    dp[i][j] = Math.min(replaceCost, Math.min(insertCost, deleteCost));
                }
            }
        }
        
        return dp[m][n];
    }

    
    public static String leer(String rutaArchivo) throws IOException {
        return Files.readString(Paths.get(rutaArchivo), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        String archivo1 = "texto1.txt";
        String archivo2 = "texto2.txt";

        int costoHueco = 1;     // delta
        int costoSustitucion = 2; // alfa

        try {
            
            String texto1 = leer(archivo1);
            String texto2 = leer(archivo2);

            System.out.println("Archivo 1: " + archivo1 + " (" + texto1.length() + " caracteres)");
            System.out.println("Archivo 2: " + archivo2 + " (" + texto2.length() + " caracteres)");
            System.out.println("Costo Hueco (delta): " + costoHueco);
            System.out.println("Costo Sustitución (alfa): " + costoSustitucion);

            CalculaDistancia calculador = new CalculaDistancia(costoHueco, costoSustitucion);
            
            long startTimeDP = System.currentTimeMillis(); 
            int distanciaDP = calculador.editDistanceDP(texto1, texto2);
            long endTimeDP = System.currentTimeMillis();
            long tiempoDP = (endTimeDP - startTimeDP);

            System.out.println("Cálculo DP completado en " + tiempoDP + " ms.");
            System.out.println("La distancia con programacion dinamica es: " + distanciaDP);
            
           
            long startTimeRec = System.currentTimeMillis();
            int distanciaRec = calculador.editDistanceRecursivo(texto1, texto2);
            long endTimeRec = System.currentTimeMillis();
            long tiempoRec = (endTimeRec - startTimeRec);

            System.out.println("Cálculo Recursivo completado en " + tiempoRec + " ms.");
            System.out.println("La distancia recursiva es: " + distanciaRec);
           
        } catch (IOException e) {
            System.err.println("Error al leer uno de los archivos: " + e.getMessage());
            System.err.println("Asegúrate de que los archivos '" + archivo1 + "' y '" + archivo2 + "' existan.");
        }
    }
}