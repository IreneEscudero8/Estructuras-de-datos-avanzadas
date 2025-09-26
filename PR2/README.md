# Proyecto: Recursión para la Recolección de Paquetes

**Equipo:**  
- - Luis Fernando Reyes Altamirano  
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
##  Cómo ejecutar

 1. **Compilar todos los archivos**

 2. **Ejecutar el programa principal:**
java MainCompare, cambiando el "arboles.txt" por el archivo deseado

---

## Gráficas e interpretaciones

En las siguientes gráficas comparamos el desempeño de las dos estructuras de datos (La lista como manera de ordenamiento lineal y la tabla hash) Se realizaron usando la clase compare structure variando N, que es el total de pacientes, en grupos de 1,000 en 1,000. Tambien se forzo en esto a que hubiera 4 elemntos repetidos.

<img src="Lista.jpeg" alt="Lista" width="400"> 
La lista mostro un desempeño similar a la funcion exponencial en cuanto a tiempo, cada vez que se le agregan datos su tiempo de recorrido va aumentando ya que cada vez hay mas datos que recorrer, y debe de recorrerlos todos en cada iteracion. Pero por otro lado contextos con bajos volumenes de datos son muy eficientes ya recorren rapido todos los datos.


<img src="Hash.jpeg" alt="Lista" width="400"> 
La tabla hash tiene un muy buen desempeño con grandes volumenes de datos. Sin embargo, con pocos volumenes de datos sigue siendo eficiente aunque hay mejores alternativas, como se puede ver en la grafica siguiente.
<img src="Comparacion2.jpeg" alt="Comparacion2" width="400">  

Como es notorio, apartir de mas de 20 datos el hash se vuelve mas eficiente a la lista. A continuacion se contraponen el Hash y la Lista en grupos de 1,000 en 1,000.

<img src="Comparacion.jpeg" alt="Comparacion" width="400"> 


##  Conclusion final

Este proyecto demuestra la utilidad de la recursión en problemas donde las soluciones pueden representarse naturalmente como árboles, como es el caso de la recolección de paquetes en diferentes oficinas. Además, permite comparar dos enfoques alternativos (recursivo e iterativo), mostrando que, para este tipo de problema, la solución recursiva es la más adecuada en términos de claridad y eficiencia.
La comparación entre ambos enfoques también destaca las diferencias en la complejidad y el tiempo de ejecución, lo que es crucial al diseñar soluciones eficientes para problemas reales.