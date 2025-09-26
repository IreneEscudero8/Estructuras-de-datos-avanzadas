/**
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase que representa un nodo en el árbol binario utilizado para la distribución de paquetes.
 */
public class NodoArbol {
    int valor;
    NodoArbol izquierdo, derecho;
    boolean esHoja; 

    // Constructor para nodos hoja 
    public NodoArbol(int valor) {
        this.valor = valor;
        this.esHoja = true;
    }

    // Constructor para nodos intermedios.
    public NodoArbol(NodoArbol izquierdo, NodoArbol derecho) {
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.esHoja = false; 
    }

    // Método que verifica si el nodo es una hoja.
    public boolean esHoja() {
        return esHoja;
    }
}