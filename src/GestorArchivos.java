import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorArchivos {
    private String ruta;

    public GestorArchivos(String ruta) {
        this.ruta = ruta;
    }

    public ArrayList<Estudiante> leer() {
        ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(ruta));
            String linea;
            int numeroLinea = 0;
            int omitidas = 0;
            while ((linea = br.readLine()) != null) {
                numeroLinea = numeroLinea + 1;
                linea = linea.trim();
                if (linea.isEmpty()) {
                    continue;
                }
                Estudiante estudiante = parsearLinea(linea);
                if (estudiante == null) {
                    omitidas = omitidas + 1;
                    System.out.println("Linea " + numeroLinea + " omitida: formato invalido.");
                } else {
                    lista.add(estudiante);
                }
            }
            System.out.println("Se recuperaron " + lista.size() + " registro(s) desde " + ruta + ".");
            if (omitidas > 0) {
                System.out.println("Lineas omitidas: " + omitidas + ".");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existe " + ruta + ". Se inicia con la lista vacia.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("No se pudo cerrar el archivo de lectura.");
                }
            }
        }
        return lista;
    }

    public boolean guardar(ArrayList<Estudiante> estudiantes) {
        File archivo = new File(ruta);
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists() && !carpeta.mkdirs()) {
            System.out.println("No se pudo crear la carpeta de datos.");
            return false;
        }

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(ruta));
            for (int i = 0; i < estudiantes.size(); i++) {
                bw.write(estudiantes.get(i).aLineaArchivo());
                bw.newLine();
            }
            System.out.println("Registros guardados en " + ruta + ". Total: " + estudiantes.size() + ".");
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    System.out.println("No se pudo cerrar el archivo de escritura.");
                }
            }
        }
    }

    private Estudiante parsearLinea(String linea) {
        String[] partes = linea.split(";");
        if (partes.length != 5) {
            return null;
        }
        String codigo = partes[0].trim();
        String nombre = partes[1].trim();
        if (codigo.isEmpty() || nombre.isEmpty()) {
            return null;
        }
        int[] notas = new int[Estudiante.CANTIDAD_NOTAS];
        try {
            for (int i = 0; i < Estudiante.CANTIDAD_NOTAS; i++) {
                notas[i] = Integer.parseInt(partes[i + 2].trim());
                if (notas[i] < 0 || notas[i] > 20) {
                    return null;
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return new Estudiante(codigo, nombre, notas);
    }
}
