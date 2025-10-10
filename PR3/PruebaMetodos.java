import java.util.Scanner;

public class PruebaMetodos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Metodos m = new Metodos();

        System.out.print("Tiempo de hamburguesa tipo 1 (m): ");
        int m1 = sc.nextInt();

        System.out.print("Tiempo de hamburguesa tipo 2 (n): ");
        int m2 = sc.nextInt();

        System.out.print("Tiempo total disponible (t): ");
        int t = sc.nextInt();

        int repeticiones = 5; // número de veces que se repite cada método
        System.out.println("\n=== PROMEDIO DE TIEMPOS DE EJECUCIÓN (en segundos) ===");

        double tiempoRec = medirPromedio(() -> m.hamburguesasRec(m1, m2, t), repeticiones);
        double tiempoMemo = medirPromedio(() -> m.hamburguesasMemo(m1, m2, t), repeticiones);
        double tiempoDin = medirPromedio(() -> m.hamburguesaDin(m1, m2, t), repeticiones);

        System.out.printf("Recursivo: %.8f s%n", tiempoRec);
        System.out.printf("Memoización: %.8f s%n", tiempoMemo);
        System.out.printf("Dinámico: %.8f s%n", tiempoDin);

        sc.close();
    }

    // Método para medir el promedio de tiempo de una función Runnable
    private static double medirPromedio(Runnable funcion, int repeticiones) {
        double total = 0;
        for (int i = 0; i < repeticiones; i++) {
            System.gc(); // limpia memoria entre pruebas
            long inicio = System.nanoTime();
            funcion.run();
            long fin = System.nanoTime();
            total += (fin - inicio) / 1_000_000_000.0;
        }
        return total / repeticiones;
    }
}
