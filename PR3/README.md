# Proyecto: Homero y las Hamburguesas — Programación Dinámica

**Equipo:**

* Luis Fernando Reyes Altamirano
* Ricardo André Gorostieta Jurado
* Irene Escudero Cazarez

**Tiempo de trabajo individual:** 2–3 horas cada uno
**Tiempo de trabajo total:** 6-9 horas

Este proyecto es parte de la práctica de **Programación Dinámica** de la materia **Estructura de Datos**. El objetivo principal es comparar tres enfoques para resolver un problema clásico de optimización con tiempo limitado: **recursividad**, **recursividad con memoización** y **programación dinámica**, midiendo su desempeño y analizando sus trade‑offs.

---

## Objetivos

* **Aplicar recursión, memoización y programación dinamica** a un problema de maximización con restricción de tiempo.
* **Comparar el desempeño** Vamos a ver el desempeño de las estructuras en base al tiempo y ver la estructura más eficiente (muy probablemente programación dinamica).

---

## Descripción del problema

Homero puede comer hamburguesas de **dos tipos**: una tarda **m** minutos y la otra **n** minutos. Dado un tiempo disponible **t**, se busca **maximizar el número total de hamburguesas** sin exceder **t**. Si no es posible usar todo el tiempo, se permite un **sobrante** (las hamburguesas se tienen que comer completas):

1. maximizar hamburguesas, viendo cual conviene agarrar.


---

## Entradas y salidas

**Entrada:** enteros positivos `m, n, t`.
**Salida:** una línea con:

* `h` = máximo de hamburguesas

**Ejemplos**

|  m |  n |  t | Mejor (x,y) |  h |
| -: | -: | -: | :---------: | -: |
|  3 |  5 | 54 |    (18,0)   | 18 |
|  3 |  5 | 56 |    (16,4)   | 20 |
|  6 |  8 | 17 |    (1,1)    |  2 |

---

## Enfoques implementados

### 1) Recursivo (backtracking)

Explora tomar `m` o `n` minutos hasta agotar tiempo. Es **didáctico** pero **exponencial** en el peor caso; útil sólo para tamaños pequeños.

### 2) Memoización

Guarda `best(tiempo)` en una caché. Complejidad **O(t)** en tiempo y memoria; misma lógica que el recursivo, pero evitando recomputaciones. Soporta reconstrucción.

### 3) Programación dinamica

Construye `dpExact[x]` con el máximo de hamburguesas exactas para cada `x ≤ t`. Al final elige el `x` con mejor `dpExact[x]` y **sobrante** `t−x` mínimo. Complejidad **O(t)** tiempo y **O(t)** memoria. Ideal para benchmarking.

**Criterio de desempate**: primero `h` máximo; a igualdad, `s` mínimo; opcionalmente, preferimos más hamburguesas del tipo de **menor tiempo**.

---

## Correctitud (bosquejo)

Por **inducción en el tiempo**: `dpExact[0]=0` es óptimo. Si `dpExact[x]` es óptimo, actualizar `x+m` y/o `x+n` con `dpExact[x]+1` preserva optimalidad local; seleccionar al final el `x` con mejor `h` y menor `s` cumple el objetivo global. La versión con memoización es equivalente evaluada on‑demand.

---

## Complejidad teórica

| Método         |      Tiempo |    Memoria | Comentarios          |
| -------------- | ----------: | ---------: | -------------------- |
| Recursivo puro | Exponencial | O(t) stack | Sólo didáctico       |
| Memoización    |        O(t) |       O(t) | Sencillo, flexible   |
| DP bottom‑up   |        O(t) |       O(t) | Estable para pruebas |

> Nota: Si `t` es muy grande, considerar optimizaciones por **gcd(m,n)**, o técnicas por residuos módulo `min(m,n)`.

---

## Estructura del proyecto

```
/ (raíz)
├─ README.md
├─ data/
│  └─ casos.txt                # Instancias de prueba (m n t por línea)
├─ src/
│  ├─ homero_recursive.*       # Recursivo puro (C++/Java/Python)
│  ├─ homero_memo.*            # Top‑down con memo
│  ├─ homero_bottomup.*        # DP tabular 1D con reconstrucción
│  └─ utils.*                  # Helpers (I/O, reconstrucción, métricas)
└─ benchmarks/
   ├─ resultados.csv           # Tiempos/ memoria por método y caso
   └─ graficas/*.png           # Gráficas generadas
```

