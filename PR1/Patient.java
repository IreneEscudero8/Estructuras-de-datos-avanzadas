/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS 
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero 
 * Clase que define un paciente por su nombre, id y un arreglo con sus resultados de exámenes 
*/ 
public class Patient { 
    private String name; 
    private int id; 
    private int[] exams; 
    
    public Patient() { } 

    public Patient(String name, int id, int[] exams) { 
        this.name = name; 
        this.exams= exams; 
        this.id=id; 
    } 

    public int[] getExams() { 
        return exams; 
    } 

    public String getName() { 
        return name; 
    } 

    public int getId() { 
        return id; 
    }

    @Override 
    public boolean equals(Object obj) { 
        if (this == obj) { 
            return true; 
        } if (obj == null) { 
            return false; 
        } if (getClass() != obj.getClass()) {
            return false; 
        } 
        final Patient other = (Patient) obj; 
        return this.id == other.id; 
    } 

    @Override 
    public String toString() { 
        String str; str= "ID: " + getId()+ "\n NAME: " + getName() + "\n EXAMS: " + getExams(); return str; 
    } 

} 

