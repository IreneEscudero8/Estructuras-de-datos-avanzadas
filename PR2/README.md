# Proyecto: Recursión para la Recolección de Paquetes

**Equipo:**  
- Luis Fernando Reyes Altamirano  
- Ricardo André Gorostieta Jurado  
- Irene Escudero Cazarez  

Tiempo de trabajo individual: 5–7 horas cada uno  
Tiempo de trabajo total: 15–21 horas

Este proyecto es parte de la práctica de **Recursión** de la materia **Estructura de Datos**. El objetivo principal es desarrollar un programa que permita al agente postal de **Correos de México** planificar las rutas para la recolección de paquetes en diferentes oficinas, basándose en un mapa con la distribución de las oficinas y el peso de los paquetes.

El proyecto consiste en implementar dos versiones del programa:

- **Versión Iterativa:** Utilizando una pila para almacenar y gestionar el recorrido.
- **Versión Recursiva:** Utilizando recursión para gestionar el recorrido de manera más directa.

## Objetivos

- **Aplicar recursión** en un problema práctico.
- **Comparar el desempeño** de algoritmos alternativos para la misma solución: una versión iterativa y una recursiva.

## Descripción del Problema

El centro de distribución de Correos de México tiene que organizar la recolección de paquetes en varias oficinas durante diferentes días de la semana. Cada oficina tiene un peso asignado de paquetes a recolectar. Los datos están representados en un mapa estructurado como un árbol donde:

- La raíz del árbol es el centro de distribución.
- Las hojas del árbol son las oficinas de correos.
- Los nodos intermedios representan las rutas a seguir entre oficinas.

El programa debe ser capaz de determinar las rutas óptimas y el peso total que el agente recogerá cada día.

## Entrada

Un archivo de texto que contiene el mapa de rutas de recolección para cada día de la semana. Cada línea del archivo representa un día de la semana, y cada línea tiene un árbol de rutas donde las hojas contienen los pesos de los paquetes a recolectar.

## Salida

Una línea por cada día de la semana que indique:
- Ruta seguida.
- Número de calles visitadas.
- Peso total recolectado.

## Estructura del Proyecto
```plaintext
PR3/
├── archivo.txt                 # Archivo de entrada con los mapas de rutas por día
├── Comparacion.jpeg            # Gráfica de comparación de desempeño (recursivo vs iterativo)
├── Recursivo.java              # Implementación del algoritmo recursivo
├── Iterativo.java              # Implementación del algoritmo iterativo
├── Main.java                   # Programa principal que ejecuta ambas versiones
├── TreeNode.java               # Clase que modela un nodo de un árbol (oficina o ruta)
├── README.md                   # Documentación del proyecto

---
```

## Como ejecutar

1. Compilar todos los archivos

2. Ejecutar el programa principal:
   En `TestComparacion`, cambiando el "arboles.txt" por el archivo deseado

---

## Gráficas e interpretaciones

En esta parte probamos las estructuras de datos, graficamos sus resultados con 

<img src="TablaPR2.jpeg" alt="Lista" width="400">  

<img src="TiempoPesoPR2.jpeg" alt="Lista" width="400">  


<img src="TiempoRutasPR2.jpeg" alt="Comparacion2" width="400">  


<img src="TiempoCallesPR2.jpeg" alt="Comparacion" width="400">
La gráfica muestra cómo varía el tiempo para calcular el número de calles recorridas por el agente con el número de hojas.
Recursivo sigue siendo más eficiente que iterativo para la mayor parte del cálculo. Sin embargo, a medida que el número de hojas se incrementa, la versión iterativa comienza a mostrar un mayor aumento en tiempo, posiblemente debido a su necesidad de manejar la pila explícitamente.


<img src="TiempoAlturaPR2.jpeg" alt="Comparacion" width="400">  

## <span style="color:blue;">Conclusión final</span>

Este proyecto demuestra la utilidad de la recursión en problemas donde las soluciones pueden representarse naturalmente como árboles, como es el caso de la recolección de paquetes en diferentes oficinas. Además, permite comparar dos enfoques alternativos (recursivo e iterativo), mostrando que, para este tipo de problema, la solución recursiva es la más adecuada en términos de claridad y eficiencia.  
La comparación entre ambos enfoques también destaca las diferencias en la complejidad y el tiempo de ejecución, lo que es crucial al diseñar soluciones eficientes para problemas reales.
