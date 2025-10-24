//Para dos cadenas, s1 y s2 el costo es d=1 por cada hueco y a = 2 por cada pareja en M (cambiar una letra por otra) 

/**
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 */

public class recursivo {
    public int EditDistance(String s1, String s2) {
        return editDistanceHelper(s1, s2, s1.length(), s2.length());
    }

    public int editDistanceHelper(String s1, String s2, int m, int n) {
                // Caso base: si una cadena está vacía
                if (m == 0) return n; // Insertar todos los caracteres de s2
                if (n == 0) return m; // Eliminar todos los caracteres de s1

                // Si los últimos caracteres son iguales, ignorarlos y continuar con los restantes
                if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
                    return editDistanceHelper(s1, s2, m - 1, n - 1);
                }

                // Si los últimos caracteres son diferentes, considerar todas las operaciones posibles
                int insertOp = editDistanceHelper(s1, s2, m, n - 1);    // Insertar
                int deleteOp = editDistanceHelper(s1, s2, m - 1, n);    // Eliminar
                int replaceOp = editDistanceHelper(s1, s2, m - 1, n - 1); // Reemplazar

                // Retornar el mínimo de las tres operaciones más uno
                return 1 + Math.min(insertOp, Math.min(deleteOp, replaceOp));
            }
    }

