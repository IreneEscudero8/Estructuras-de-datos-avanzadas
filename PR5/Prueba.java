import java.util.*;
import java.io.*;
/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase de prueba para el árbol de Huffman que lee un archivo de entrada con símbolos,
 * sus frecuencias y un texto a codificar/decodificar.
 */

public class Prueba {
        
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("archivo.txt"));

        // Leer entradas
        String[] symbolsStr = br.readLine().trim().split(" ");
        String[] freqStr = br.readLine().trim().split(" ");
        String text = br.readLine().trim();

        int n = symbolsStr.length;
        char[] symbols = new char[n];
        int[] freq = new int[n];

        for (int i = 0; i < n; i++) {
            if (symbolsStr[i].equals("_")) {
                symbols[i] = ' ';   // espacios
            } else {
                symbols[i] = symbolsStr[i].charAt(0);
            }
            freq[i] = Integer.parseInt(freqStr[i]);
        }

        // Construccion del arbol
        long t1 = System.nanoTime();
        Huffman2 h = new Huffman2(symbols, freq);
        long t2 = System.nanoTime();

        // Codificacion 
        long t3 = System.nanoTime();
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(h.getCode(c));
        }
        long t4 = System.nanoTime();

        // Decodificacion
        StringBuilder decoded = new StringBuilder();
        decoded.append(h.decode(encoded.toString()));

        // Resultados
        System.out.println("Alfabeto:");
        for (char c : symbols) System.out.print(c + " ");
        System.out.println("\nCodigos Huffman:");
        h.printCodes();

        System.out.println("Tamanho original: " + text.length());
        System.out.println("Bits codificados: " + encoded.length());
        System.out.println("Bits UTF-8: " + (text.length() * 8));
        System.out.println("Tiempo arbol (ns): " + (t2 - t1));
        System.out.println("Tiempo codificacion (ns): " + (t4 - t3));

        System.out.println("Texto codificado:");
        System.out.println(encoded.toString());

        System.out.println("Texto decodificado:");
        System.out.println(decoded.toString());
    }
}
