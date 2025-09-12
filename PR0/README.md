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
