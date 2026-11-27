# Sistema de seguimiento del rendimiento academico - AulaLogic

## Identificacion

- Estudiante: Peñafiel Leyber
- Asignatura: Algoritmos y Logica de Programacion
- Carrera: Ingenieria de Software, Universidad Tecnica de Ambato
- Nivel / modalidad: Primero / Individual
- Fecha: 27 de noviembre de 2026
- Variante asignada: B
- Promedio minimo de aprobacion: 12

## Descripcion del problema

La Universidad Tecnica de Ambato necesita un sistema de consola para registrar, consultar, procesar y almacenar las calificaciones de una asignatura. Los registros deben conservarse despues de cerrar el programa y el sistema debe generar indicadores del rendimiento del curso.

## Variante

El limite esta en la constante `Estudiante.LIMITE_APROBACION`. Con la variante B vale 12. El estado es APROBADO cuando el promedio es mayor o igual a ese limite. Carlos Vega (notas 12, 12 y 12) promedia exactamente 12 y debe aprobar.

## Compilacion y ejecucion

Ejecutar siempre desde la raiz del repositorio, para que la ruta `datos/estudiantes.txt` se resuelva bien.

```bash
javac -encoding UTF-8 -d bin src/*.java
java -cp bin Main
