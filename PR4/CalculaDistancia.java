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

    


    public static void main(String[] args) {

       
       
        int costoHueco = 1;     // delta
        int costoSustitucion = 2; // alfa

        try {
            
            String texto1 = new String(Files.readAllBytes(Paths.get("texto1.txt")), StandardCharsets.UTF_8);
            String texto2 = new String(Files.readAllBytes(Paths.get("texto2.txt")), StandardCharsets.UTF_8);
            
            System.out.println("Archivo 1: " + texto1.length() + " caracteres)");
            System.out.println("Archivo 2: " + texto2.length() + " caracteres)");
            System.out.println("Costo Hueco (delta): " + costoHueco);
            System.out.println("Costo Sustitución (alfa): " + costoSustitucion);

            CalculaDistancia calculador = new CalculaDistancia(costoHueco, costoSustitucion);
            
            long startTimeDP1 = System.currentTimeMillis(); 
            int distanciaDP1 = calculador.editDistanceDP(texto1, texto2);
            long endTimeDP1 = System.currentTimeMillis();
            long tiempoDP1 = (endTimeDP1 - startTimeDP1);

            System.out.println("Cálculo DP de A a B completado en " + tiempoDP1 + " ms.");
            System.out.println("La distancia con programacion dinamica es: " + distanciaDP1);
            
            long startTimeDP2 = System.currentTimeMillis(); 
            int distanciaDP2 = calculador.editDistanceDP(texto2, texto1);
            long endTimeDP2 = System.currentTimeMillis();
            long tiempoDP2 = (endTimeDP2 - startTimeDP2);

            System.out.println("Cálculo DP de B a A completado en " + tiempoDP2 + " ms.");
            System.out.println("La distancia con programacion dinamica es: " + distanciaDP2);

            long startTimeRec1 = System.currentTimeMillis();
            int distanciaRec1 = calculador.editDistanceRecursivo(texto1, texto2);
            long endTimeRec1 = System.currentTimeMillis();
            long tiempoRec1 = (endTimeRec1 - startTimeRec1);

            System.out.println("Cálculo Recursivo de A a B completado en " + tiempoRec1 + " ms.");
            System.out.println("La distancia recursiva es: " + distanciaRec1);

            long startTimeRec2 = System.currentTimeMillis();
            int distanciaRec2 = calculador.editDistanceRecursivo(texto2, texto1);
            long endTimeRec2 = System.currentTimeMillis();
            long tiempoRec2 = (endTimeRec2 - startTimeRec2);

            System.out.println("Cálculo Recursivo de B a A completado en " + tiempoRec2 + " ms.");
            System.out.println("La distancia recursiva es: " + distanciaRec2);
           
        } catch (IOException e) {
            System.err.println("Error al leer uno de los archivos: " + e.getMessage());
            System.err.println("Asegúrate de que los archivos existan.");
        }
    }
}