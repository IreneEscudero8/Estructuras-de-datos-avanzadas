# 🏥 Proyecto Triage System
---
**Equipo:**  
- Luis Fernando Reyes  
- Andre Gorostieta  
- Irene Escudero  

Proyecto de la materia **Estructura de Datos Avanzadas**, que implementa un sistema de triage para pacientes, comparando el desempeño de dos estructuras de datos:  

-  **Lista doblemente ligada ordenada** 
-  **Árbol binario de búsqueda (BST)** 

El objetivo es simular la llegada en tiempo real de 1000 pacientes y comparar los tiempos de ejecución en tres operaciones clave:

1. **Insertar** pacientes (con prioridad e ID).  
2. **Buscar** pacientes (por ID en lista, por prioridad en BST).  
3. **Eliminar/atender** pacientes en orden de prioridad.  

---

## Estructura del proyecto

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
├── Patient.java             # Clase que modela a un paciente (nombre, ID, prioridad, etc.)
├── TriageSystem.java        # Simulación principal: genera pacientes y compara rendimiento entre lista y BST
├── README.md                # Documentación del proyecto

---
## ⚙️ Cómo ejecutar

# 1. Compilar todos los archivos
javac *.java Arbol_binario/*.java Lista_ligada_ordenada/*.java

# 2. Ejecutar el programa principal
java TriageSystem


---

##  Conclusión

En conclusion a lista ordenada permite eliminar al paciente con mayor prioridad con mayor facilidad, pero sufre al insertar nuevos pacientes, ya que debe recorrer la lista para mantener el orden, resultando en un mayor tiempo de ejecucion. Por otro lado, el BST equilibra mejor las operaciones: tanto la inserción como la búsqueda y eliminación se realizan rapidamente. La comparación experimental entre ambas estructuras evidencia que la lista es más eficiente en escenarios con pocas inserciones y muchas eliminaciones, mientras que el árbol resulta más escalable y robusto cuando el volumen de pacientes aumenta. Esto demuestra la importancia de seleccionar la estructura de datos adecuada según las características del problema real.  


