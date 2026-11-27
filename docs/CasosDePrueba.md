# Casos de prueba

Variante B. Limite 12. Cada caso parte del archivo semilla, salvo indicacion contraria.

| Caso | Entrada | Resultado esperado | Resultado obtenido | Estado |
| --- | --- | --- | --- | --- |
| CP01 | Opcion 1. Codigo E006, nombre Pedro Lima, notas 16, 17 y 18. | Alta correcta. Promedio 17.00, maxima 18, minima 16, estado APROBADO. | Prueba de escritorio: (16+17+18)/3 = 17.00, APROBADO. Falta la corrida en consola. | Pendiente de verificacion en consola |
| CP02 | Opcion 1. Codigo E001. | Mensaje de codigo ya registrado. No pide nombre ni notas. El listado no crece. | Prueba de escritorio: `existeCodigo` encuentra E001 y el metodo retorna sin registrar. Falta la corrida. | Pendiente de verificacion en consola |
| CP03 | Opcion 1. Codigo E007, nombre Diana Soto. En la calificacion 1 ingresar 21, luego -1, luego abc, luego 15. Despues 16 y 14. | 21, -1 y abc se rechazan y se vuelve a pedir la misma calificacion. El registro termina con 15, 16 y 14. Promedio 15.00, APROBADO. | Prueba de escritorio: el `while` no sale hasta una nota entera entre 0 y 20. (15+16+14)/3 = 15.00. Falta la corrida. | Pendiente de verificacion en consola |
| CP04 | Opcion 3. Codigo E004. | Carlos Vega, notas 12, 12, 12, promedio 12.00, estado APROBADO. | Prueba de escritorio: 36/3 = 12.00 y 12.00 >= 12, APROBADO. Falta la corrida. | Pendiente de verificacion en consola |
| CP05 | Opcion 3. Codigo E001. | Muestra codigo, nombre, tres notas, promedio 15.00, maxima 18, minima 12 y APROBADO. | Prueba de escritorio: 45/3 = 15.00, maxima 18, minima 12. Falta la corrida. | Pendiente de verificacion en consola |
| CP06 | Opcion 3. Codigo E999. | Mensaje informativo de que no existe. El menu sigue disponible. | Prueba de escritorio: `buscar` recorre la lista y devuelve null. Falta la corrida. | Pendiente de verificacion en consola |
| CP07 | Copiar `datos/estudiantes.txt` a un respaldo y renombrarlo. Ejecutar y elegir la opcion 4. No usar la opcion 6 hasta restaurar el respaldo, porque salir guarda la lista vacia y crearia un archivo vacio. | Mensaje de que no hay estudiantes y no se puede generar el reporte. No hay division. | Prueba de escritorio: `generarReporte` retorna antes de dividir si la lista esta vacia. Falta la corrida. | Pendiente de verificacion en consola |
| CP08 | Opcion 1 con E006, Pedro Lima, 16, 17, 18. Opcion 5. Cerrar. Abrir el archivo y comprobar la linea `E006;Pedro Lima;16;17;18`. Ejecutar de nuevo y listar. | E006 aparece sin volver a registrarlo. Promedio 17.00 recalculado, no leido del archivo. | Prueba de escritorio: el archivo no tiene columna de promedio; `leer` construye el objeto y `calcularPromedio` vuelve a dividir. Falta la corrida. | Pendiente de verificacion en consola |

## Reporte esperado con el archivo semilla

```text
========== REPORTE ACADEMICO ==========
Limite de aprobacion: 12
Total de estudiantes: 5
Aprobados: 4
Reprobados: 1
Promedio general del curso: 15.00
Porcentaje de aprobados: 80.00 %

Promedio mas alto (primero registrado en caso de empate):
Codigo: E003
Nombre: Marta Ruiz
Calificaciones: 20, 18, 19
Promedio: 19.00
Nota maxima: 20
Nota minima: 18
Estado: APROBADO

Promedio mas bajo (primero registrado en caso de empate):
Codigo: E002
Nombre: Luis Perez
Calificaciones: 8, 10, 12
Promedio: 10.00
Nota maxima: 12
Nota minima: 8
Estado: REPROBADO

Estudiantes con promedio inferior al general (15.00):
- E002 Luis Perez (10.00)
- E004 Carlos Vega (12.00)

Listado ordenado de mayor a menor promedio:
1. E003 - Marta Ruiz - 19.00 - APROBADO
2. E005 - Elena Cruz - 19.00 - APROBADO
3. E001 - Ana Torres - 15.00 - APROBADO
4. E004 - Carlos Vega - 12.00 - APROBADO
5. E002 - Luis Perez - 10.00 - REPROBADO
=======================================
