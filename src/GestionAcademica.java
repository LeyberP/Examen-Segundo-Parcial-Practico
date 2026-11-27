import java.util.ArrayList;
import java.util.Locale;

public class GestionAcademica {
    private ArrayList<Estudiante> estudiantes;

    public GestionAcademica() {
        this.estudiantes = new ArrayList<Estudiante>();
    }

    public boolean registrar(Estudiante estudiante) {
        if (estudiante == null) {
            return false;
        }
        if (estudiante.getCodigo().isEmpty() || estudiante.getNombre().isEmpty()) {
            return false;
        }
        if (estudiante.getCodigo().contains(";") || estudiante.getNombre().contains(";")) {
            return false;
        }
        if (existeCodigo(estudiante.getCodigo())) {
            return false;
        }
        int[] notas = estudiante.getNotas();
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] < 0 || notas[i] > 20) {
                return false;
            }
        }
        estudiantes.add(estudiante);
        return true;
    }

    public boolean existeCodigo(String codigo) {
        return buscar(codigo) != null;
    }

    public Estudiante buscar(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getCodigo().equals(codigo)) {
                return estudiantes.get(i);
            }
        }
        return null;
    }

    public String listar() {
        if (estudiantes.isEmpty()) {
            return "No hay estudiantes registrados.";
        }
        String texto = "========== LISTADO DE ESTUDIANTES ==========\n";
        for (int i = 0; i < estudiantes.size(); i++) {
            texto = texto + estudiantes.get(i).describir() + "\n";
            texto = texto + "-------------------------------------------\n";
        }
        return texto;
    }

    public String generarReporte() {
        if (estudiantes.isEmpty()) {
            return "No hay estudiantes registrados. No es posible generar el reporte.";
        }

        int total = estudiantes.size();
        int aprobados = 0;
        int reprobados = 0;
        double sumaPromedios = 0;
        Estudiante mejor = estudiantes.get(0);
        Estudiante peor = estudiantes.get(0);

        for (int i = 0; i < total; i++) {
            Estudiante actual = estudiantes.get(i);
            double promedio = actual.calcularPromedio();
            sumaPromedios = sumaPromedios + promedio;
            if (actual.obtenerEstado().equals("APROBADO")) {
                aprobados = aprobados + 1;
            } else {
                reprobados = reprobados + 1;
            }
            if (promedio > mejor.calcularPromedio()) {
                mejor = actual;
            }
            if (promedio < peor.calcularPromedio()) {
                peor = actual;
            }
        }

        double promedioGeneral = sumaPromedios / total;
        double porcentaje = (aprobados * 100.0) / total;

        String reporte = "";
        reporte = reporte + "========== REPORTE ACADEMICO ==========\n";
        reporte = reporte + "Limite de aprobacion: " + Estudiante.LIMITE_APROBACION + "\n";
        reporte = reporte + "Total de estudiantes: " + total + "\n";
        reporte = reporte + "Aprobados: " + aprobados + "\n";
        reporte = reporte + "Reprobados: " + reprobados + "\n";
        reporte = reporte + "Promedio general del curso: " + String.format(Locale.US, "%.2f", promedioGeneral) + "\n";
        reporte = reporte + "Porcentaje de aprobados: " + String.format(Locale.US, "%.2f", porcentaje) + " %\n";
        reporte = reporte + "\nPromedio mas alto (primero registrado en caso de empate):\n";
        reporte = reporte + mejor.describir() + "\n";
        reporte = reporte + "\nPromedio mas bajo (primero registrado en caso de empate):\n";
        reporte = reporte + peor.describir() + "\n";
        reporte = reporte + "\nEstudiantes con promedio inferior al general ("
                + String.format(Locale.US, "%.2f", promedioGeneral) + "):\n";

        int inferiores = 0;
        for (int i = 0; i < total; i++) {
            Estudiante actual = estudiantes.get(i);
            if (actual.calcularPromedio() < promedioGeneral) {
                reporte = reporte + "- " + actual.getCodigo() + " " + actual.getNombre()
                        + " (" + String.format(Locale.US, "%.2f", actual.calcularPromedio()) + ")\n";
                inferiores = inferiores + 1;
            }
        }
        if (inferiores == 0) {
            reporte = reporte + "Ninguno.\n";
        }

        reporte = reporte + "\nListado ordenado de mayor a menor promedio:\n";
        ArrayList<Estudiante> ordenados = ordenarPorPromedio();
        for (int i = 0; i < ordenados.size(); i++) {
            Estudiante actual = ordenados.get(i);
            reporte = reporte + (i + 1) + ". " + actual.getCodigo() + " - " + actual.getNombre()
                    + " - " + String.format(Locale.US, "%.2f", actual.calcularPromedio())
                    + " - " + actual.obtenerEstado() + "\n";
        }
        reporte = reporte + "=======================================";
        return reporte;
    }

    public ArrayList<Estudiante> obtenerEstudiantes() {
        ArrayList<Estudiante> copia = new ArrayList<Estudiante>();
        for (int i = 0; i < estudiantes.size(); i++) {
            copia.add(estudiantes.get(i));
        }
        return copia;
    }

    private ArrayList<Estudiante> ordenarPorPromedio() {
        ArrayList<Estudiante> copia = obtenerEstudiantes();
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {
                if (copia.get(j).calcularPromedio() < copia.get(j + 1).calcularPromedio()) {
                    Estudiante temporal = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temporal);
                }
            }
        }
        return copia;
    }
}
