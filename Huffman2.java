import java.util.Comparator;
import java.util.PriorityQueue;


public class Huffman2 {

    private int n; // Número de símbolos
    private PriorityQueue<Integer> pq;
    private int[] L; // Hijos izquierdos
    private int[] R; // Hijos derechos
    private int[] P; // Padres
    private int[] f; // Frecuencias
    private char[] charMap; // Mapea los índices de las hojas (1 a n) a los caracteres reales

    public Huffman2(char[] letters, int[] frequencies) {
        if (letters.length != frequencies.length) {
            throw new IllegalArgumentException("Los arrays de letras y frecuencias deben tener el mismo tamaño.");
        }
        
        this.n = frequencies.length;
        int maxNodes = 2 * n; 

        this.L = new int[maxNodes];
        this.R = new int[maxNodes];
        this.P = new int[maxNodes];
        this.f = new int[maxNodes];
        this.charMap = new char[n + 1]; 

        this.pq = new PriorityQueue<>(Comparator.comparingInt(i -> this.f[i]));

        for (int i = 0; i < n; i++) {
            this.f[i + 1] = frequencies[i];
            this.charMap[i + 1] = letters[i]; 
        }

        BuildHuffmanTree();
    }


    void BuildHuffmanTree() {
        for (int i = 1; i <= n; i++) {
            L[i] = 0;
            R[i] = 0;
            pq.add(i);
        }

        for (int i = n + 1; i <= 2 * n - 1; i++) {
            int x = pq.poll();
            int y = pq.poll();

            f[i] = f[x] + f[y];
            L[i] = x; P[x] = i;
            R[i] = y; P[y] = i;

            pq.add(i); 
        }
        P[2 * n - 1] = 0;
    }

    public void printCodes() {
        System.out.println("--- Códigos Huffman ---");
        for (int i = 1; i <= n; i++) {
            System.out.println("Símbolo '" + charMap[i] + "' (f=" + f[i] + "): " + getCodeByIndex(i));
        }
    }

    public String getCode(char symbol) {
        for (int i = 1; i <= n; i++) {
            if (charMap[i] == symbol) {
                return getCodeByIndex(i); 
            }
        }
        return "Símbolo no encontrado";
    }

    private String getCodeByIndex(int i) {
        if (i < 1 || i > n) {
            return "Índice inválido";
        }
        
        StringBuilder code = new StringBuilder();
        int current = i;
        
        while (P[current] != 0) {
            int parent = P[current];
            if (L[parent] == current) {
                code.insert(0, '0');
            } else if (R[parent] == current) {
                code.insert(0, '1');
            }
            current = parent;
        }
        return code.toString();
    }

 
    public void decode(String code) {
        System.out.println("\n--- Decodificando: " + code + " ---");
        int root = 2 * n - 1;
        int current = root;
        
        StringBuilder decodedOutput = new StringBuilder();

        for (char bit : code.toCharArray()) {
            if (bit == '0') {
                current = L[current];
            } else if (bit == '1') {
                current = R[current];
            } else {
                continue;
            }

            if (L[current] == 0 && R[current] == 0) {
                decodedOutput.append(charMap[current]);
                current = root; 
            }
        }
        
        if (current != root) {
            System.out.println("Error: El código está incompleto o es inválido.");
        } else {
            System.out.println("Resultado: " + decodedOutput.toString());
        }
    }


    public static void main(String[] args) {
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F'};
        int[] frequencies = {45, 13, 12, 16, 9, 5};

        // 1. Crear el árbol (usando Huffman2)
        Huffman2 huffmanTree = new Huffman2(letters, frequencies);
        
        // 2. Imprimir todos los códigos
        huffmanTree.printCodes();

        // 3. Probar la codificación de un solo carácter
        System.out.println("Código para 'B': " + huffmanTree.getCode('B'));
        
        // 4. Probar la decodificación
        // Prueba con el código anterior que dio "ABC DA"
        huffmanTree.decode("01011001110");
    }
}