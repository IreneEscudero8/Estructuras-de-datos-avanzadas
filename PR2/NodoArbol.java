/**
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase que representa un nodo en el árbol binario utilizado para la distribución de paquetes.
 */
public class NodoArbol {
    int valor; // Almacena el peso si es una hoja, o se deja sin usar si es un nodo intermedio.
    NodoArbol izquierdo, derecho;

    NodoArbol(int valor) {
        this.valor = valor;
    }

    // Constructor para nodos intermedios (sin valor, solo conexiones)
    NodoArbol(NodoArbol izquierdo, NodoArbol derecho) {
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    // Verifica si el nodo es una hoja (una oficina con paquetes)
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }
}