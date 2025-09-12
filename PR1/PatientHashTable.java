import java.util.HashSet;
import java.util.Set;

/**
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS 
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero 
 * * Esta clase implementa una tabla de hash para almacenar y procesar registros de pacientes.
 * Utiliza un HashSet interno para detectar eficientemente los registros idénticos.
 */
public class PatientHashTable {

    private Set<Patient> uniquePatients;
    
    private int identicalPatientsCount;

    public PatientHashTable() {
        this.uniquePatients = new HashSet<>();
        this.identicalPatientsCount = 0;
    }

    public void addPatient(Patient patient) {
        if (!uniquePatients.add(patient)) {
            this.identicalPatientsCount++;
        }
    }

    public int getIdenticalPatientsCount() {
        return this.identicalPatientsCount;
    }
    
    public int getUniquePatientCount(){
        return this.uniquePatients.size();
    }

    public void printResults() {
        if (this.identicalPatientsCount > 0) {
            System.out.println("se encontraron " + this.identicalPatientsCount + " pacientes idénticos");
        } else {
            System.out.println("no hay dos pacientes con registros idénticos");
        }
    }

}
