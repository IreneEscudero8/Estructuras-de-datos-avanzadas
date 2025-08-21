
package practica0;

/**
 *
 * @author LARIG
 */
public class Practica0 {

    public static void main(String[] args) {
        // Crear el árbol de pacientes
        PatientBST triage = new PatientBST();

        // Insertar pacientes con diferentes prioridades
        triage.insert(new Patient("Juan", 5, 1));
        triage.insert(new Patient("Maria", 2, 2));
        triage.insert(new Patient("Pedro", 8, 3));
        triage.insert(new Patient("Ana", 1, 4));
        triage.insert(new Patient("Luis", 7, 5));
        triage.insert(new Patient("Carla", 2, 6)); // misma prioridad que María (desempata con ID)

        // Mostrar pacientes en orden (de más urgente a menos urgente)
        System.out.println("Pacientes en orden de prioridad:");
        triage.inOrder();

        // Buscar un paciente
        int searchPriority = 2;
        System.out.println("\nBuscando paciente con prioridad " + searchPriority + ":");
        Patient found = triage.search(searchPriority);
        if (found != null) {
            System.out.println("Encontrado: " + found);
        } else {
            System.out.println("No existe paciente con esa prioridad.");
        }

        // Mostrar el siguiente paciente a atender
        System.out.println("\nSiguiente paciente a atender:");
        System.out.println(triage.getNextPatient());

        // Atender al más urgente (eliminar prioridad más baja)
        System.out.println("\nAtendiendo al paciente más urgente (prioridad 1)...");
        triage.delete(1);

        // Mostrar pacientes después de atender
        System.out.println("\nPacientes restantes en orden de prioridad:");
        triage.inOrder();
    }
}

    
