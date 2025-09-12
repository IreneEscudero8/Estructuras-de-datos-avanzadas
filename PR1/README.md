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
PR1/
├── archivo.txt                 # Archivo de entrada con datos de pacientes
│
├── Comparacion.jpeg            # Gráfica de comparación de desempeño (lineal vs hash)
├── Comparacion2.jpeg           # Segunda gráfica de comparación
├── Hash.jpeg                   # Gráfica enfocada en tabla hash
├── Lista.jpeg                  # Gráfica enfocada en lista lineal
│
├── CompareStructures.java      # Clase que compara desempeño entre estructuras (lista vs hash)
├── HashTest.java               # Pruebas unitarias / de validación para la implementación con hash
├── LeerPacientes.java          # Lector de archivo: transforma cada línea en un paciente con 10 enteros
├── ListStructure.java          # Implementación de lista lineal/secuencial para almacenar pacientes
├── MainCompare.java            # Programa principal: ejecuta ambas versiones y mide tiempos
├── Patient.java                # Clase que modela un paciente (arreglo de 10 enteros, equals y hashCode)
├── PatientHashTable.java       # Implementación de tabla de hash para pacientes
├── TestList.java               # Pruebas unitarias / de validación para la lista
│
├── README.md                   # Documentación del proyecto

```
---
##  Cómo ejecutar

 1. **Compilar todos los archivos**

 2. **Ejecutar el programa principal:**
java MainCompare, cambiando el "archivo.txt" por el archivo deseado

---

## Gráficas e interpretaciones

En las siguientes gráficas comparamos el desempeño de las dos estructuras de datos (La lista como manera de ordenamiento lineal y la tabla hash) Se realizaron usando la clase compare structure variando N, que es el total de pacientes, en grupos de 1,000 en 1,000. Tambien se forzo en esto a que hubiera 4 elemntos repetidos.

<img src="Lista.jpeg" alt="Lista" width="400"> 
La lista mostro un desempeño similar a la funcion exponencial en cuanto a tiempo, cada vez que se le agregan datos su tiempo de recorrido va aumentando ya que cada vez hay mas datos que recorder. Pero por otro lado contextos con bajos volumenes de datos son muy eficientes ya recorren rapido todos los datos.


<img src="Hash.jpeg" alt="Lista" width="400"> 
La tabla hash tiene un muy buen desempeño con grandes volumenes de datos, ya que su aumento es constante. Con pocos volumenes de datos sigue siendo eficiente, aunque hay mejores alternativas, como se puede ver en la grafica siguiente.
<img src="Comparacion2.jpeg" alt="Comparacion2" width="400">  

Como es notorio, apartir de mas de 20 datos el hash se vuelve mas eficiente a la lista. A continuacion se contraponen el Hash y la Lista en grupos de 1,000 en 1,000.

<img src="Comparacion.jpeg" alt="Comparacion" width="400"> 


##  Conclusion final

La comparación directa revela el punto de inflexión: la tabla de hash supera en eficiencia a la lista lineal con tan solo unas pocas decenas de registros. Para conjuntos de datos grandes, la diferencia no es marginal, sino de varios órdenes de magnitud, haciendo de la tabla de hash la única opción viable para una aplicación real. En definitiva, este proyecto demuestra empíricamente que para problemas de búsqueda, inserción y detección de duplicados en grandes volúmenes de datos, la tabla de hash es una solución categóricamente superior a las búsquedas lineales. Su capacidad para distribuir y acceder a los datos de manera casi instantánea la convierte en una herramienta fundamental en el desarrollo de software eficiente y escalable.












