# Examen-Segundo-Parcial-Practico — Sistema AulaLogic

## Identificación del estudiante

| Campo | Dato |
|-------|------|
| **Nombre** | Peñafiel Leyber |
| **Asignatura** | Algoritmos y Lógica de Programación |
| **Examen** | Segundo Parcial Práctico |
| **Fecha** | 27 de noviembre de 2026 |
| **Variante** | B |
| **Límite de aprobación** | 12 |

---

## Descripción del problema

El sistema **AulaLogic** es una aplicación de consola que permite a un docente registrar, consultar, procesar y almacenar las calificaciones de los estudiantes de una asignatura. Implementa cuatro clases colaborativas (`Estudiante`, `GestionAcademica`, `GestorArchivos` y `Main`) y conserva los datos entre ejecuciones mediante un archivo de texto plano.

El límite de aprobación asignado es **12**. Con variante B, un estudiante cuyo promedio sea exactamente 12 queda en estado **APROBADO**.

---

## Estructura del repositorio

```
Examen-Segundo-Parcial-Practico/
├── README.md
├── src/
│   ├── Main.java
│   ├── Estudiante.java
│   ├── GestionAcademica.java
│   └── GestorArchivos.java
├── datos/
│   └── estudiantes.txt
├── docs/
│   ├── Analisis.md
│   └── CasosDePrueba.md
└── evidencias/
    └── Ejecucion.png
```

---

## Instrucciones de compilación y ejecución

Los comandos se ejecutan **desde la raíz del repositorio** (`Examen-Segundo-Parcial-Practico/`). Esa carpeta es necesaria para que la ruta relativa `datos/estudiantes.txt` se resuelva correctamente.

### Compilación

```bash
javac -encoding UTF-8 -d bin src/*.java
```

### Ejecución

```bash
java -cp bin Main
```

> **Nota:** si la carpeta `bin/` no existe, créala antes con `mkdir bin` (Linux/macOS) o `mkdir bin` (Windows).

---

## Funcionalidades implementadas

| # | Funcionalidad | Detalle |
|---|---------------|---------|
| 1 | **Registro de estudiantes** | Valida código único, texto sin `;` y calificaciones enteras entre 0 y 20. Vuelve a pedir cada campo incorrecto. |
| 2 | **Procesamiento de calificaciones** | Calcula promedio real (división `double`), nota máxima, nota mínima y estado APROBADO/REPROBADO según el límite 12. |
| 3 | **Consulta por código** | Búsqueda lineal en el `ArrayList`; muestra todos los datos o un mensaje si el código no existe. |
| 4 | **Reporte académico** | Total de estudiantes, aprobados, reprobados, promedio general del curso, porcentaje de aprobados, estudiante con mayor promedio, estudiante con menor promedio, listado de por debajo del promedio general y listado ordenado de mayor a menor promedio. |
| 5 | **Guardado manual** | Escribe `datos/estudiantes.txt` completo con `FileWriter` + `BufferedWriter` sin duplicar registros. |
| 6 | **Persistencia automática** | Al iniciar, recupera los registros con `FileReader` + `BufferedReader` y recalcula promedios y estados. Al salir por la opción 6, guarda automáticamente antes de cerrar. |
| 7 | **Menú en bucle** | El menú se repite hasta la opción 6. Las opciones inválidas muestran un mensaje y no cierran el programa. |

---

## Limitaciones

- El archivo `datos/estudiantes.txt` no guarda el promedio ni el estado; ambos se recalculan al leer, por lo que dependen del límite configurado en el código.
- El programa no soporta caracteres `;` en código ni nombre, ya que ese carácter es el separador del archivo.
- La búsqueda distingue mayúsculas y minúsculas en el código (por ejemplo, `E001` ≠ `e001`).

---

## Control de versiones — commits realizados

1. `Análisis y estructura inicial` — creación del repositorio y esqueleto de clases.
2. `Implementación de clases y registro` — `Estudiante`, `GestionAcademica` y `GestorArchivos` con lógica principal.
3. `Procesamiento y persistencia` — reporte académico, lectura y escritura de archivo.
4. `Pruebas y documentación final` — `Main` completo, casos de prueba y documentación.
