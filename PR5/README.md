# Proyecto: Compresión de Texto con Algoritmos Codiciosos (Códigos de Huffman)

**Equipo:**

* Luis Fernando Reyes Altamirano [Revisar/Confirmar nombres]
* Ricardo André Gorostieta Jurado [Revisar/Confirmar nombres]
* Irene Escudero Cazarez [Revisar/Confirmar nombres]

**Tiempo de trabajo individual:** 2-3 horas
**Tiempo de trabajo total:** 6-9 horas

El **Práctica 5: Algoritmos Codiciosos** de la materia **Estructura de Datos**. El objetivo central es aplicar algoritmos codiciosos en un problema práctico : el desarrollo de un algoritmo de compresión de textos alfanuméricos (de hasta 5000 caracteres). Para ello, se implementó el algoritmo de Huffman, buscando generar un código que sea altamente eficiente para el almacenamiento y la recuperación de datos.

---

## 🎯 Objetivos

* Aplicar algoritmos codiciosos en un problema práctico.
* Comparar el desempeño de algoritmos alternativos para el mismo problema.

---

## 📖 Descripción del Problema

Se te encargó desarrollar un algoritmo para **comprimir textos alfanuméricos de hasta 5000 caracteres**. El objetivo es guardarlos en un campo de una base de datos y poder recuperarlos de forma simple y eficiente.

Para cumplir con este propósito, se decidió implementar el **algoritmo de Huffman**, el cual genera un código conocido por su alta eficiencia en la compresión.

El proyecto requiere la implementación de tres algoritmos fundamentales:

* Algoritmo codicioso para producir el código de Huffman.
* Algoritmo para codificar el texto de entrada.
* Algoritmo para decodificar un texto codificado.

## 📥 Entrada del Programa

El programa recibe la información desde un **archivo de texto de entrada** que debe contener exactamente tres líneas, siguiendo este formato:

* **El Alfabeto.** Contiene una lista de los **símbolos alfanuméricos** que conforman el alfabeto, separados por espacios en blanco.
* **Frecuencias.** Una lista de **números** (enteros) que indican la frecuencia de aparición de cada símbolo, siguiendo el mismo orden de la línea anterior.
* **Texto a Codificar.** El texto completo (alfanumérico, hasta 5000 caracteres) que será sometido al proceso de compresión.

---

## 📤 Salida del Programa

Lo que debe imprimir el programa es una secuencia de texto que indique la siguiente información, línea por línea:

* **El alfabeto** utilizado.
* **El código de Huffman** obtenido para cada símbolo del alfabeto.
* **Estadísticas de Compresión:** Una línea con tres datos clave: el número de símbolos en el texto original, el número de bits del texto codificado (Huffman), y el número de bits que se requerirían con UTF-8.
* **Tiempos de Ejecución:** El tiempo utilizado para el cálculo del código de Huffman y el tiempo utilizado para la codificación.
* **El texto codificado** (la secuencia de 1s y 0s).
* **El texto decodificado** (para verificar que coincide con el original).

## 📂 Estructura del Proyecto

├── Huffman.java      # Clase  que implementa el **algoritmo de Huffman** (nos sirvio para la clase 2).
├── Huffman           # Pseudocodigo del algoritmo de Huffman
├── Huffman2.java     # Clase principal, con `main` para la **ejecución, lectura del archivo de entrada** y la impresión de la salida solicitada (tiempos y estadísticas).
├── archivo.txt       # Archivo txt de entrada, contiene alfabeto, frecuencias y texto acodificar.
└── README.md         # Documentación del proyecto (este archivo).

## Cómo ejecutar

1. Compilar todos los archivos.
2. Ejecutar `Huffman2`, sustituya el nombre de archivo.txt por el nombre del archivo de texto que contiene los datos de entrada (alfabeto, frecuencias y texto a codificar).

## Casos de Prueba

Para poner a prueba los algoritmos y ver cómo se comportaba su rendimiento y capacidad de compresión, corrimos el programa con documentos de diferentes tamaños.

Usamos textos con las siguientes longitudes de caracteres para nuestras pruebas, respetando el límite de la práctica:

* **5** caracteres
* **50** caracteres
* **500** caracteres
* **2,500** caracteres
* **5,000** caracteres

El objetivo era medir el tiempo de ejecución y la cantidad de bits generados en cada caso para después poder graficar y comparar el desempeño de la compresión de Huffman contra el formato UTF-8.




## Conclusión Final

El objetivo de esta práctica era implementar un algoritmo codicioso para lograr dos tareas fundamentales: comprimir un texto (codificar) y recuperarlo intacto (decodificar). Al analizar los resultados, pudimos ver cómo el enfoque codicioso conecta ambos procesos de forma eficiente.

* **Proceso de Codificación:** Aquí es donde actúa la estrategia codiciosa. El algoritmo analizó las frecuencias y construyó el árbol "de abajo hacia arriba", asignando las rutas más cortas (menos bits) a los caracteres que más se repiten. Los resultados mostraron que esta asignación inteligente es lo que permite reducir el tamaño del archivo final sin perder información.

* **Proceso de Decodificación:** Esta parte fue crucial para validar la estructura de datos. Comprobamos que el árbol de Huffman genera "códigos prefijo" (ningún código es el inicio de otro), lo que elimina cualquier ambigüedad. Al recorrer el árbol bit por bit desde la raíz hasta las hojas, el programa pudo reconstruir el texto original con exactitud, demostrando que la compresión es totalmente reversible.

En conclusión, la implementación demostró que el algoritmo codicioso es una solución robusta: logra una codificación eficiente en espacio y permite una decodificación rápida y sin errores, cumpliendo con el ciclo completo de almacenamiento y recuperación de datos.