/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase que define una lista doblemente ligada ordenada segun prioridad del paciente
 */

public class DblyLnkSctr {    
    private LnrDblNd<Patient> head,tail;
    private int count;

    public DblyLnkSctr() {
        head = new LnrDblNd<Patient>();    // head sentinel node
        tail = new LnrDblNd<Patient>();    // tail sentinel node
        tail.setPrevious(head);
        head.setNext(tail);
        count = 0;    
    }
 
    
    public boolean addPatient(Patient person) {
     boolean agregado = false; 

     if (person == null) throw new ADTsException("addPatient(): null element");

     // Verificar si ya existe un paciente con el mismo id
     if (findById(person.getId()) == null) { 
         LnrDblNd<Patient> current = head.getNext();
         while (current != tail && current.getPatient().getPriority() <= person.getPriority()) {
             current = current.getNext();
         }

         LnrDblNd<Patient> auxnd = new LnrDblNd<Patient>(person);
         LnrDblNd<Patient> prv = current.getPrevious();

         auxnd.setNext(current);
         auxnd.setPrevious(prv);

         prv.setNext(auxnd);
         current.setPrevious(auxnd);

         count++;
         agregado = true;     }

     return agregado; 
   }
  
    public Patient first() throws ADTsException {
        if (isEmpty()) throw new ADTsException ("DblyLnkSctr first(): structure is Empty"); 
        
        LnrDblNd<Patient> first = head.getNext();
        return first.getPatient();
    }

    public Patient last() throws ADTsException {
        if (isEmpty()) throw new ADTsException ("DblyLnkSctr first(): structure is Empty"); 

        LnrDblNd<Patient> last = tail.getPrevious();
        return last.getPatient();
    }
    
    public Patient removeFirst() throws ADTsException {
        if (isEmpty()) throw new ADTsException("removeFirst(): structure is Empty"); 
        Patient result = first();
        removePatientById(result.getId());
        return result;
    }
   
    public boolean removePatientById(int id) {
        LnrDblNd<Patient> ndperson = findById(id);
        boolean removed = false;

        if (ndperson != null) {
            LnrDblNd<Patient> prv = ndperson.getPrevious();       
            LnrDblNd<Patient> nxt = ndperson.getNext();

            prv.setNext(nxt); 
            nxt.setPrevious(prv);

            count--;
            removed = true;
        }
        return removed;
    }
    
    public LnrDblNd<Patient> findById(int id) {
        LnrDblNd<Patient> current = head.getNext();
        LnrDblNd<Patient> result = null;

        while (current != tail && result == null) {
            if (current.getPatient().getId() == id) {
                result = current;   // encontrado
            }
            current = current.getNext();
        }
        return result;
         }

        public boolean containsById(int id) {
            return (findById(id) != null);
    }

        
    //find recursivo
    public LnrDblNd<Patient> findByIdRv(int id) {
        return findByIdRv(id, head.getNext());
    }

    private LnrDblNd<Patient> findByIdRv(int id, LnrDblNd<Patient> node) {
        LnrDblNd<Patient> result;

        if (node == tail) {
            result = null; // caso base no encontrado
        } else if (node.getPatient().getId() == id) {
            result = node; // caso base encontrado
        } else {
            result = findByIdRv(id, node.getNext()); // caso recursivo
        }

        return result;
    }
            
    //vaciar lista
    public void clear() {
        head = new LnrDblNd<Patient>();
        tail = new LnrDblNd<Patient>();
        tail.setPrevious(head);
        head.setNext(tail);
        count = 0;
    }
    
    public int size() {
        return count;
    }
   
    public boolean isEmpty() {
        return (count == 0);
    }
    
    //toString iterativo
    @Override
    public String toString() {
        String result = "  {DLS: c" + count + ":";

        if( isEmpty() ) {
            result = result + " EMPTY";
        } else {
            LnrDblNd<Patient> current = head.getNext();

            while (current.getNext() != null) {
                result = result + ", " + current.getPatient().toString();
                current = current.getNext();
            }
        }
        result = result + "}";
        return result;
    }   
    
    //toString recursivo
    public String toStringRv(){
        String result="";
        if( isEmpty() )
            result ="EMPTY";
        else{
        result = "  {DLS: " + count + ": ";
        result+=toStringRv(head.getNext())+ "}";
        }
        return result;
    }
    private String toStringRv(LnrDblNd<Patient> otro ){
        String result="";
        if(!otro.getNext().equals(tail)){//caso recursivo
            result= otro.getPatient()+", "+ toStringRv(otro.getNext());
        }else{ //caso base
            result+=otro.getPatient();
        }
            return result;
    }
         
   
}
