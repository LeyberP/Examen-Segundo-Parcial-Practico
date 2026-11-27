# Análisis del sistema — AulaLogic

**Estudiante:** Peñafiel Leyber  
**Asignatura:** Algoritmos y Lógica de Programación  
**Fecha:** 27 de noviembre de 2026  
**Variante:** B — Límite de aprobación: **12**

---

## 1. Descripción del problema

La Universidad Técnica de Ambato necesita un sistema de consola que registre, consulte, procese y almacene las calificaciones de los estudiantes de una asignatura. El sistema debe:

- Registrar estudiantes con código único, nombre y tres calificaciones validadas.
- Calcular promedio, nota máxima, nota mínima y estado académico (APROBADO / REPROBADO).
- Permitir buscar un estudiante por código.
- Generar un reporte académico del curso.
- Guardar y recuperar los registros desde un archivo de texto, para conservarlos entre ejecuciones.

---

## 2. Análisis de entrada, proceso y salida

### RF01 — Registro de estudiante

| Elemento | Descripción |
|----------|-------------|
| **Entrada** | Código (texto sin `;`), nombre (texto sin `;`), tres calificaciones enteras entre 0 y 20. |
| **Proceso** | Verificar que el código no esté repetido. Validar que código y nombre no estén vacíos ni contengan `;`. Pedir cada calificación de nuevo si está fuera del rango o no es un entero. Crear el objeto `Estudiante` y agregarlo al `ArrayList`. |
| **Salida** | Confirmación del registro con los datos calculados, o mensaje de error si el código ya existe. |

### RF02 — Procesamiento de calificaciones

| Elemento | Descripción |
|----------|-------------|
| **Entrada** | Arreglo `int[]` con las tres notas del estudiante. |
| **Proceso** | Promedio = (nota1 + nota2 + nota3) / 3.0 (división real). Recorrido del arreglo para obtener máximo y mínimo. Comparar el promedio con el límite 12 para determinar el estado. |
| **Salida** | `double` promedio, `int` nota máxima, `int` nota mínima, `String` estado ("APROBADO" o "REPROBADO"). |

### RF03 — Consulta por código

| Elemento | Descripción |
|----------|-------------|
| **Entrada** | Código ingresado por el usuario. |
| **Proceso** | Recorrer el `ArrayList` comparando cada código con `equals`. Retornar el objeto encontrado o `null`. |
| **Salida** | Datos completos del estudiante, o mensaje "No se encontro un estudiante con el codigo indicado." |

### RF04 — Reporte académico

| Elemento | Descripción |
|----------|-------------|
| **Entrada** | El `ArrayList` de estudiantes cargado en memoria. |
| **Proceso** | Contar total, aprobados y reprobados. Acumular promedios para obtener el promedio general. Calcular porcentaje de aprobados. Recorrer la lista para encontrar el de mayor y menor promedio (en caso de empate, el primero registrado). Filtrar los que están por debajo del promedio general. Ordenar una copia de la lista de mayor a menor promedio con `Collections.sort`. |
| **Salida** | Totales, promedios, porcentaje, estudiante más alto, estudiante más bajo, lista de por debajo del promedio y listado ordenado. Si la lista está vacía, se muestra un mensaje y no se realiza ninguna división. |

### RF05 — Persistencia en archivo

| Elemento | Descripción |
|----------|-------------|
| **Entrada (guardar)** | El `ArrayList` de estudiantes en memoria. |
| **Proceso (guardar)** | Abrir `datos/estudiantes.txt` con `FileWriter` + `BufferedWriter`. Escribir cada estudiante en formato `codigo;nombre;nota1;nota2;nota3`. Sobrescribir el archivo completo para evitar duplicados. |
| **Salida (guardar)** | Archivo `datos/estudiantes.txt` actualizado. |
| **Entrada (leer)** | Archivo `datos/estudiantes.txt`. |
| **Proceso (leer)** | Abrir con `FileReader` + `BufferedReader`. Separar cada línea por `;`. Convertir las notas a `int[]` y construir objetos `Estudiante`. El promedio y el estado se recalculan a partir de las notas, no se leen del archivo. Si el archivo no existe, el programa inicia con la lista vacía sin lanzar una excepción. |
| **Salida (leer)** | `ArrayList<Estudiante>` cargado con los registros del archivo. |

---

## 3. Arquitectura de clases

### 3.1 Clase `Estudiante`

Representa a un estudiante y encapsula sus datos y cálculos.

| Atributo / Método | Tipo | Descripción |
|-------------------|------|-------------|
| `codigo` | `private String` | Identificador único. |
| `nombre` | `private String` | Nombre completo. |
| `notas` | `private int[]` | Arreglo de tres calificaciones. |
| `CANTIDAD_NOTAS` | `static final int = 3` | Constante que define el número de notas. |
| `calcularPromedio()` | `double` | Suma de notas dividida entre 3.0. |
| `obtenerNotaMaxima()` | `int` | Recorre `notas[]` y retorna el mayor. |
| `obtenerNotaMinima()` | `int` | Recorre `notas[]` y retorna el menor. |
| `obtenerEstado()` | `String` | "APROBADO" si promedio ≥ 12, "REPROBADO" en caso contrario. |
| `describir()` | `String` | Retorna una cadena con todos los datos calculados para mostrar en consola. |
| Getters | — | `getCodigo()`, `getNombre()`, `getNotas()`. |

