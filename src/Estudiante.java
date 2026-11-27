/*
 * Universidad Tecnica de Ambato - FISEI
 * Carrera: Ingenieria de Software
 * Asignatura: Algoritmos y Logica de Programacion
 * Examen practico integrador - Sistema AulaLogic
 * Estudiante: Penafiel Leyber
 * Variante B: limite de aprobacion 12.
 * Si el docente asigna otra letra, cambie solo LIMITE_APROBACION
 * y el estudiante de prueba cuyo promedio debe ser exactamente ese limite.
 */
import java.util.Locale;

public class Estudiante {
    public static final int LIMITE_APROBACION = 12;
    public static final int CANTIDAD_NOTAS = 3;

    private String codigo;
    private String nombre;
    private int[] notas;

    public Estudiante(String codigo, String nombre, int[] notas) {
        if (codigo == null || nombre == null || notas == null || notas.length != CANTIDAD_NOTAS) {
            throw new IllegalArgumentException("Datos de estudiante incompletos.");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.notas = new int[CANTIDAD_NOTAS];
        for (int i = 0; i < CANTIDAD_NOTAS; i++) {
            this.notas[i] = notas[i];
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getNotas() {
        int[] copia = new int[notas.length];
        for (int i = 0; i < notas.length; i++) {
            copia[i] = notas[i];
        }
        return copia;
    }

    public double calcularPromedio() {
        int suma = 0;
        for (int i = 0; i < notas.length; i++) {
            suma = suma + notas[i];
        }
        return (double) suma / notas.length;
    }

    public int obtenerNotaMaxima() {
        int maxima = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > maxima) {
                maxima = notas[i];
            }
        }
        return maxima;
    }

    public int obtenerNotaMinima() {
        int minima = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] < minima) {
                minima = notas[i];
            }
        }
        return minima;
    }

    public String obtenerEstado() {
        if (calcularPromedio() >= LIMITE_APROBACION) {
            return "APROBADO";
        }
        return "REPROBADO";
    }

    public String aLineaArchivo() {
        return codigo + ";" + nombre + ";" + notas[0] + ";" + notas[1] + ";" + notas[2];
    }

    public String describir() {
        return "Codigo: " + codigo + "\n"
                + "Nombre: " + nombre + "\n"
                + "Calificaciones: " + notas[0] + ", " + notas[1] + ", " + notas[2] + "\n"
                + "Promedio: " + String.format(Locale.US, "%.2f", calcularPromedio()) + "\n"
                + "Nota maxima: " + obtenerNotaMaxima() + "\n"
                + "Nota minima: " + obtenerNotaMinima() + "\n"
                + "Estado: " + obtenerEstado();
    }
}
