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

    // Calcula el peso total de los paquetes 
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

    // Obtiene la ruta óptima iterativa 
    public String obtenerRutaIterativa(NodoArbol raiz) {
        if (raiz == null) return "";

        java.util.Stack<NodoArbol> pila = new java.util.Stack<>();
        pila.push(raiz);

        StringBuilder resultado = new StringBuilder();

        while (!pila.isEmpty()) {
            NodoArbol nodo = pila.pop();

            if (nodo.esHoja()) {
                // agregamos el valor de la hoja
                if (resultado.length() > 0) {
                    resultado.append(" -> ");
                }
                resultado.append(nodo.valor);
            } else {
                int hIzq = alturaArbol(nodo.izquierdo);
                int hDer = alturaArbol(nodo.derecho);

                if (hIzq <= hDer) {
                    if (nodo.derecho != null) pila.push(nodo.derecho);
                    if (nodo.izquierdo != null) pila.push(nodo.izquierdo);
                } else {
                    if (nodo.izquierdo != null) pila.push(nodo.izquierdo);
                    if (nodo.derecho != null) pila.push(nodo.derecho);
                }
            }
        }

        return resultado.toString();
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

   // Obtiene la ruta óptima recursiva
    public String obtenerRutaRecursiva(NodoArbol arbol) {
        if (arbol == null) return "";
        // caso hoja
        if (arbol.esHoja()) {
            return String.valueOf(arbol.valor);
        }
        int hIzq = alturaArbol(arbol.izquierdo);
        int hDer = alturaArbol(arbol.derecho);

        // primero visitamos el subarbol menos profundo
        String rutaPrimero, rutaDespues;
        if (hIzq <= hDer) {
            rutaPrimero = obtenerRutaRecursiva(arbol.izquierdo);
            rutaDespues = obtenerRutaRecursiva(arbol.derecho);
        } else {
            rutaPrimero = obtenerRutaRecursiva(arbol.derecho);
            rutaDespues = obtenerRutaRecursiva(arbol.izquierdo);
        }
        // concatenamos rutas
        if (rutaPrimero.isEmpty()) return rutaDespues;
        if (rutaDespues.isEmpty()) return rutaPrimero;
        return rutaPrimero + " -> " + rutaDespues;
    }


//------------- CONSTRUCCION DEL ARBOL DESDE UN STRING----------------------
    
    // Método para leer el árbol.
    public NodoArbol leerArbol(String linea) {
        // Reemplazar '(' con '( ' para que el Scanner los lea separados
        linea = linea.replace("(", "( ").replace(")", " )");
        Scanner scanner = new Scanner(linea);
        return leerArbolHelper(scanner);
    }
    
    // Método auxiliar que construye el árbol de forma recursiva.
    private NodoArbol leerArbolHelper(Scanner scanner) {
        if (!scanner.hasNext()) {
            return null;
        }

        String next = scanner.next();

        if (next.equals("(")) {
            // Es un nodo intermedio 
            NodoArbol izquierdo = leerArbolHelper(scanner); 
            NodoArbol derecho = leerArbolHelper(scanner);

            // Siguiente lectura debe ser un ')'
            if (scanner.hasNext() && scanner.next().equals(")")) {
                return new NodoArbol(izquierdo, derecho);
            }
        } else {
            // Es un nodo hoja 
            try {
                int valor = Integer.parseInt(next);
                return new NodoArbol(valor);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null; 
    }
}
