package ficheros;
import java.io.*;
import java.util.Scanner;

public class EscrituraSecuencialFicheros {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String opcion=""; 
		do {
			menu();
			System.out.print("Selecciona una opción: ");
			opcion = sc.nextLine();
			switch (opcion) {
			    case "1":
			        System.out.print("Introduce el nombre del fichero (se añadirá '.txt'): ");
			        String nombreFicheroOPC1 = sc.nextLine();
			        nombreFicheroOPC1+=".txt";
			        crearFichero(sc, nombreFicheroOPC1);
			        break;
			    case "2":
			        System.out.print("Introduce el nombre del fichero (sin extensión): ");
			        String nombreFicheroOPC2 = sc.nextLine() + ".txt";
			        añadirFinal(sc,nombreFicheroOPC2);
			        break;
			    case "3":
					System.out.print("Introduce el nombre del fichero (sin extensión): ");
					String nombreFichero = sc.nextLine() + ".txt";
			        añadirPrincipio(sc, nombreFichero);
			        break;
			    case "4":
			        System.out.println("Saliendo del programa...");
			    break;
			default:
			    System.out.println("Opción no válida, por favor intenta nuevamente.");
			}
		}while(!opcion.equalsIgnoreCase("4"));
}

	private static void menu() {
		System.out.println("\n--- MENÚ ---");
		System.out.println("1. Crear fichero y escribir contenido");
		System.out.println("2. Añadir texto al final del fichero");
		System.out.println("3. Añadir texto al principio del fichero");
		System.out.println("4. Salir");

	}

	// COMPROBAR FICHEROS SÍ EXISTEN
	public static boolean comprobarFicheros(File nombreFichero) {
		return nombreFichero.exists();
	}
	// 1. CREAR FICHERO
	public static void crearFichero(Scanner sc, String nombreFichero) {
		BufferedWriter escritura=null;
	    try {
	    	// Creación del buffer para poder escribir
			FileWriter lectura = new FileWriter(nombreFichero);
			escritura = new BufferedWriter(lectura); 
			// La forma de controlarlo es poniendo FIN
			System.out.println("Escribe el contenido del fichero. Escribe 'FINAL' para terminar:");
			String linea;
			while (!(linea = sc.nextLine()).equals("FINAL")) {
			    escritura.write(linea);
			    escritura.newLine();
			}
			System.out.println("Fichero '" + nombreFichero + "' creado con éxito.");
		} catch (IOException e) {
		    System.out.println("Error al crear el fichero.");
		        e.printStackTrace();
	    }finally {
		    try {
		        if (escritura != null) {
		            escritura.close();  // Cerrar el BufferedWriter
		        }
		    } catch (IOException e) {
		        e.printStackTrace(); 
		    }
		}
	}
	
	// 2. AÑADIR TEXTO AL FINAL DEL FICHERO
	public static void añadirFinal(Scanner sc, String nombreFichero) {
	    File fichero = new File(nombreFichero);
		if (comprobarFicheros(fichero)) {
			BufferedWriter escritura=null;
			try {
				// Uso el true, para poder agregar al final.
				FileWriter lectura = new FileWriter(fichero, true);
				escritura = new BufferedWriter(lectura);
			    System.out.println("Introduce el texto que deseas añadir al final del fichero. Escribe 'FIN' para terminar:");
				String linea;
				// EScribiremos una linea hasta que escriba fin
				while (!(linea = sc.nextLine()).equals("FIN")) {
				    escritura.write(linea);
				    escritura.newLine();
				}
				System.out.println("Texto añadido al final del fichero '" + nombreFichero + "' con éxito.");
			} catch (IOException e) {
			    System.out.println("Error al abrir el fichero.");
			            e.printStackTrace();
			}finally {
			    try {
			        if (escritura != null) {
			            escritura.close();  // Cerrar el BufferedWriter
			        }
			    } catch (IOException e) {
			        e.printStackTrace(); 
			    }
			}
			
		}else {
			System.out.println("NO EXISTE EL FICHERO");
		}
	}
	
	// 3. AÑADIR TEXTO AL PRINCIPIO DEL FICHERO
	public static void añadirPrincipio(Scanner sc, String nombreFichero) {
		File fichero = new File(nombreFichero);
		if(comprobarFicheros(fichero)) {
			BufferedReader lecturabuffer=null;
			BufferedWriter escriturabuffer=null;
			try {
			// Leer el contenido actual del fichero
				StringBuilder contenido = new StringBuilder();
				FileReader lectura = new FileReader(fichero); 
				lecturabuffer = new BufferedReader(lectura); 
			    String linea;
			    while ((linea = lecturabuffer.readLine()) != null) {
			        contenido.append(linea).append("\n");
			    }
				
				// Escribir el nuevo contenido al principio
			    FileWriter fw = new FileWriter(fichero); 
			    escriturabuffer = new BufferedWriter(fw); 
			    System.out.println("Introduce el texto que deseas añadir al principio del fichero. Escribe 'FIN' para terminar:");
				linea="";
				while (!(linea = sc.nextLine()).equals("FIN")) {
					escriturabuffer.write(linea);
					escriturabuffer.newLine();
				}
			
				// Añadir el contenido anterior después del nuevo texto
				escriturabuffer.write(contenido.toString());
				
				System.out.println("Texto añadido al principio del fichero '" + nombreFichero + "' con éxito.");
			} catch (IOException e) {
			    System.out.println("Error al abrir el fichero.");
			        e.printStackTrace();
			    }finally {
				    try {
				        if (escriturabuffer != null) {
				        	escriturabuffer.close();  // Cerrar el BufferedWriter
				        }
				        if(lecturabuffer != null) {
				        	lecturabuffer.close(); // Cerrar bufferreader
				        }
				    } catch (IOException e) {
				        e.printStackTrace(); 
				    }
				}
			}else {
				System.out.println("NO EXISTE EL FICHERO");
			}
		}
	
}