### 3.2 Clase `GestionAcademica`

Administra la colección de estudiantes y genera los indicadores del curso.

| Atributo / Método | Descripción |
|-------------------|-------------|
| `estudiantes` | `private ArrayList<Estudiante>` con todos los registros en memoria. |
| `registrar(Estudiante)` | Agrega el estudiante si su código no existe. Retorna `boolean`. |
| `existeCodigo(String)` | Recorre la lista y retorna `true` si el código ya está registrado. |
| `buscar(String)` | Retorna el `Estudiante` con ese código, o `null`. |
| `listar()` | Recorre la lista y llama a `describir()` de cada estudiante. |
| `generarReporte()` | Calcula totales, promedios, extremos y orden; imprime el reporte. Protege la división si la lista está vacía. |
| `getEstudiantes()` | Retorna el `ArrayList` completo para que `GestorArchivos` lo guarde. |

### 3.3 Clase `GestorArchivos`

Maneja la lectura y escritura del archivo de persistencia.

| Método | Descripción |
|--------|-------------|
| `guardar(ArrayList<Estudiante>, String)` | Escribe cada estudiante en `datos/estudiantes.txt` con `FileWriter` + `BufferedWriter`. Captura `IOException`. |
| `leer(String)` | Lee línea a línea con `FileReader` + `BufferedReader`, separa por `;` y reconstruye los objetos. Captura `FileNotFoundException` e `IOException`. |

### 3.4 Clase `Main`

Presenta el menú y coordina las demás clases.

| Método | Descripción |
|--------|-------------|
| `main(String[])` | Al iniciar, llama a `GestorArchivos.leer`. Muestra el menú en bucle `do-while` hasta la opción 6. Antes de salir llama a `GestorArchivos.guardar`. |
| `leerOpcion(Scanner)` | Captura la opción del menú con `try-catch NumberFormatException`; retorna -1 si no es un entero. |
| `registrarEstudiante(Scanner, GestionAcademica)` | Llama a `leerTextoValido` para código y nombre, y a `leerNota` para cada calificación. |
| `buscarEstudiante(Scanner, GestionAcademica)` | Pide el código y llama a `gestion.buscar`. |
| `leerTextoValido(Scanner, String)` | Repite el pedido mientras el texto esté vacío o contenga `;`. |
| `leerNota(Scanner, int)` | Repite el pedido mientras el valor no sea un entero entre 0 y 20. |

---

## 4. Conocimientos aplicados

| Tema | Aplicación en el proyecto |
|------|---------------------------|
| Variables y tipos de datos | `String codigo`, `String nombre`, `int[] notas`, `double promedio`, `boolean aprobado`. |
| Operadores y expresiones | División real `(suma / 3.0)`, comparaciones `>=`, `<=`, operador ternario en estado. |
| Condicionales | `if-else` para validaciones, estado académico y control de lista vacía. |
| Ciclos | `do-while` para el menú, `for` para notas y `while` para validar entrada. |
| Métodos | Métodos `void` para registrar y listar; métodos con retorno para calcular, buscar y validar. |
| Clases y objetos | Cuatro clases con encapsulamiento (`private` + getters); objetos creados con `new`. |
| Arreglos y colecciones | `int[]` para las notas de cada estudiante; `ArrayList<Estudiante>` para la colección. |
| Búsqueda y ordenamiento | Búsqueda lineal por código; `Collections.sort` con `Comparator` para el reporte ordenado. |
| Lectura/escritura de archivos | `FileWriter` + `BufferedWriter` para guardar; `FileReader` + `BufferedReader` para leer. |
| Manejo de excepciones | `try-catch` para `NumberFormatException`, `FileNotFoundException` e `IOException`. |
| Control de versiones | Repositorio individual en GitHub con commits que evidencian el desarrollo progresivo. |

---

## 5. Decisiones de diseño

- El límite de aprobación (variante B = **12**) se declara como constante en `Main` y se pasa a `GestionAcademica` al crear el objeto, de modo que todas las comparaciones usen un único valor.
- El promedio y el estado **no se guardan en el archivo**; se recalculan al leer, lo que evita inconsistencias si el límite cambiara.
- El menú usa `leerOpcion` con `try-catch` en lugar de `Scanner.nextInt`, para que una entrada no numérica no finalice el programa inesperadamente.
- Si la lista está vacía al generar el reporte, el método retorna antes de cualquier división, evitando `ArithmeticException`.
- El archivo se sobrescribe completamente en cada guardado (`FileWriter(ruta, false)`), sin acumular líneas repetidas.

---

## 6. Compilación y ejecución

Desde la raíz del repositorio (`Examen-Segundo-Parcial-Practico/`):

```bash
javac -encoding UTF-8 -d bin src/*.java
java -cp bin Main
```

La ruta relativa `datos/estudiantes.txt` se resuelve correctamente solo si el programa se ejecuta desde la raíz del repositorio.
