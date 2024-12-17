package ficheros;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/*
 *AUTOR: ANTONIO JESÚS GÓMEZ OSORIO*
 *TEMA 1 - ACCESO A DATOS
 *PROGRAMA: TAREA 2 | CREACIÓN Y BORRADO
 *USO: FILE - FICHEROS/DIRECTORIOS
 *FECHA: 20/09/2024
 *JAVA VERSIÓN: 1.8
 */
public class CreacionBorradoFicheros {
	public static void crearestructura(String[][] rutasDirectorioFicheros) {
		
		// Recorrido del array bidimensional.
        for(String[] recorrido: rutasDirectorioFicheros) { 
        	// Indicar la ruta relativa para que se cree directamente en el propio proyecto.
        	File rutaDirectorio= new File (recorrido[0]+"\\"+recorrido[1]);
        	// Sí ya existe no será necesario crear. 
        	if(!rutaDirectorio.exists()) {
        		// Usamos mkdirs en vez de mkdir para crear la ruta completa. Ya que mkdir necesita que el parent exista.
        		rutaDirectorio.mkdirs();
        	}
        	// Creación de ficheros
        	File rutaFichero= new File (rutaDirectorio+"\\"+recorrido[2]);
    		try {
    			// Comprobar sí existe dicho fichero o no
	    		if(!rutaFichero.exists()) {
	    			// Si no existe pues lo creará
					rutaFichero.createNewFile();
	    		}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error al crear el fichero");
			}
    		
        }
        System.out.println("Arbol de todo los ficheros y directorios correctamente");
        System.out.println("######################################################");
	}
	public static void eliminadorecursiv() {
		Scanner sc=new Scanner(System.in);
		String rutaAeliminar="";
		// El do:while es para que repita la acción de borrado hasta que el usario quiera parar. Salir = "Exit"
		do {
			// Pedir por pantalla
			System.out.println("Introduce una ruta(Introduce ruta absoluta) / Sí desea salir escriba - exit -"); 
			rutaAeliminar=sc.nextLine();
			File rutaBorrar=new File(rutaAeliminar); 
			// Ruta que va ser eliminada
			if(rutaBorrar.exists()) {
				System.out.println("Existe la ruta, vamos a borrar");
				System.out.println("##############################");
				// Comprobar si es un fichero o directorio. Ya que sí es un directorio deberá eliminar todo su contenido antes
				if(rutaBorrar.isFile()) {
					// Borrado del fichero si fue posible o no
					if(borrarFichero(rutaBorrar)) {
						System.out.println("Fue borrado correctamente el fichero");
					}else {
						System.out.println("No fue borrado correctamente el fichero");
					}
				}	
				else if(rutaBorrar.isDirectory()) {
					// Si es un directorio, Borrará todo su contenido uno por uno y además todo los subdirectorios y ficheros, que cuelguen del mismo.
					File[] ficheros=rutaBorrar.listFiles();
					for(File dir: ficheros) {
						if(dir.isFile()) {
							dir.delete();
						}else if(dir.isDirectory()) {
							borrarDiretorio(rutaBorrar);
						}
					}
				}
			}else if(rutaAeliminar.equalsIgnoreCase("exit")) {
				// Saliendo
				System.out.println("Saliendo...");
			}
			else{
				// En caso de no introducirse bien la ruta que la vuelva a pedir.
				System.out.println("No existe el fichero o directorio que desea borrar, vuelve a intentarlo");
			}
		}while(!rutaAeliminar.equalsIgnoreCase("exit"));
		
		
	}
	public static boolean borrarFichero(File rutaBorrar) {
		// Borrado de fichero si se encuentra el fichero automaticamente
		boolean verdad=false;
		if(rutaBorrar.delete()) {
			verdad=true;
		}
		return verdad;
	}
	public static void borrarDiretorio(File rutaBorrar) {
		// Se recorre todos los directorios y ficheros que existan dentro de la ruta y automaticamente lo borra al completa de forma recursiva
		File[] ficheros=rutaBorrar.listFiles(); // Aquí listamos los ficheros/directorios
		for(File dir: ficheros) {	
			if(dir.isFile()) {
				// Si es un fichero lo borra
				dir.delete();
			}else if(dir.isDirectory()) {
				// Si es un directorio lo borra
				if(!dir.delete()) {
					borrarDiretorio(dir);
				}
			}
		}
	}

	public static void main(String[] args) {
		// Una Array Bidimensional con un 3x8 donde tenemos escrita cada ruta de cada fichero
        String[][] rutasDirectorioFicheros = {
                {"Abuelo", "Padre", "Hijo1.txt"},
                {"Abuelo", "Padre", "Hija2.txt"},
                {"Abuelo", "Madre", "Hijo3.txt"},
                {"Abuelo", "Madre", "Hija4.txt"},
                {"Abuela", "Padre", "Hijo5.txt"},
                {"Abuela", "Padre", "Hija6.txt"},
                {"Abuela", "Madre", "Hijo7.txt"},
                {"Abuela", "Madre", "Hija8.txt"}
        };
        // Programa
        crearestructura(rutasDirectorioFicheros);
        eliminadorecursiv();
        
	}
}
