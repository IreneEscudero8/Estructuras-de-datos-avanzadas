/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase que define un nodo
 */
public class LnrDblNd<Patient> {
    private LnrDblNd<Patient> next;
    private LnrDblNd<Patient> previous;
    private Patient patient;

    public LnrDblNd() {
       previous = null;
       next = null;
       patient = null;
    }

    public LnrDblNd (Patient patient) {
       previous = null;
       next = null;
       this.patient =patient;
    }

    public LnrDblNd(LnrDblNd<Patient> prvs, LnrDblNd<Patient> nxt, Patient patient) {
        previous = prvs;
        next = nxt;
        this.patient =patient;
    }

    public LnrDblNd<Patient> getPrevious() {
       return previous;
    }

    public void setPrevious (LnrDblNd<Patient> dlnode) {
       previous = dlnode;
    }
    
    public LnrDblNd<Patient> getNext() {
       return next;
    }
    
    public void setNext (LnrDblNd<Patient> dlnode) {
       next = dlnode;
    }
    
    public Patient getPatient() {
       return patient;
    }

    public void setPatient (Patient patient) {
       this.patient = patient;
    }

    @Override
    public String toString() {
        String str= "", sprev="rfrnc", snext="rfrnc";
        if (patient == null) {
            return "-sentinel node-";
        } else {
            if( previous == null) sprev= "null";
            if( next == null) snext= "null";
            str= "PREVIOUS: " + sprev + "  NEXT: "  + snext +
                    "  ELEMENT: " + patient.toString(); 
        }
        return str;
    }
    
}

