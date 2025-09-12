import java.util.HashMap;
import java.util.Map;

/**
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS 
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero 
 * Esta clase implementa una tabla de hash para almacenar y procesar registros de pacientes.
 * Ahora cuenta el total de pacientes idénticos, igual que la lista lineal.
 */
public class PatientHashTable {

    private Map<Patient, Integer> patientCounts;
    private int identicalPatientsCount;

    public PatientHashTable() {
        this.patientCounts = new HashMap<>();
        this.identicalPatientsCount = 0;
    }

    public void addPatient(Patient patient) {
        int count = patientCounts.getOrDefault(patient, 0);
        count++;
        patientCounts.put(patient, count);

        // Si el paciente aparece más de una vez, sumamos este nuevo a duplicados
        if (count > 1) {
            identicalPatientsCount++;
        }
    }

    public int getIdenticalPatientsCount() {
        return this.identicalPatientsCount;
    }

    public int getUniquePatientCount() {
        return this.patientCounts.size();
    }

    public void printResults() {
        if (this.identicalPatientsCount > 0) {
            System.out.println("se encontraron " + this.identicalPatientsCount + " pacientes idénticos");
        } else {
            System.out.println("no hay dos pacientes con registros idénticos");
        }
    }
}
