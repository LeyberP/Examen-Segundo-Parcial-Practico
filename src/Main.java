import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestionAcademica gestion = new GestionAcademica();
        GestorArchivos archivos = new GestorArchivos("datos/estudiantes.txt");

        ArrayList<Estudiante> recuperados = archivos.leer();
        for (int i = 0; i < recuperados.size(); i++) {
            Estudiante estudiante = recuperados.get(i);
            if (!gestion.registrar(estudiante)) {
                System.out.println("No se cargo el codigo duplicado o invalido: " + estudiante.getCodigo());
            }
        }

        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(sc);
            switch (opcion) {
                case 1:
                    registrarEstudiante(sc, gestion);
                    break;
                case 2:
                    System.out.println(gestion.listar());
                    break;
                case 3:
                    buscarEstudiante(sc, gestion);
                    break;
                case 4:
                    System.out.println(gestion.generarReporte());
                    break;
                case 5:
                    archivos.guardar(gestion.obtenerEstudiantes());
                    break;
                case 6:
                    archivos.guardar(gestion.obtenerEstudiantes());
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opcion incorrecta. Seleccione un numero del 1 al 6.");
                    break;
            }
        } while (opcion != 6);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("============================================");
        System.out.println("           SISTEMA AULALOGIC");
        System.out.println("============================================");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Listar estudiantes");
        System.out.println("3. Buscar estudiante");
        System.out.println("4. Generar reporte academico");
        System.out.println("5. Guardar registros");
        System.out.println("6. Salir");
        System.out.println("============================================");
        System.out.print("Seleccione una opcion: ");
    }

    private static int leerOpcion(Scanner sc) {
        String entrada = sc.nextLine().trim();
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void registrarEstudiante(Scanner sc, GestionAcademica gestion) {
        String codigo = leerTextoValido(sc, "Codigo del estudiante: ");
        if (gestion.existeCodigo(codigo)) {
            System.out.println("El codigo ya esta registrado. No se guardo el estudiante.");
            return;
        }

        String nombre = leerTextoValido(sc, "Nombre completo: ");
        int[] notas = new int[Estudiante.CANTIDAD_NOTAS];
        for (int i = 0; i < Estudiante.CANTIDAD_NOTAS; i++) {
            notas[i] = leerNota(sc, i + 1);
        }

        Estudiante estudiante = new Estudiante(codigo, nombre, notas);
        if (gestion.registrar(estudiante)) {
            System.out.println("Estudiante registrado.");
            System.out.println(estudiante.describir());
        } else {
            System.out.println("No se pudo registrar el estudiante.");
        }
    }

    private static void buscarEstudiante(Scanner sc, GestionAcademica gestion) {
        System.out.print("Ingrese el codigo del estudiante a buscar: ");
        String codigo = sc.nextLine().trim();
        Estudiante encontrado = gestion.buscar(codigo);
        if (encontrado == null) {
            System.out.println("No se encontro un estudiante con el codigo: " + codigo);
        } else {
            System.out.println(encontrado.describir());
        }
    }

    private static String leerTextoValido(Scanner sc, String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("El campo no puede estar vacio. Intente de nuevo.");
            } else if (texto.contains(";")) {
                System.out.println("El campo no puede contener punto y coma. Intente de nuevo.");
                texto = "";
            }
        } while (texto.isEmpty());
        return texto;
    }

    private static int leerNota(Scanner sc, int numero) {
        int nota = -1;
        do {
            System.out.print("Calificacion " + numero + " (0-20): ");
            String entrada = sc.nextLine().trim();
            try {
                nota = Integer.parseInt(entrada);
                if (nota < 0 || nota > 20) {
                    System.out.println("La calificacion debe estar entre 0 y 20. Intente de nuevo.");
                    nota = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido. Intente de nuevo.");
            }
        } while (nota < 0);
        return nota;
    }
}
