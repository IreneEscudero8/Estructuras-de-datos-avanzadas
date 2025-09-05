/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS 
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero 
 * Clase que define un paciente por un arreglo con sus resultados de exámenes 
*/ 
import java.util.Arrays;
public class Patient { 
    private int[] exams; 
    private final int DEFAULT_CAPACITY = 10;
    
    public Patient() {
        exams = new int[DEFAULT_CAPACITY];
    }

    public Patient(int[] exams) { 
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
        Patient other = (Patient) obj;
        return Arrays.equals(this.exams, other.exams);
    }

    @Override 
    public String toString() { 
        String str; str= "EXAMS: " + getExams(); return str; 
    } 

} 

