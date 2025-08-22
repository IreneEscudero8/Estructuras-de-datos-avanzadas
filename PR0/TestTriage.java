/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase clase tipo main que verifica el buen funcionamiento del sistema en diversos casos 
*/
public class TestTriage {
    public static void main(String[] args) {
        DblyLnkSctr lista = new DblyLnkSctr();

        // --- Crear pacientes ---
        Patient p1 = new Patient("Alice", 5 ,1);
        Patient p2 = new Patient("Bob", 3, 2);
        Patient p3 = new Patient("Charlie", 8, 3);
        Patient p4 = new Patient("Diana", 1, 4);

        // --- Agregar pacientes ---
        System.out.println("Agregar p1: " + lista.addPatient(p1)); // true
        System.out.println("Agregar p2: " + lista.addPatient(p2)); // true
        System.out.println("Agregar p3: " + lista.addPatient(p3)); // true
        System.out.println("Agregar p4: " + lista.addPatient(p4)); // true

        System.out.println("\nLista actual:");
        System.out.println(lista.toString());

        // --- Intentar agregar duplicado ---
        System.out.println("\nIntentar agregar p2 otra vez: " + lista.addPatient(p2)); // false

        // --- Buscar por id ---
        System.out.println("\nBuscar paciente id=3: " + lista.findById(3)); // Charlie
        System.out.println("Buscar paciente id=99: " + lista.findById(99)); // null

        // --- Eliminar paciente ---
        System.out.println("\nEliminar paciente id=2: " + lista.removePatientById(2)); // true
        System.out.println("Eliminar paciente id=99: " + lista.removePatientById(99)); // false

        System.out.println("\nLista después de eliminaciones:");
        System.out.println(lista.toString());

        // --- Agregar paciente de máxima prioridad (menos urgente = más alto número) ---
        Patient pMax = new Patient("Max", 10, 5);
        System.out.println("\nAgregar paciente máxima prioridad: " + lista.addPatient(pMax));

        // --- Agregar paciente de mínima prioridad (más urgente = 1) ---
        Patient pMin = new Patient("Min", 0, 6);
        System.out.println("Agregar paciente mínima prioridad: " + lista.addPatient(pMin));

        System.out.println("\nLista final:");
        System.out.println(lista.toString());
    }
    
    
}
