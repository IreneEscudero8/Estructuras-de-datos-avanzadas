import java.util.Scanner;
/**
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase que procesa un árbol binario para la distribución de paquetes, con métodos en versiones recursivas e iterativas.
 */

public class ProcesadorArbol {


    // ----------------- METODOS ITERATIVOS ----------------------------------------------------
    
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

// Calcula el peso total de los paquetes (suma de hojas) 
public int pesoTotalIterativo(NodoArbol raiz) {
    if (raiz == null) return 0;
    int peso = 0;
    PilaArbol<NodoArbol> pila = new PilaArbol<>();
    pila.push(raiz);

    while (!pila.estaVacia()) {
        NodoArbol actual = pila.pop();
        if (actual.esHoja()) {
            peso += actual.valor;
        } else {
            if (actual.derecho != null) pila.push(actual.derecho);
            if (actual.izquierdo != null) pila.push(actual.izquierdo);
        }
    }
    return peso;
}

// Calcula la altura del árbol 
public int alturaIterativa(NodoArbol raiz) {
    if (raiz == null) return 0;
    java.util.Queue<NodoArbol> cola = new java.util.LinkedList<>();
    cola.add(raiz);
    int altura = -1;

    while (!cola.isEmpty()) {
        int nivel = cola.size();
        for (int i = 0; i < nivel; i++) {
            NodoArbol actual = cola.poll();
            if (actual.izquierdo != null) cola.add(actual.izquierdo);
            if (actual.derecho != null) cola.add(actual.derecho);
        }
        altura++;
    }
    return altura;
}

// Calcula el número total de calles (ida y vuelta a cada hoja)
public int callesTotalesIterativo(NodoArbol raiz) {
    if (raiz == null) return 0;
    int calles = 0;
    PilaArbol<NodoArbol> pila = new PilaArbol<>();
    pila.push(raiz);

    while (!pila.estaVacia()) {
        NodoArbol actual = pila.pop();
        if (!actual.esHoja()) {
            // Cada nodo interno conecta dos calles de ida y vuelta (2+2=4)
            calles += 4;
            if (actual.derecho != null) pila.push(actual.derecho);
            if (actual.izquierdo != null) pila.push(actual.izquierdo);
        }
    }
    return calles;
}

// Obtiene la ruta óptima iterativa (camino más largo)
public String obtenerRutaIterativa(NodoArbol raiz) {
    if (raiz == null) return "";
    // Usaremos BFS para encontrar el camino más largo hasta una hoja
    class Par {
        NodoArbol nodo;
        String ruta;
        Par(NodoArbol nodo, String ruta) {
            this.nodo = nodo;
            this.ruta = ruta;
        }
    }

    java.util.Queue<Par> cola = new java.util.LinkedList<>();
    cola.add(new Par(raiz, String.valueOf(raiz.valor)));
    String mejorRuta = "";
    int mejorAltura = -1;

    while (!cola.isEmpty()) {
        Par actual = cola.poll();
        NodoArbol nodo = actual.nodo;
        String ruta = actual.ruta;

        if (nodo.esHoja()) {
            // Contar altura de esta ruta
            int altura = ruta.split(" <- ").length - 1;
            if (altura > mejorAltura) {
                mejorAltura = altura;
                mejorRuta = ruta;
            }
        } else {
            if (nodo.izquierdo != null) {
                cola.add(new Par(nodo.izquierdo, String.valueOf(nodo.izquierdo.valor) + " <- " + ruta));
            }
            if (nodo.derecho != null) {
                cola.add(new Par(nodo.derecho, String.valueOf(nodo.derecho.valor) + " <- " + ruta));
            }
        }
    }
    return mejorRuta;
}

    //----------------- METODOS RECURSIVOS ----------------------------------------------------
    
    // Calcula el peso total de los paquetes 
    public int pesoTotalRecursivo(NodoArbol arbol) {
        if (arbol.esHoja()) {
            return arbol.valor;
        }
        return pesoTotalRecursivo(arbol.izquierdo) + pesoTotalRecursivo(arbol.derecho);
    }

    // Calcula la altura del árbol (distancia máxima de la raíz a una hoja)
    public int alturaArbol(NodoArbol arbol) {
        if (arbol.esHoja()) {
            return 0;
        }
        int alturaIzquierda = alturaArbol(arbol.izquierdo);
        int alturaDerecha = alturaArbol(arbol.derecho);
        return 1 + Math.max(alturaIzquierda, alturaDerecha); // Suma 1 por el nivel actual
    }

    // Calcula el número total de calles (viaje de ida y vuelta a cada hoja)
    public int callesTotalesRecursivo(NodoArbol arbol) {
        if (arbol.esHoja()) {
            return 0;
        }
        return callesTotalesRecursivo(arbol.izquierdo) + callesTotalesRecursivo(arbol.derecho) + 4;
    }
        
    // Obtiene la ruta óptima de forma recursiva, siguiendo el camino más largo.
    public String obtenerRutaRecursiva(NodoArbol arbol) {
        // Si llegamos a un nodo nulo, no hay ruta.
        if (arbol == null) {
            return "";
        }
        // Si es una hoja retornamos su valor.
        if (arbol.esHoja()) {
            return String.valueOf(arbol.valor);
        }
        // determinar el camino más largo.
        int alturaIzquierda = alturaArbol(arbol.izquierdo);
        int alturaDerecha = alturaArbol(arbol.derecho);
        if (alturaIzquierda >= alturaDerecha) {
            return obtenerRutaRecursiva(arbol.izquierdo) + " <- " + arbol.valor;
        } else {
            return obtenerRutaRecursiva(arbol.derecho) + " <- " + arbol.valor;
        }
    }


//------------- CONSTRUCCIÓN DEL ÁRBOL DESDE UN STRING----------------------
    
    // Método para leer el árbol.
    public NodoArbol leerArbol(String linea) {
        // Usa un scanner para procesar la línea de entrada.
        Scanner scanner = new Scanner(linea);
        return leerArbolHelper(scanner);
    }

    // Metodo auxiliar 
    private NodoArbol leerArbolHelper(Scanner scanner) {
        if (!scanner.hasNext()) {
            return null;
        }
        String next = scanner.next();

        if (next.equals("(")) {
            // Es un nodo no hoja, tiene hijos
            NodoArbol izquierdo = leerArbolHelper(scanner);
            NodoArbol derecho = leerArbolHelper(scanner);
            // La siguiente lectura debe ser un ')'
            if (scanner.hasNext() && scanner.next().equals(")")) {
                return new NodoArbol(izquierdo, derecho);
            }
        } else {
            // Es un nodo hoja, tiene valor
            try {
                int valor = Integer.parseInt(next);
                return new NodoArbol(valor);
            } catch (NumberFormatException e) {
                // Si no es un número y no '(' es incorrecto
                return null;
            }
        }
        return null; // En caso incorrecto
    }
}

    


