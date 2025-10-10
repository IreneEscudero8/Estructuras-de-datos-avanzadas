/**
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase para probar los metodos y su buen funcionamiento
 */

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

        System.out.println("\n=== RESULTADOS ===");

        int[] rec = m.hamburguesasRec(m1, m2, t);
        System.out.println("Recursivo: " + rec[0] + " hamburguesas, sobrante " + rec[1] + " min.");

        int[] memo = m.hamburguesasMemo(m1, m2, t);
        System.out.println("Memoización: " + memo[0] + " hamburguesas, sobrante " + memo[1] + " min.");

        int[] din = m.hamburguesaDin(m1, m2, t);
        System.out.println("Dinámico: " + din[0] + " hamburguesas, sobrante " + din[1] + " min.");

        sc.close();
    }
}