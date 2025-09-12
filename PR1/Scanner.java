import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Clase de ayuda para leer datos desde consola usando Scanner.
 * - No cierra System.in (evita "romper" el flujo de entrada del programa).
 * - Valida y repite la pregunta hasta obtener un valor correcto.
 * - Maneja el clásico problema de nextInt()/nextDouble() + nextLine().
 *
 * Uso:
 *   int edad = ScannerUtil.readInt("Edad: ", 0, 150);
 *   double precio = ScannerUtil.readDouble("Precio: ");
 *   String nombre = ScannerUtil.readLine("Nombre: ");
 */
public final class ScannerUtil {
    // Un único Scanner compartido. Locale.US usa punto decimal; cambia si prefieres coma.
    private static final Scanner SC = new Scanner(System.in).useLocale(Locale.US);

    private ScannerUtil() { /* evitar instanciación */ }

    /** Lee una línea completa (incluye espacios). Nunca retorna null. */
    public static String readLine(String prompt) {
        System.out.print(prompt);
        String s = SC.nextLine();
        // Normaliza null/EOF a cadena vacía
        return (s == null) ? "" : s;
    }

    /** Lee un int con reintentos hasta que el usuario escriba un entero válido. */
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String token = SC.nextLine();
            try {
                return Integer.parseInt(token.trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Ingresa un número entero válido.");
            } catch (NoSuchElementException e) {
                System.out.println("⚠️  Fin de entrada. Intenta de nuevo.");
            }
        }
    }

    /** Lee un int acotado entre [min, max] (inclusive). */
    public static int readInt(String prompt, int min, int max) {
        while (true) {
            int v = readInt(prompt);
            if (v < min || v > max) {
                System.out.printf("⚠️  Debe estar entre %d y %d.%n", min, max);
            } else {
                return v;
            }
        }
    }

    /** Lee un long con reintentos. */
    public static long readLong(String prompt) {
        while (true) {
            System.out.print(prompt);
            String token = SC.nextLine();
            try {
                return Long.parseLong(token.trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Ingresa un número entero (long) válido.");
            }
        }
    }

    /** Lee un double con reintentos. Usa Locale.US por defecto (punto decimal). */
    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String token = SC.nextLine();
            try {
                return Double.parseDouble(token.trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Ingresa un número decimal válido (usa punto).");
            }
        }
    }

    /** Lee un double acotado entre [min, max]. */
    public static double readDouble(String prompt, double min, double max) {
        while (true) {
            double v = readDouble(prompt);
            if (v < min || v > max) {
                System.out.printf("⚠️  Debe estar entre %.4f y %.4f.%n", min, max);
            } else {
                return v;
            }
        }
    }

    /**
     * Lee un boolean sensible a español/inglés.
     * Acepta: s, si, sí, y, yes, true → true
     *         n, no, false → false
     */
    public static boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String t = SC.nextLine().trim().toLowerCase();
            if (t.matches("s|si|sí|y|yes|true")) return true;
            if (t.matches("n|no|false")) return false;
            System.out.println("⚠️  Responde sí/s o no/n (o true/false).");
        }
    }

    /** Lee una opción de un conjunto permitido (case-insensitive). Retorna la opción elegida en su forma original del arreglo. */
    public static String readOption(String prompt, String... opciones) {
        if (opciones == null || opciones.length == 0) {
            throw new IllegalArgumentException("Debes proporcionar opciones.");
        }
        while (true) {
            System.out.print(prompt);
            String ans = SC.nextLine().trim();
            for (String op : opciones) {
                if (ans.equalsIgnoreCase(op)) return op; // conserva capitalización original
            }
            System.out.print("⚠️  Opción inválida. Opciones: ");
            for (int i = 0; i < opciones.length; i++) {
                System.out.print(opciones[i] + (i + 1 < opciones.length ? ", " : ""));
            }
            System.out.println();
        }
    }

    /** Demostración sencilla. Ejecuta este main para probar rápidamente. */
    public static void main(String[] args) {
        System.out.println("=== Demo ScannerUtil ===");
        String nombre = ScannerUtil.readLine("Nombre: ");
        int edad = ScannerUtil.readInt("Edad (0–150): ", 0, 150);
        double salario = ScannerUtil.readDouble("Salario mensual (usa punto): ", 0.0, 1_000_000.0);
        boolean estudiante = ScannerUtil.readBoolean("¿Eres estudiante? (sí/no): ");
        String turno = ScannerUtil.readOption("Turno (mañana/tarde/noche): ", "mañana", "tarde", "noche");

        System.out.println("\n--- Resumen ---");
        System.out.printf("Nombre: %s%n", nombre);
        System.out.printf("Edad: %d%n", edad);
        System.out.printf("Salario: %.2f%n", salario);
        System.out.printf("Estudiante: %s%n", estudiante ? "Sí" : "No");
        System.out.printf("Turno: %s%n", turno);
    }
}
