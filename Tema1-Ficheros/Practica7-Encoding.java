package ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.Normalizer;

public class Encoding {
	/*Realiza un programa que convierta ficheros indicándole 4 parámetros:
	     + Path fichero de entrada
	     + Enconding de entrada
	     + Path fichero de salida (debe crearse)
	     + Enconding de salida
	Se podrán elegir los siguientes encodings:
	
	- ASCII, UTF-8, UTF-16, ISO-8859-1
	
	Utilizando las siguientes clases para leer:
	
	- InputStreamReader, BufferedReader
	
	Utilizando las siguientes clases para escribir:
	
	- BufferedWriter, OutputStreamWriter, FileOutputStream
	
	Capturando las excepciones siguientes:
	
	- FileNotFoundException, IOException, Exception
	*/
	public static boolean comprobarParametroEncoding(String encoding) {
		return encoding.equalsIgnoreCase("ASCII") || encoding.equalsIgnoreCase("Utf-8") ||  
				encoding.equalsIgnoreCase("Utf-16") ||  encoding.equalsIgnoreCase("iso-8859-1");//Comprobar encoding elegido
	}
	public static boolean comprobarParametroRuta(File entrada) {
		return entrada.exists();//Comprobar ruta
	}

	public static void traductor(String entradaRuta, String salidaRuta, String encodingEntrada, String encondingSalida) {
		BufferedReader buffelectura = null;
        BufferedWriter buffeEscritura = null;

        try {
            // Leer el archivo en UTF-8
            buffelectura = new BufferedReader(new InputStreamReader(new FileInputStream(entradaRuta), encodingEntrada));
            
            // Escribir en el archivo en formato ASCII
            buffeEscritura = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(salidaRuta), encondingSalida));

            String line;
            while ((line = buffelectura.readLine()) != null) {
                buffeEscritura.write(line);
                buffeEscritura.newLine();
            }

            System.out.println("Conversión completa: " + encondingSalida);

        } catch(FileNotFoundException e) {
        	e.printStackTrace();
        }
        catch (IOException e) {
        
            e.printStackTrace();
        } finally {
            try {
                if (buffelectura != null) {
                    buffelectura.close();
                }
                if (buffeEscritura != null) {
                    buffeEscritura.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }	
	}
	public static void main(String[] args) {
		if(args.length==4) {
			// Rutas
			try {
				File rutaentrada= new File(args[0]);
				File rutasalida= new File(args[2]);
	
				if(comprobarParametroRuta(rutaentrada) && rutasalida.createNewFile() && comprobarParametroEncoding(args[1]) && comprobarParametroEncoding(args[3]) ) {
					traductor(args[0],args[2],args[1],args[3]);
				}else {
					System.out.println("No introdujo correctamente los parametros");
					System.out.println("bye...");
				}
			}catch(IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage()+" fichero no creado");
			}
			
		}else {
			System.out.println("Error no introdujo correctamente los 4 parámetros");
		}
	}

	
}