---

## Cómo ejecutar

### Opción A — C++

```bash
g++ -O2 -std=c++17 src/homero_bottomup.cpp -o homero
./homero < data/casos.txt
```

### Opción B — Python

```bash
python3 src/homero.py < data/casos.txt
```

**Formato `data/casos.txt`:**

```
m n t
m n t
...
```

---

## Conjunto de pruebas y parámetros

Para comparar, usamos tamaños crecientes de `t` y distintos pares `(m,n)`:

* Pequeños: `(3,5)` con `t ∈ {100, 500, 1e3}`
* Medianos: `(37,53)` con `t ∈ {1e4, 5e4, 1e5}`
* Grandes: `(401,997)` con `t ∈ {2e5, 5e5, 1e6}`

Cada instancia se corre **N=20** veces y se reporta **promedio** y **desviación estándar**.

---

## Resultados (tablas y gráficas)

> Coloca aquí tus resultados. Se incluyen nombres de archivo sugeridos para reutilizar tu flujo de trabajo de la práctica pasada.

**Tabla de resultados**
`benchmarks/resultados.csv` (resumen exportado a imagen para el README):

<img src="benchmarks/TablaPD.jpeg" alt="Tabla de resultados" width="520"/>

**Gráficas de tiempo vs tamaño**

* `benchmarks/graficas/TiempoPD_Total.jpeg` — Tiempo (ms) por método vs `t`.
* `benchmarks/graficas/TiempoPD_Pequeños.jpeg` — Zoom casos pequeños.
* `benchmarks/graficas/TiempoPD_Medianos.jpeg` — Zoom casos medianos.
* `benchmarks/graficas/TiempoPD_Grandes.jpeg` — Zoom casos grandes.

<img src="benchmarks/graficas/TiempoPD_Total.jpeg" alt="Tiempo total" width="520"/>

**Gráfica estabilidad (desviación estándar)**
`benchmarks/graficas/EstabilidadPD.jpeg` — Variabilidad temporal por método.

<img src="benchmarks/graficas/EstabilidadPD.jpeg" alt="Estabilidad" width="520"/>

**Gráfica memoria**
`benchmarks/graficas/MemoriaPD.jpeg` — Memoria (MB) estimada por método.

<img src="benchmarks/graficas/MemoriaPD.jpeg" alt="Memoria" width="520"/>

---

## Interpretación

* **DP bottom‑up** suele ofrecer **tiempos más estables** y predecibles para `t` grandes, con bajo overhead de llamadas.
* **Memoización** empata en complejidad asintótica y puede ser más **simple de extender** (p.ej., más tipos de hamburguesa), pero incurre en overhead de función/caché.
* **Recursivo puro** colapsa rápidamente; útil sólo para explicar el estado del problema y validar casos muy pequeños.

En todos los tamaños, el **criterio lexicográfico** garantiza que, a igual número de hamburguesas, elegimos el **sobrante mínimo**; cuando hay empate total, documentamos la política (preferir la de menor tiempo).

---

## Implementaciones de referencia

Se incluyen plantillas en `src/` para C++ y Python con reconstrucción `(x,y)` y pruebas unitarias simples. Adapta al lenguaje requerido por el curso si es necesario.

---

## Pruebas

* **Unitarias**: casos borde (`t < min(m,n)`, `m==n`, varias `t` con mismo `h` y distinto `s`).
* **Golden tests**: ejemplos del enunciado y aleatorios reproducibles (semilla fija).

---

## Conclusión

La **programación dinámica** modela de forma natural este problema y permite comparar, en igualdad de condiciones, tres estilos de solución. Para tamaños reales, **bottom‑up** es la opción más estable para benchmarking; **memoización** es una alternativa clara y extensible; y la **recursión pura** queda para fines pedagógicos. Documentar el **desempate** y recuperar `(x,y)` vuelve la solución **auditable** y lista para producción/competencias.

# Proyecto: Homero y las Hamburguesas — Programación Dinámica

**Equipo:**

* Luis Fernando Reyes Altamirano
* Ricardo André Gorostieta Jurado
* Irene Escudero Cazarez

