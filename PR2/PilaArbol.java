/**
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase que implementa una pila para procesar nodos de un árbol binario de manera iterativa.
 * Utiliza una estructura de pila basada en nodos para recorrer el árbol sin recursión.
 */
public class PilaArbol<T> {
    
    private Nodo<T> cima; 
    
    public PilaArbol() {
        cima = null;
    }

    public PilaArbol(T dato){
        cima=null;
    }

    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public T pop() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        T dato = cima.dato;
        cima = cima.siguiente;
        return dato;
    }

    public T peek() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        return cima.dato;
    }

    /**
     * Verifica si la pila está vacía.
     */
    public boolean estaVacia() {
        return cima == null;
    }
}




