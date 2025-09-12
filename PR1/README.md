# Proyecto Detección de Registros Idénticos (Tablas de Hash)
---
**Equipo:**  
- Luis Fernando Reyes Altamirano  
- Ricardo André Gorostieta Jurado  
- Irene Escudero Cazarez  

Tiempo de trabajo individual: 5–7 horas cada uno  
Tiempo de trabajo total: 15–21 horas

Proyecto de la materia **Estructura de Datos Avanzadas**, que detecta si existen pacientes con **registros de laboratorio idénticos** (tuplas de 10 enteros) y **compara el desempeño** de dos enfoques de almacenamiento/búsqueda:

- **Arreglo lineal (búsqueda secuencial)**
- **Tabla de hash** (encadenamiento o direccionamiento abierto)


El objetivo es leer un archivo con **n** pacientes (1 ≤ n ≤ 100,000), donde cada línea posterior a la primera contiene **exactamente 10 enteros** (0 a 10,000,000) separados por espacio o tabulador, y producir una salida que indique si **no hay** pacientes con registros idénticos o si **se encontraron m pacientes idénticos**.

---

## Estructura del proyecto
```plaintext
├── src/
│   ├── LeerPacientes.java      # Lector robusto del archivo (lee n y luego n líneas con 10 enteros)
│   ├── Patient.java            # Tupla inmutable de 10 enteros (equals/hashCode)
│   ├── LinearSolution.java     # Detección con arreglo lineal (O(n²) peor/promedio)
│   ├── HashSolution.java       # Detección con tabla de hash (O(n) promedio)
│   ├── MainCheck.java          # Programa principal que imprime la salida requerida
│   └── Benchmark.java          # Comparador de tiempos y generación de CSV
├── data/
│   └── ejemplo.txt             # Archivo de ejemplo con formato válido
├── README.md                   # Este documento
└── Makefile                    # (Opcional) Atajos para compilar/ejecutar
```
---
##  Cómo ejecutar

 1. **Compilar todos los archivos**

 2. **Ejecutar el programa principal:**
java TriageSystem

---

## Gráficas e interpretaciones

En las siguientes gráficas comparamos el desempeño de las dos estructuras de datos escogidas para el proyecto (lista ligada ordenada y árbol binario). En las gráficas el eje de las X representa el número de pacientes y el eje de las Y representa el tiempo agregado.

<img src="Lista.jpeg" alt="lista_ligada" width="400">  
En primer lugar, observemos que en la gráfica se muestra el desempeño de las funciones insert, search y delete de la clase lista ligada ordenada. Podemos ver que la más tarda es la de insert, esto es porque cada vez hay más elementos y tiene que ingresarlos a corde su prioridad y recorre toda la lista hasta encontrar la prioridad correspondiente. 


<img src="Hash.jpeg" alt="arbol_binario" width="400">  
En la segunda gráfica podemos observar el desempeño de las mismas funciones pero en el árbol binario. El desempeño de las funciones fue bastante similar debido a cómo funciona el árbol binario.

<img src="Comparacion.jpeg" alt="insert" width="400">  
En desempeño en la función Insert podemos observar como al principio las dos estructuras van parejas en base al tiempo, pero después en la lista se va acumulando mucho el tiempo de insertar a comparación del árbol. El cual conviene más si la cantidad de datos es más grande, esto es por que la lista ligada tiene que recorrer toda la lista al insertar un nuevo elemento y aumenta el tiempo de recorrido entre mas larga sea la lista.

<img src="search.jpg" alt="search" width="400">  
En la función Search la lista ordenada tardó más que el árbol binario pero la diferencia no fue tan marcada, ya que en la lista doblemente ligada no existe acceso directo a los elementos aunque esté ordenada, se debe recorrer nodo por nodo hasta encontrar al paciente, lo que implica un costo lineal. En cambio, el árbol binario de búsqueda aprovecha su estructura jerárquica para descartar la mitad de los elementos en cada comparación.

<img src="delete.jpg" alt="delete" width="400">  
Y por último, en la función delete se comportó mejor la estructura de lista enlazada que la de árbol binario, porque la lista ligada ordenada siempre coloca al paciente de mayor prioridad al inicio; para atenderlo solo se quita el primer nodo, sin necesidad de recorrer ni reorganizar nada. En cambio, en un árbol binario primero hay que localizar el paciente más prioritario y después ajustar las conexiones entre nodos, lo que implica más pasos.

##  Conclusion final

En conclusion a lista ordenada permite eliminar al paciente con mayor prioridad con mayor facilidad, pero sufre al insertar nuevos pacientes, ya que debe recorrer la lista para mantener el orden, resultando en un mayor tiempo de ejecucion. Por otro lado, el BST equilibra mejor las operaciones: tanto la inserción como la búsqueda y eliminación se realizan rapidamente. La comparación experimental entre ambas estructuras evidencia que la lista es más eficiente en escenarios con pocas inserciones y muchas eliminaciones, mientras que el árbol resulta más escalable y robusto cuando el volumen de pacientes aumenta. Esto demuestra la importancia de seleccionar la estructura de datos adecuada según las características del problema real.  