**Tiempo de trabajo individual:** 6–7 horas cada uno
**Tiempo de trabajo total:** 18–21 horas

Este proyecto es parte de la práctica de **Programación Dinámica** de la materia **Estructura de Datos**. El objetivo principal es comparar tres enfoques para resolver un problema clásico de optimización con tiempo limitado: **recursividad pura**, **recursividad con memoización (top‑down)** y **programación dinámica tabular (bottom‑up)**, midiendo su desempeño y analizando sus trade‑offs.

---

## Objetivos

* **Aplicar recursión, memoización y DP tabular** a un problema de maximización con restricción de tiempo.
* **Comparar el desempeño** (tiempo, memoria, estabilidad) de los tres enfoques.
* **Recuperar soluciones** (cuántas hamburguesas de cada tipo) y documentar criterios de desempate.

---

## Descripción del problema

Homero puede comer hamburguesas de **dos tipos**: una tarda **m** minutos y la otra **n** minutos. Dado un tiempo disponible **t**, se busca **maximizar el número total de hamburguesas** sin exceder **t**. Si no es posible usar todo el tiempo, se permite un **sobrante** (minutos sin usar). El criterio es **lexicográfico**:

1. maximizar hamburguesas; 2) minimizar sobrante.

Este problema es una variante de **coin change / knapsack sin límite** con pesos `m, n` y valor 1 por hamburguesa.

---

## Entradas y salidas

**Entrada:** enteros positivos `m, n, t`.
**Salida:** una línea con:

* `h` = máximo de hamburguesas,
* `s` = sobrante mínimo (se omite si es 0).
  Opcionalmente, también imprimimos la combinación `(x, y)` tomada.

**Ejemplos**

|  m |  n |  t | Mejor (x,y) |  h |  s |
| -: | -: | -: | :---------: | -: | -: |
|  3 |  5 | 54 |    (18,0)   | 18 |  0 |
|  3 |  5 | 56 |    (16,4)   | 20 |  0 |
|  6 |  8 | 17 |    (1,1)    |  2 |  1 |

---

## Enfoques implementados

### 1) Recursivo puro (backtracking)

Explora tomar `m` o `n` minutos hasta agotar tiempo. Es **didáctico** pero **exponencial** en el peor caso; útil sólo para tamaños pequeños.

### 2) Recursivo con **memoización** (top‑down)

Guarda `best(tiempo)` en una caché. Complejidad **O(t)** en tiempo y memoria; misma lógica que el recursivo, pero evitando recomputaciones. Soporta reconstrucción.

### 3) **DP tabular** (bottom‑up)

Construye `dpExact[0..t]` con el máximo de hamburguesas exactas para cada `x ≤ t`. Al final elige el `x` con mejor `dpExact[x]` y **sobrante** `t−x` mínimo. Complejidad **O(t)** tiempo y **O(t)** memoria. Ideal para benchmarking.

**Criterio de desempate**: primero `h` máximo; a igualdad, `s` mínimo; opcionalmente, preferimos más hamburguesas del tipo de **menor tiempo**.

---

## Correctitud (bosquejo)

Por **inducción en el tiempo**: `dpExact[0]=0` es óptimo. Si `dpExact[x]` es óptimo, actualizar `x+m` y/o `x+n` con `dpExact[x]+1` preserva optimalidad local; seleccionar al final el `x` con mejor `h` y menor `s` cumple el objetivo global. La versión con memoización es equivalente evaluada on‑demand.

---

## Complejidad teórica

| Método         |      Tiempo |    Memoria | Comentarios          |
| -------------- | ----------: | ---------: | -------------------- |
| Recursivo puro | Exponencial | O(t) stack | Sólo didáctico       |
| Memoización    |        O(t) |       O(t) | Sencillo, flexible   |
| DP bottom‑up   |        O(t) |       O(t) | Estable para pruebas |

> Nota: Si `t` es muy grande, considerar optimizaciones por **gcd(m,n)**, o técnicas por residuos módulo `min(m,n)`.

---

## Estructura del proyecto

