import java.util.Comparator;
import java.util.PriorityQueue; 


public class Huffman {

    
    private int n; 
    
    private PriorityQueue<Integer> pq; 
    private int[] L; // Hijos izquierdos
    private int[] R; // Hijos derechos
    private int[] P; // Padres
    private int[] f; // Frecuencias


    public Huffman(int[] initialFrequencies) {
   
        this.n = initialFrequencies.length;
        int maxNodes = 2 * n; 

        // Inicializar los arrays
        this.L = new int[maxNodes];
        this.R = new int[maxNodes];
        this.P = new int[maxNodes];
        this.f = new int[maxNodes]; // Almacena las hojas y los nodos internos


        this.pq = new PriorityQueue<>(Comparator.comparingInt(i -> this.f[i]));

        for (int i = 0; i < n; i++) {
            this.f[i + 1] = initialFrequencies[i];
        }

        // Construir el arbol 
        BuildHuffmanTree();
    }

 
    void BuildHuffmanTree() {
        // Inicializar las hojas del arbol (simbolos 1 a n)
        for (int i = 1; i <= n; i++) {
            L[i] = 0; // hojas
            R[i] = 0;
            pq.add(i); // Añadir el índice i a la cola. Su prioridad la da f[i]
        }

        // Construir los nodos internos (de n+1 a 2*n-1)
        for (int i = n + 1; i <= 2 * n - 1; i++) {
            // Extraer los dos nodos con menor frecuencia
            int x = pq.poll(); 
            int y = pq.poll();

            // Crear un nuevo nodo interno i
            f[i] = f[x] + f[y]; // La frecuencia es la suma de sus hijos
            L[i] = x; P[x] = i; // x es el hijo izquierdo
            R[i] = y; P[y] = i; // y es el hijo derecho

            pq.add(i); 
        }
        P[2 * n - 1] = 0; // La raiz no tiene padre
    }

    public void getCode() {
        System.out.println("--- Codigos Huffman ---");
        for (int i = 1; i <= n; i++) {
            // Imprime el simbolo y su codigo binario
            System.out.println("Simbolo " + i + " (f=" + f[i] + "): " + getCodeHelper(i));
        }
    }

    public String getCodeHelper(int i) {
        if (i < 1 || i > n) {
            return "Simbolo invalido";
        }
        
        StringBuilder code = new StringBuilder();
        int current = i;
        
        // Mientras no lleguemos a la raiz
        while (P[current] != 0) {
            int parent = P[current];
            if (L[parent] == current) {
                // Si soy hijo izquierdo, añado un 0
                code.insert(0, '0');
            } else if (R[parent] == current) {
                // Si soy hijo derecho, añado un 1
                code.insert(0, '1');
            }
            current = parent; // Subir al padre
        }
        return code.toString();
    }

    public void decode(String code) {
        int root = 2 * n - 1; // El indice de la raiz
        int current = root;
        
        StringBuilder decodedOutput = new StringBuilder();

        for (char bit : code.toCharArray()) {
            if (bit == '0') {
                current = L[current]; // Bajar por la izquierda
            } else if (bit == '1') {
                current = R[current]; // Bajar por la derecha
            } else {
                continue; // Ignorar caracteres que no sean 0 o 1
            }

            // Si llegamos a un nodo hoja 
            if (L[current] == 0 && R[current] == 0) {
                decodedOutput.append("(Símbolo " + current + ") ");
                current = root; // Volver a la raiz para el siguiente simbolo
            }
        }
        
        if (current != root) {
            System.out.println("Error: El codigo esta incompleto o es invalido.");
        } else {
            System.out.println("Resultado: " + decodedOutput.toString());
        }
    }

    // --- Ejemplo de uso ---
    public static void main(String[] args) {
        // Prueba: Frecuencias para 5 símbolos (A, B, C, D, E)
        // Supongamos que:
        // Simbolo 1 (A) = 45
        // Simbolo 2 (B) = 13
        // Simbolo 3 (C) = 12
        // Simbolo 4 (D) = 16
        // Simbolo 5 (E) = 9
        // Simbolo 6 (F) = 5
        int[] frequencies = {45, 13, 12, 16, 9, 5};

        Huffman huffmanTree = new Huffman(frequencies);
        
        huffmanTree.getCode();
        
        // Ejemplo de codigo: "0" (A) + "101" (C) + "100" (B) = "0101100"
        huffmanTree.decode("01011001110"); // A, C, B, F, A
    }
}