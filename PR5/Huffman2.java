import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase que define los metiodos para construir un árbol de Huffman,
 * codificar y decodificar textos usando los códigos generados.
 */

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
            throw new IllegalArgumentException("Los arrays de letras y frecuencias deben tener el mismo tamanho.");
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
        System.out.println("--- Codigos Huffman ---");
        for (int i = 1; i <= n; i++) {
            System.out.println("Simbolo '" + charMap[i] + "' (f=" + f[i] + "): " + getCodeByIndex(i));
        }
    }

    public String getCode(char symbol) {
        for (int i = 1; i <= n; i++) {
            if (charMap[i] == symbol) {
                return getCodeByIndex(i); 
            }
        }
        return " ";
    }

    private String getCodeByIndex(int i) {
        if (i < 1 || i > n) {
            return "Indice invalido";
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

 
// Reemplaza el método decode actual por este
public String decode(String code) {
    StringBuilder decoded = new StringBuilder();
    int root = 2 * n - 1;   // raíz según tu esquema de índices
    int current = root;

    for (int i = 0; i < code.length(); i++) {
        char bit = code.charAt(i);

        if (bit == '0') {
            current = L[current];
        } else if (bit == '1') {
            current = R[current];
        } else {
            // bit inválido: inserta espacio y reinicia
            decoded.append(' ');
            current = root;
            continue;
        }

        // si nos salimos del árbol o el hijo es 0 -> inserta espacio y reinicia
        if (current <= 0 || current >= L.length) {
            decoded.append(' ');
            current = root;
            continue;
        }

        // si es hoja (no tiene hijos) -> tomar símbolo
        if (L[current] == 0 && R[current] == 0) {
            // Si la hoja no tiene mapeo válido, poner espacio
            char sym = (current >= 1 && current <= n) ? charMap[current] : '\0';
            if (sym == '\0') {
                decoded.append(' ');
            } else {
                decoded.append(sym);
            }
            current = root; // volver a raíz para el siguiente símbolo
        }
    }

    // Si al final no volvimos a la raíz, puede ser código incompleto: agregar espacio
    if (current != root) {
        decoded.append(' ');
    }

    String result = decoded.toString();
    System.out.println("Resultado: " + result);
    return result;
}

    public static void main(String[] args) {
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F'};
        int[] frequencies = {45, 13, 12, 16, 9, 5};

        // 1. Crear el árbol (usando Huffman2)
        Huffman2 huffmanTree = new Huffman2(letters, frequencies);
        
        // 2. Imprimir todos los códigos
        huffmanTree.printCodes();

        // 3. Probar la codificación de un solo carácter
        System.out.println("Codigo para 'B': " + huffmanTree.getCode('B'));
        
        // 4. Probar la decodificación
        // Prueba con el código anterior que dio "ABC DA"
        huffmanTree.decode("01011001110");
    }
}