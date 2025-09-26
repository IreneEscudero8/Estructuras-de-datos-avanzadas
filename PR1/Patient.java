/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS 
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero 
 * Clase que define un paciente por un arreglo con sus resultados de exámenes 
*/ 
import java.util.Arrays;
public class Patient2 { 
    private int[] exams; 
    private final int DEFAULT_CAPACITY = 10;
    
    public Patient2() {
        exams = new int[DEFAULT_CAPACITY];
    }

    public Patient2(int[] exams) { 
        this.exams= exams; 
    } 

    public int[] getExams() { 
        return exams; 
    } 

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Patient2 other = (Patient2) obj;
        return Arrays.equals(this.exams, other.exams);
    }

    @Override
    public int hashCode() {
        // Un multiplicador primo ayuda a reducir las colisiones. El 31 es un estándar.
        final int prime = 31;

        // Se inicia con un valor no nulo para asegurar que todas las posiciones contribuyan.
        int result = 1;

        for (int examValue : exams) {
            // Esta fórmula combina los valores de forma sensible al orden.
            // El desbordamiento de entero (overflow) es un comportamiento esperado e
            // inofensivo para el cálculo del hash.
            result = prime * result + examValue;
        }

        return result;
    }

    /* Por ejemplo, así es como el orden cambia el resultado:
     * - Para el arreglo [10, 20]: el hash es (31 * 1 + 10) * 31 + 20 = 1291
     * - Para el arreglo [20, 10]: el hash es (31 * 1 + 20) * 31 + 10 = 1591
     */

    @Override 
    public String toString() { 
        String str; str= "EXAMS: " + getExams(); return str; 
    } 

} 

