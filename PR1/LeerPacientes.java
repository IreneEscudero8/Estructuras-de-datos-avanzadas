import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeerPacientes {
    public static void main(String[] args) {
        String ruta = (args.length > 0) ? args[0] : "pacientes.txt";
        try {
            List<double[]> pacientes = leerPacientes(new File(ruta));

            // Demo: imprimir lo leído
            for (int i = 0; i < pacientes.size(); i++) {
                System.out.print("Paciente " + (i + 1) + ": ");
                double[] p = pacientes.get(i);
                for (double v : p) System.out.print(v + " ");
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.err.println("❌ Archivo no encontrado: " + ruta);
        }
    }

    /** Lee un archivo donde cada línea representa un paciente con 10 números. */
    public static List<double[]> leerPacientes(File archivo) throws FileNotFoundException {
        List<double[]> lista = new ArrayList<>();

        // Scanner con charset explícito. Usamos \R para separar por saltos de línea.
        try (Scanner sc = new Scanner(archivo, StandardCharsets.UTF_8.name())) {
            sc.useDelimiter("\\R");
            int numLinea = 0;

            while (sc.hasNext()) {
                String linea = sc.next();
                numLinea++;

                if (linea == null) continue;
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) continue;

                String[] tokens = linea.split("\\s+");
                if (tokens.length != 10) {
                    System.err.printf("⚠️  Línea %d: se esperaban 10 números, hay %d. Se omite.%n",
                                      numLinea, tokens.length);
                    continue;
                }

                double[] datos = new double[10];
                boolean ok = true;

                for (int i = 0; i < 10; i++) {
                    String t = tokens[i].replace(',', '.'); // permite 36,5 o 36.5
                    try {
                        datos[i] = Double.parseDouble(t);
                    } catch (NumberFormatException ex) {
                        System.err.printf("⚠️  Línea %d: token '%s' no es un número válido. Se omite la línea.%n",
                                          numLinea, tokens[i]);
                        ok = false;
                        break;
                    }
                }

                if (ok) lista.add(datos);
            }
        }
        return lista;
    }
}
