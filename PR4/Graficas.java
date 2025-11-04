/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase para generar datos de tiempo de ejecución para los tres métodos de cálculo
 */
import java.util.Random;

public class Graficas {

    private static final int[] LENGTHS = {5, 50, 500, 5000, 50000};
    private static final int COSTO_HUECO = 1;
    private static final int COSTO_SUSTITUCION = 2;

    public static void main(String[] args) {
        CalculaDistancia calculator = new CalculaDistancia(COSTO_HUECO, COSTO_SUSTITUCION);

        // Almacenes de resultados finales  
        StringBuilder resultsRec = new StringBuilder("Recursion: ");
        StringBuilder resultsMemo = new StringBuilder("Memoizacion: ");
        StringBuilder resultsDP = new StringBuilder("DP: ");

        // Pruebas para cada longitud
        for (int N : LENGTHS) {
            String textA = generateRandomString(N, 0);
            String textB = generateRandomString(N, 1);
            
            String resultStr = "";

            // Recursión
            long timeRec = -1; 
            try {
                long start = System.nanoTime();
                // Esta llamada fallará por TimeLimit o StackOverflow en N > 18
                calculator.editDistanceRecursivo(textA, textB);
                timeRec = (System.nanoTime() - start) / 1_000_000;
                resultStr = String.valueOf(timeRec);
            } catch (CalculaDistancia.TiempoExcedidoException | StackOverflowError e) {
                // Captura TimeLimit o StackOverflow 
                resultStr = "Error";
            }
            resultsRec.append(resultStr).append(", ");

            // Memoización 
            timeRec = -1;
            try {
                long start = System.nanoTime();
                calculator.editDistanceMemoizado(textA, textB);
                timeRec = (System.nanoTime() - start) / 1_000_000;
                resultStr = String.valueOf(timeRec);
            } catch (CalculaDistancia.TiempoExcedidoException | StackOverflowError | OutOfMemoryError e) {
                // Captura TimeLimit, desbordamiento o FALLO de memoria (OutOfMemoryError)
                resultStr = "Error";
            }
            resultsMemo.append(resultStr).append(", ");
            
            // Programación Dinámica
            timeRec = -1;
            try {
                long start = System.nanoTime();
                calculator.editDistanceDP(textA, textB);
                timeRec = (System.nanoTime() - start) / 1_000_000;
                resultStr = String.valueOf(timeRec);
            } catch (OutOfMemoryError e) {
                // Captura error de memoria (OutOfMemoryError)
                resultStr = "Error";
            } catch (Exception e) {
                // Captura cualquier otro error
                resultStr = "Error";
            }
            resultsDP.append(resultStr).append(", ");
        }

        // Salida
        System.out.println(resultsRec.toString());
        System.out.println(resultsMemo.toString());
        System.out.println(resultsDP.toString());
    }

    // Genera una cadena aleatoria de longitud length
    private static String generateRandomString(int length, int seed) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";
        Random random = new Random(length + seed); 
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}