```
/ (raíz)
├─ README.md
├─ data/
│  └─ casos.txt                # Instancias de prueba (m n t por línea)
├─ src/
│  ├─ homero_recursive.*       # Recursivo puro (C++/Java/Python)
│  ├─ homero_memo.*            # Top‑down con memo
│  ├─ homero_bottomup.*        # DP tabular 1D con reconstrucción
│  └─ utils.*                  # Helpers (I/O, reconstrucción, métricas)
└─ benchmarks/
   ├─ resultados.csv           # Tiempos/ memoria por método y caso
   └─ graficas/*.png           # Gráficas generadas
```

---

## Cómo ejecutar

### Opción A — C++

```bash
g++ -O2 -std=c++17 src/homero_bottomup.cpp -o homero
./homero < data/casos.txt
```

### Opción B — Python

```bash
python3 src/homero.py < data/casos.txt
```

**Formato `data/casos.txt`:**

```
m n t
m n t
...
```

---

## Conjunto de pruebas y parámetros

Para comparar, usamos tamaños crecientes de `t` y distintos pares `(m,n)`:

* Pequeños: `(3,5)` con `t ∈ {100, 500, 1e3}`
* Medianos: `(37,53)` con `t ∈ {1e4, 5e4, 1e5}`
* Grandes: `(401,997)` con `t ∈ {2e5, 5e5, 1e6}`

Cada instancia se corre **N=20** veces y se reporta **promedio** y **desviación estándar**.

---

## Resultados (tablas y gráficas)

> Coloca aquí tus resultados. Se incluyen nombres de archivo sugeridos para reutilizar tu flujo de trabajo de la práctica pasada.

**Tabla de resultados**
`benchmarks/resultados.csv` (resumen exportado a imagen para el README):

<img src="benchmarks/TablaPD.jpeg" alt="Tabla de resultados" width="520"/>

**Gráficas de tiempo vs tamaño**

* `benchmarks/graficas/TiempoPD_Total.jpeg` — Tiempo (ms) por método vs `t`.
* `benchmarks/graficas/TiempoPD_Pequeños.jpeg` — Zoom casos pequeños.
* `benchmarks/graficas/TiempoPD_Medianos.jpeg` — Zoom casos medianos.
* `benchmarks/graficas/TiempoPD_Grandes.jpeg` — Zoom casos grandes.

<img src="benchmarks/graficas/TiempoPD_Total.jpeg" alt="Tiempo total" width="520"/>

**Gráfica estabilidad (desviación estándar)**
`benchmarks/graficas/EstabilidadPD.jpeg` — Variabilidad temporal por método.

<img src="benchmarks/graficas/EstabilidadPD.jpeg" alt="Estabilidad" width="520"/>

**Gráfica memoria**
`benchmarks/graficas/MemoriaPD.jpeg` — Memoria (MB) estimada por método.

<img src="benchmarks/graficas/MemoriaPD.jpeg" alt="Memoria" width="520"/>

---

## Interpretación

* **DP bottom‑up** suele ofrecer **tiempos más estables** y predecibles para `t` grandes, con bajo overhead de llamadas.
* **Memoización** empata en complejidad asintótica y puede ser más **simple de extender** (p.ej., más tipos de hamburguesa), pero incurre en overhead de función/caché.
* **Recursivo puro** colapsa rápidamente; útil sólo para explicar el estado del problema y validar casos muy pequeños.

En todos los tamaños, el **criterio lexicográfico** garantiza que, a igual número de hamburguesas, elegimos el **sobrante mínimo**; cuando hay empate total, documentamos la política (preferir la de menor tiempo).

---

## Implementaciones de referencia

Se incluyen plantillas en `src/` para C++ y Python con reconstrucción `(x,y)` y pruebas unitarias simples. Adapta al lenguaje requerido por el curso si es necesario.

---

## Pruebas

* **Unitarias**: casos borde (`t < min(m,n)`, `m==n`, varias `t` con mismo `h` y distinto `s`).
* **Golden tests**: ejemplos del enunciado y aleatorios reproducibles (semilla fija).

---

## Conclusión

La **programación dinámica** modela de forma natural este problema y permite comparar, en igualdad de condiciones, tres estilos de solución. Para tamaños reales, **bottom‑up** es la opción más estable para benchmarking; **memoización** es una alternativa clara y extensible; y la **recursión pura** queda para fines pedagógicos. Documentar el **desempate** y recuperar `(x,y)` vuelve la solución **auditable** y lista para producción/competencias.

