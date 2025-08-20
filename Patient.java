
package practica0;

/* * PROYECTO ESTRUCTURA DE DATOS AVANZADAS 
   * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero 
   * Clase que define un paciente por su nombre, id y prioridad 
*/ 
public class Patient { 
    private String name; 
    private int id; 
    private int priority; 
    
    public Patient() { } 

    public Patient(String name, int priority, int id) { 
        this.name = name; 
        this.priority = priority; 
        this.id=id; 
    } 

    public int getPriority() { 
        return priority; 
    } 

    public void setPriority (int num) { 
        priority = num; 
    } 

    public String getName() { 
        return name; 
    } 

    public void setName (String newName) { 
        name=newName; 
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
        String str; str= "ID: " + getId()+ "\n NAME: " + getName() + "\n PRIORITY: " + getPriority(); return str; 
    } 

} 
