
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
