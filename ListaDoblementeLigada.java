/*
PROYECTO ESTRUCTURA DE DATOS AVANZADAZ
Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
*/
package DblyLnkSctr;

import java.util.Iterator;    // Iterator
import ADTsException.*;

public class DblyLnkSctr<T> implements Iterable<T>
{    
    private LnrDblNd<T> head,tail;
    private int count;

    public DblyLnkSctr() {
        head = new LnrDblNd<T>();    // head sentinel node
        tail = new LnrDblNd<T>();    // tail sentinel node
        tail.setPrevious(head);
        head.setNext(tail);
        count = 0;    }
 
    /**
     * Adds the specified element to the front/head of this structure.
    */
    public void addFirst(T elem){
        if (elem==null) throw new ADTsException ("DblyLnkSctr addFirst(): null element");

        LnrDblNd<T> newnd= new LnrDblNd<T>();
        newnd.setElement(elem);    // Node with element inside    
        addNd(head, head.getNext(), newnd);    // count could be >=0        
    }    

    // Adding node adnd, coming with element inside.    
    private void addNd(LnrDblNd<T> prv, LnrDblNd<T> nxt, LnrDblNd<T> adnd) {
        adnd.setNext(nxt);
        adnd.setPrevious(prv);
        
        prv.setNext(adnd);
        nxt.setPrevious(adnd);
        count++;        
    }

     /**
     * Adds the specified element to the rear of this list.
     */
    public void addLast(T elem) {
        if (elem==null) throw new ADTsException ("DblyLnkSctr addLast(): null element");
        
        LnrDblNd<T> newnd= new LnrDblNd<T>();
        newnd.setElement(elem);    // Node with element inside          
        addNd(tail.getPrevious(), tail, newnd);    // count could be >=0
    }
    
    public T first() throws ADTsException {
        if (isEmpty()) throw new ADTsException ("DblyLnkSctr first(): structure is Empty"); 
        
        LnrDblNd<T> first = head.getNext();
        return first.getElement();
    }

    public T last() throws ADTsException {
        if (isEmpty()) throw new ADTsException ("DblyLnkSctr first(): structure is Empty"); 

        LnrDblNd<T> last = tail.getPrevious();
        return last.getElement();
    }
    
    public T removeFirst() throws ADTsException {
        if (isEmpty()) throw new ADTsException ("DblyLnkSctr removeFirst(): structure is Empty"); 

        T result = first();
        removeNd(head.getNext());
        return result;
    }

    // Unlink node ndelm, keeping inside the references to the element, the
    //    the next, and the previous.    
    private void removeNd(LnrDblNd<T> ndelm) {
        LnrDblNd<T> prv, nxt;   
        prv = ndelm.getPrevious();       
        nxt = ndelm.getNext();
        
        prv.setNext(nxt); 
        nxt.setPrevious(prv);
        
        ndelm= null;
        count--;
    }    

    public T removeLast () throws ADTsException {
        if (isEmpty()) throw new ADTsException ("DblyLnkSctr removeLast(): structure is Empty"); 

        T result = last();
        removeNd(tail.getPrevious());
        return result;
    }

    // Metodo iterativo, navegando
    private LnrDblNd<T> find(T elem) {
        int tam= size();
        int i= 1;
        LnrDblNd<T> fnd= null;
        LnrDblNd<T> current= head.getNext();

        while( i<=tam ) {
            if( current.getElement().equals(elem) ) {
                fnd= current;
                i= tam+1;
            }
            current= current.getNext();
            i++;
        }
        return fnd;
    }
        
    public boolean contains (T elem) {
        return ( findRecursivo(elem) != null );//Recursivo para poder probarlo
    }
     

    public Iterator<T> iterator() {
        return new LnrDblNdItrtr<T> (head.getNext(),count);
    }
    
    // Reset the Doubly Linked Structure
    public void clear() {
        head = new LnrDblNd<T>();
        tail = new LnrDblNd<T>();
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

    @Override
    public String toString() {
        String result = "  {DLS: c" + count + ": ";

        if( isEmpty() ) {
            result = result + " EMPTY";
        } else {
            LnrDblNd<T> current = head.getNext();

            while (current.getNext() != null) {
                result = result + ", " + current.getElement().toString();
                current = current.getNext();
            }
        }
        result = result + "}";
        return result;
    }   
   FIND RECURSIVO
    // Ejercicio 1: find
    // Método público de find donde se aplica la recursividad
    //Esta función busca un nodo que contiene un elemento específico en una lista enlazada de forma recursiva
    public LnrDblNd<T> findRecursivo(T elem) {
        LnrDblNd<T> fnd; //Declaración de una variable fnd para almacenar el nodo encontrado
        fnd=findRecursivo(elem,head.getNext()); //llama al método recursivo comenzando desde el nodo que sigue el head
         return fnd; //devuelve el nodo encontrado
     }
     // Método privado de find
    //Esta función implementa la búsqueda recursiva del nodo con el elemento desde otro nodo dado
     private LnrDblNd<T> findRecursivo(T elem,  LnrDblNd<T> otro) {
         LnrDblNd<T> fnd=null; //Declara una variable fnd que almacena el nodo encontrado
         if(otro!=tail){
            if(elem.equals(otro.getElement())){ //Compara el elemento buscado con el elemento del nodo actual
               fnd=otro; //Si los elementos coinciden guarda el nodo actual en fnd
            }else{
              fnd=findRecursivo(elem, otro.getNext()); //bisca el elemento recursivo en el siguiente nodo
            }
         }
         return fnd; //Regresa el nodo encontrado
    }
    // String recursivo 
     // Este método genera una representación en cadena de los elementos de la lista enlazada de forma recursiva
    public String toStringRecursivo(){
        String result=""; // Declaramos una variable para construir la cadena resultante
        if( isEmpty() )
            result ="EMPTY"; // Si la lista esta vacía se estable que esta vacía la cadena
        else{
        result = "  {DLS: " + count + ": "; //Sino esta vacía se genera una cadena indicando la longitud de la lista 
        result+=toString(head.getNext())+ "}"; //Llama un método auziliar para realizar la recursividad para procesar los nodos de todos los datos que van después de head
        }
        return result; //Regresa como resultado la cadena
    }
    private String toString(LnrDblNd<T> otro ){
        //Se construye un método público donde se lleve a cabo la representación en cadena de los elementos de los nodos         de forma recursiva
        String result=""; //Declara una variable para construir el resultado
        if(!otro.getNext().equals(tail)){//Caso recursivo: verifica que el nodo actual no es el último antes de la cola (tail)
            result= otro.getElement()+", "+ toString(otro.getNext()); //Agrega el elemento del nodo actual al resultado,              seguido de una coma y continúa procesando el siguiente nodo
        }else{ //Caso base: si el nodo actual es el último antes de la cola
            result+=otro.getElement(); // Deveuelve la representación parcial del resultado
        }
        return result; // Regresa la cadena 
    }
       
} //clase 
