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
        raiz=null;
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

/**
 * Ejemplo de uso: Recorrido en preorden iterativo de un árbol binario.
 */
class NodoArbol {
    int valor;
    NodoArbol izquierdo, derecho;

    NodoArbol(int valor) {
        this.valor = valor;
    }
}

class ProcesadorArbol {
    // Recorrido preorden iterativo usando la pila
    public void preordenIterativo(NodoArbol cima) {
        if (cima == null) return;
        PilaArbol<NodoArbol> pila = new PilaArbol<>();
        pila.push(cima);

        while (!pila.estaVacia()) {
            NodoArbol actual = pila.pop();
            System.out.print(actual.valor + " "); // Procesa el nodo

            // Primero se inserta el derecho, luego el izquierdo (para que el izquierdo se procese primero)
            if (actual.derecho != null) pila.push(actual.derecho);
            if (actual.izquierdo != null) pila.push(actual.izquierdo);
        }
    }
}


