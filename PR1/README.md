# Proyecto Triage System
---
**Equipo:**  
- Luis Fernando Reyes Altamirano 
- Ricardo André Gorostieta Jurado 
- Irene Escudero Cazarez

  Tiempo de trabajo individual: 5-7 horas cada uno  
  Tiempo de trabajo total: 15-21 horas

Proyecto de la materia **Estructura de Datos Avanzadas**, que implementa un sistema de triage para pacientes, comparando el desempeño de la tabla hash vs un arreglo:  

-  **Tabla Hash** 
-  **Arreglo** 

El objetivo es simular la llegada en tiempo real de 1000 pacientes cada minuto aleatoriamente y comparar los tiempos de ejecución en tres operaciones clave:

1. **Insertar** pacientes (con prioridad e ID).  
2. **Buscar** pacientes (por ID en lista, por prioridad en BST).  
3. **Eliminar/atender** pacientes en orden de prioridad.  

---


## Estructura del proyecto
```plaintext

├── Arbol_binario/
│   ├── PatientBST.java      # Implementación del árbol binario de búsqueda (BST) para manejar pacientes
│   ├── TreeNode.java        # Clase que representa cada nodo del árbol (contiene a un paciente y referencias a hijos)
│   ├── Practica0.java       # Programa de prueba principal para validar el árbol binario
│
├── Lista_ligada_ordenada/
│   ├── DblyLnkSctr.java     # Implementación de la lista doblemente ligada ordenada (manejo de pacientes por prioridad)
│   ├── LnrDblNd.java        # Clase nodo de la lista doblemente ligada (contiene a un paciente y punteros prev/next)
│   ├── ADTsException.java   # Clase de excepción personalizada para manejar errores en operaciones de la lista
│   ├── TestTriage.java      # Programa de prueba principal para validar la lista ligada
│
├── Patient.java             # Clase que modela a un paciente con 10 valores en examenes 
├── TriageSystem.java        # Simulación principal: genera pacientes y compara rendimiento entre lista y BST
├── README.md                # Documentación del proyecto
```
---
##  Cómo ejecutar

 1. **Compilar todos los archivos**

 2. **Ejecutar el programa principal:**
java TriageSystem

---
## Gráficas e interpretaciones

En las siguientes gráficas comparamos el desempeño de las dos estructuras de datos escogidas para el proyecto (lista ligada ordenada y árbol binario). En las gráficas el eje de las X representa el número de pacientes y el eje de las Y representa el tiempo agregado.

<img src="lista_ligada.jpg" alt="lista_ligada" width="400">  
En primer lugar, observemos que en la gráfica se muestra el desempeño de las funciones insert, search y delete de la clase lista ligada ordenada. Podemos ver que la más tarda es la de insert, esto es porque cada vez hay más elementos y tiene que ingresarlos a corde su prioridad y recorre toda la lista hasta encontrar la prioridad correspondiente. 


<img src="arbol_binario.jpg" alt="arbol_binario" width="400">  
En la segunda gráfica podemos observar el desempeño de las mismas funciones pero en el árbol binario. El desempeño de las funciones fue bastante similar debido a cómo funciona el árbol binario.

<img src="insert.jpg" alt="insert" width="400">  
En desempeño en la función Insert podemos observar como al principio las dos estructuras van parejas en base al tiempo, pero después en la lista se va acumulando mucho el tiempo de insertar a comparación del árbol. El cual conviene más si la cantidad de datos es más grande, esto es por que la lista ligada tiene que recorrer toda la lista al insertar un nuevo elemento y aumenta el tiempo de recorrido entre mas larga sea la lista.

<img src="search.jpg" alt="search" width="400">  
En la función Search la lista ordenada tardó más que el árbol binario pero la diferencia no fue tan marcada, ya que en la lista doblemente ligada no existe acceso directo a los elementos aunque esté ordenada, se debe recorrer nodo por nodo hasta encontrar al paciente, lo que implica un costo lineal. En cambio, el árbol binario de búsqueda aprovecha su estructura jerárquica para descartar la mitad de los elementos en cada comparación.

<img src="delete.jpg" alt="delete" width="400">  
Y por último, en la función delete se comportó mejor la estructura de lista enlazada que la de árbol binario, porque la lista ligada ordenada siempre coloca al paciente de mayor prioridad al inicio; para atenderlo solo se quita el primer nodo, sin necesidad de recorrer ni reorganizar nada. En cambio, en un árbol binario primero hay que localizar el paciente más prioritario y después ajustar las conexiones entre nodos, lo que implica más pasos.

##  Conclusion final

En conclusion a lista ordenada permite eliminar al paciente con mayor prioridad con mayor facilidad, pero sufre al insertar nuevos pacientes, ya que debe recorrer la lista para mantener el orden, resultando en un mayor tiempo de ejecucion. Por otro lado, el BST equilibra mejor las operaciones: tanto la inserción como la búsqueda y eliminación se realizan rapidamente. La comparación experimental entre ambas estructuras evidencia que la lista es más eficiente en escenarios con pocas inserciones y muchas eliminaciones, mientras que el árbol resulta más escalable y robusto cuando el volumen de pacientes aumenta. Esto demuestra la importancia de seleccionar la estructura de datos adecuada según las características del problema real.  








