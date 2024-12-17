package ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GuardadoDisco {
    public static void main(String[] args) {
        /*
         * Acceder a un fichero de texto, volcadlo a otro fichero temporal realizando lo siguiente:
         * 1 - Forzamos Mayúscula después de .
         * 2 - Eliminamos dobles/triples/etc.. consecutivos espacios en blanco.
         * Utilizando las clases: File, BufferedReader, BufferedWriter, FileReader, FileWriter.
         * File.createTempFile nos da un nombre de fichero temporal no utilizado antes.
         * File(objeto).renameTo nos permite renombrar los ficheros al final del proceso.*
         */

        File temporal = null; // Fichero temporal
        try {
            temporal = File.createTempFile("temporal", ".txt"); // Crear fichero temporal
            File fichero = new File("D:\\Java\\EjercicioSobreFILE\\ficheroPrueba.txt");
            volcado(temporal, fichero);
            File agregarNombre = new File("D:\\Java\\EjercicioSobreFILE\\ficheroPrueba_renombrado.txt");
            temporal.renameTo(agregarNombre);
            System.out.println("Archivo temporal creado: " + agregarNombre.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // VOLCADO
    public static void volcado(File temporal, File fichero) {
        FileReader fich = null;
        FileWriter temp = null;
        BufferedReader brfreader = null;
        BufferedWriter brfwritte = null;

        try {
            fich = new FileReader(fichero);
            temp = new FileWriter(temporal);
            brfreader = new BufferedReader(fich);
            brfwritte = new BufferedWriter(temp);
            // Utilizando BufferedReader
            String line = brfreader.readLine();

            while (line != null) {
                // 1. Reemplazar múltiples espacios en blanco por uno solo
                line = line.replaceAll("\\s+", " ");
                
                // 2. Convertir a mayúscula la letra que sigue a un punto (.)
                char[] linea = line.toCharArray();
                boolean convertirMayuscula = false;

                for (int o : linea) {
                    if (convertirMayuscula && o >= 'a' && o <= 'z') {
                        brfwritte.write(mayusculas(o));
                        convertirMayuscula = false;
                    } else {
                        brfwritte.write(o);
                    }

                    // Si se encuentra un punto, la siguiente letra será mayúscula
                    if (o == '.') {
                        convertirMayuscula = true;
                    }
                }

                brfwritte.newLine();
                line = brfreader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Finalmente cerrar el fichero correctamente
            try {
                if (brfwritte != null) {
                    brfwritte.close();
                }
                if (brfreader != null) {
                    brfreader.close();
                }
            } catch (IOException e) {
                System.out.println("Ocurrió un error inesperado al cerrar el fichero.");
                e.printStackTrace();
            }
        }
    }

    // Convertir en mayúsculas
    public static int mayusculas(int letra) {
        if (letra >= 'a' && letra <= 'z') {
            letra = letra - 32; // Convertir a mayúscula
        }
        return letra;
    }
}
