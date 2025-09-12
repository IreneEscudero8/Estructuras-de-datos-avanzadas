/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase que define una lista, un arreglo lineal, que almacena pacientes
 */
public class ListStructure {
    private Patient[] list;
    private int count;

    public List(int n) {
       list= new Patient[n];
       count= 0;
    }
 
    public boolean addPatient(Patient person){
        boolean agregado=false;
        if(count<list.length){
            agregado=true;
            list[count]=person;
            count++;
        }
        return agregado;
    }

    public int repetidos() {
        int totalIdenticos = 0;
        boolean[] contado = new boolean[count];
        
        for (int i = 0; i < count; i++) {
            // Checar si el paciente ya fue revisado
            if (!contado[i]) {
                int tamanhoGrupo = 1;
                
                // Comparar paciente con el resto
                for (int j = i + 1; j < count; j++) {
                    if (list[i].equals(list[j])) {
                        tamanhoGrupo++;
                        // Marcarlo como contado
                        contado[j] = true;
                    }
                }
                
                // Sumar el total de grupos de duplicados
                if (tamanhoGrupo > 1) {
                    totalIdenticos += tamanhoGrupo;
                }
            }
        }        
        return totalIdenticos;
    }


    
}
