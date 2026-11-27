# Casos de prueba

Variante B. Limite 12. Cada caso parte del archivo semilla, salvo indicacion contraria.

| Caso | Entrada | Resultado esperado | Resultado obtenido | Estado |
| --- | --- | --- | --- | --- |
| CP01 | Opcion 1. Codigo E006, nombre Pedro Lima, notas 16, 17 y 18. | Alta correcta. Promedio 17.00, maxima 18, minima 16, estado APROBADO. | Estudiante registrado. Codigo: E006 / Nombre: Pedro Lima / Calificaciones: 16, 17, 18 / Promedio: 17.00 / Nota maxima: 18 / Nota minima: 16 / Estado: APROBADO. | CORRECTO |
| CP02 | Opcion 1. Codigo E001. | Mensaje de codigo ya registrado. No pide nombre ni notas. El listado no crece. | El codigo ya esta registrado. No se guardo el estudiante. El menu continua sin pedir nombre ni notas. | CORRECTO |
| CP03 | Opcion 1. Codigo E007, nombre Diana Soto. En la calificacion 1 ingresar 21, luego -1, luego abc, luego 15. Despues 16 y 14. | 21, -1 y abc se rechazan y se vuelve a pedir la misma calificacion. El registro termina con 15, 16 y 14. Promedio 15.00, APROBADO. | Calificacion 1 (0-20): "La calificacion debe estar entre 0 y 20. Intente de nuevo." (para 21 y -1). "Ingrese un numero valido. Intente de nuevo." (para abc). Luego acepta 15. Resultado: Calificaciones: 15, 16, 14 / Promedio: 15.00 / Estado: APROBADO. | CORRECTO |
| CP04 | Opcion 3. Codigo E004. | Carlos Vega, notas 12, 12, 12, promedio 12.00, estado APROBADO. | Codigo: E004 / Nombre: Carlos Vega / Calificaciones: 12, 12, 12 / Promedio: 12.00 / Nota maxima: 12 / Nota minima: 12 / Estado: APROBADO. | CORRECTO |
| CP05 | Opcion 3. Codigo E001. | Muestra codigo, nombre, tres notas, promedio 15.00, maxima 18, minima 12 y APROBADO. | Codigo: E001 / Nombre: Ana Torres / Calificaciones: 15, 18, 12 / Promedio: 15.00 / Nota maxima: 18 / Nota minima: 12 / Estado: APROBADO. | CORRECTO |
| CP06 | Opcion 3. Codigo E999. | Mensaje informativo de que no existe. El menu sigue disponible. | No se encontro un estudiante con el codigo: E999. El menu continua disponible. | CORRECTO |
| CP07 | Iniciar con archivo vacio (sin semilla). Elegir la opcion 4. | Mensaje de que no hay estudiantes y no se puede generar el reporte. No hay division. | No hay estudiantes registrados. No es posible generar el reporte. El menu continua sin ningun error ni excepcion. | CORRECTO |
| CP08 | Opcion 1 con E006, Pedro Lima, 16, 17, 18. Opcion 5. Cerrar. Abrir el archivo y comprobar la linea E006;Pedro Lima;16;17;18. Ejecutar de nuevo y listar. | E006 aparece sin volver a registrarlo. Promedio 17.00 recalculado, no leido del archivo. | Registros guardados en datos/estudiantes.txt. Total: 6. Nueva ejecucion: Se recuperaron 6 registro(s) desde datos/estudiantes.txt. E006 / Pedro Lima / Promedio: 17.00 sin haberlo registrado de nuevo. | CORRECTO |

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
```

## Resultado obtenido con el archivo semilla

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
```

**Estado del reporte:** CORRECTO — resultado obtenido coincide con el esperado.
