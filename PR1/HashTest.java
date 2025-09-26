/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase principal para probar la funcionalidad de Patient y PatientHashTable.
*/
public class HashTest {

    public static void main(String[] args) {
        PatientHashTable patientTable = new PatientHashTable();
        int[] exams1 = {10, 85, 92, 45};
        int[] exams2 = {70, 65, 88, 91};
        int[] exams3 = {10, 85, 92, 45}; // Duplicado de exams1
        int[] exams4 = {55, 78, 99, 100};
        int[] exams5 = {70, 65, 88, 91}; // Duplicado de exams2
        int[] exams6 = {45, 92, 85, 10}; // Mismos valores que exams1, pero en otro orden
        int[] exams7 = {120, 80, 75, 60};

        Patient2 paciente1 = new Patient2(exams1);
        Patient2 paciente2 = new Patient2(exams2);
        Patient2 paciente3 = new Patient2(exams3);
        Patient2 paciente4 = new Patient2(exams4);
        Patient2 paciente5 = new Patient2(exams5);
        Patient2 paciente6 = new Patient2(exams6);
        Patient2 paciente7 = new Patient2(exams7);
        
        System.out.println("Añadiendo pacientes a la tabla...");

        patientTable.addPatient(paciente1);
        patientTable.addPatient(paciente2);
        patientTable.addPatient(paciente3); // Este debería ser contado como idéntico.
        patientTable.addPatient(paciente4);
        patientTable.addPatient(paciente5); // Este también debería ser contado como idéntico.
        patientTable.addPatient(paciente6); // Este debería ser único por el orden.
        patientTable.addPatient(paciente7);
        
        System.out.println("Proceso de añadir pacientes finalizado.\n");


        System.out.println("------ Resultados del Analisis ------");
        System.out.println("Número de pacientes únicos registrados: " + patientTable.getUniquePatientCount());
        System.out.println("Número de registros identicos encontrados: " + patientTable.getIdenticalPatientsCount());
        
        System.out.print("Conclusion: ");
        patientTable.printResults();
        System.out.println("------------------------------------");

        // Verificación adicional del hashCode
        System.out.println("\n--- Verificacion de HashCodes ---");
        System.out.println("HashCode Paciente 1 ([10, 85, 92, 45]): " + paciente1.hashCode());
        System.out.println("HashCode Paciente 3 ([10, 85, 92, 45]): " + paciente3.hashCode());
        System.out.println("HashCode Paciente 6 ([45, 92, 85, 10]): " + paciente6.hashCode());
        System.out.println("P1 y P3 son iguales? " + paciente1.equals(paciente3)); // Debería ser true
        System.out.println("P1 y P6 son iguales? " + paciente1.equals(paciente6)); // Debería ser false
        System.out.println("------------------------------------");
    }
}
