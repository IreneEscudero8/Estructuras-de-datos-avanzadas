# Proyecto: Distancia de Levenshtein — Recursión y Programación Dinámica

**Equipo:**

* Luis Fernando Reyes Altamirano
* Ricardo André Gorostieta Jurado
* Irene Escudero Cazarez

**Tiempo de trabajo individual:** 2-3 horas cada uno
**Tiempo de trabajo total:** 6-8 horas

Este proyecto es parte de la práctica de **Recursividad, Memoización y Programación Dinámica** de la materia **Estructura de Datos**. El objetivo principal es desarrollar un programa que permita determinar la distancia de edicion entre el texto que se extraega de dos archivos. delta = 1 y alfa = 2. Delta es el costo de inserción o borrado. Alfa es el costo de cambiar caracteres.

El proyecto consiste en implementar tres versiones del programa:

* **Versión Recursiva sin Memoización:** 
* **Versión Recursiva sin Memoización:** 
* **Versión con Programación Dinámica:** 

---

## Objetivos

* Aplicar **recursión sin memoización**, **recursión con memoización** y **programación dinámica** en un mismo problema práctico.
* **Comparar el desempeño** de los tres enfoque para ver cual conviene más.
* Identificar ventajas y desventajas de cada técnica.
* Relacionar el análisis teórico con los resultados y analizarlos en base a gráficas.

---

## Descripción del Problema

El problema que resolvemos en esta práctica es ayudar a un profesor de bachillerato que sospecha que sus estudiantes están entregando tareas muy parecidas.

Nuestro objetivo es **cuantificar numéricamente qué tan similares son** dos documentos de texto. El principal reto es el **desempeño**, ya que cada ensayo puede tener hasta 50,000 caracteres (entre 5,000 y 10,000 palabras). Necesitamos un algoritmo que pueda comparar archivos de ese tamaño sin tardar demasiado.

Para hacerlo, implementamos un programa que calcula la **distancia de edición** (Levenshtein) entre dos textos. Esta distancia mide el "costo" mínimo para convertir un texto en otro usando tres operaciones básicas, con costos definidos:

* **Insertar** un carácter: $\beta$
* **Borrar** (eliminar) un carácter: $\beta$
* **Reemplazar** un carácter por otro: $\alpha$

Para esta práctica, los valores específicos que usamos fueron **$\alpha = 2$** y **$\beta = 1$**.

## Entrada

El programa recibe como entrada los **nombres de dos archivos de texto**, `A` y `B`, que son los documentos que se van a comparar.

* **Formato:** Los archivos deben ser de texto plano (`.txt`) codificados en **UTF-8**.
* **Longitud Máxima:** Cada archivo puede tener una longitud máxima de **50,000 caracteres**.

## Salida

Lo que debe imprimir el programa es una **sola línea de texto** con toda esta información:

* El número de caracteres del documento `A` y del `B`.
* La distancia de edición que calculó: `D(A, B)` y `D(B, A)`.
* El tiempo que se tardó en hacer el cálculo de las distancias.

**Especificación del tiempo:** Si el cálculo se tarda **más de 10 segundos**, el programa va a detenerse y mostrar el mensaje: `tiempo límite para resolver el problema excedido`.

## Estructura del Proyecto



## Cómo ejecutar



## Casos de Prueba



## Gráficas e Interpretaciones


## Conclusión Final


