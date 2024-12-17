package ficheros;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class LecturaFicherosSecuencial {
	public static void recorreFicheros(File ficheros[], String ruta) {
		// Funcion de recorrer el fichero
        for( File f: ficheros )
        {          
        	System.out.println("\n"+"Ruta Absoluta "+f.getAbsolutePath());
            System.out.print(cadenaTextoTipoPermisos(f) + " " + f.getName() );
            System.out.println(recorrerSubdirectorios(f));
            if(f.isFile()) {
            	// Presentación de ambas opciones filereader y buffereader
				System.out.println("Caracteres [a,e,i,o,u]- Cantidad(FileReader): "+filereader(f));
				System.out.println("Caracteres [a,e,i,o,u]- Cantidad(FileReader): "+bufferedreader(f));
            }
        }
	}
	//Funciones FileReader/BufferedReader
	public static int filereader(File f) {
		// Contador de vocales
		int contadorvocales=0;
		FileReader fichero=null;
		try {
			fichero= new FileReader(f);
			// Utilizando FileReader
			int letra = fichero.read();
			while( letra != -1 )
			{
			    // CÓDIGO AQUÍ ARRIBA
				// Aquí pasamos la función con la que contamos
				contadorvocales+=contador(letra);
				
				// LEEMOS LO ÚLTIMO
			    letra = fichero.read();
			}
		 }catch (IOException e) {
			e.printStackTrace();
		 }finally {
			// Finally para cerrar el fichero correctamente o en casa de haya un error avise
            try {
                if (fichero != null) {
                	fichero.close();                
                }
            } catch (IOException e) {
                System.out.println("Ocurrió un error inesperado al cerrar el fichero.");
                e.printStackTrace();
            }
        }
		return contadorvocales;


	}
	public static int bufferedreader(File f) {
		int contador1=0;
		FileReader fichero = null;
		try {
			fichero = new FileReader(f);
			BufferedReader brf = new BufferedReader( fichero );
			// Utilizando BufferedReader
			String line = brf.readLine();
			while( line != null ) {
				// CÓDIGO AQUÍ ARRIBA
				char[] linea=line.toCharArray();
				for(int o:linea) {
					contador1+=contador(o);
				}
				// LEEMOS LO ÚLTIMO
				line = brf.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// Finally para cerrar el fichero correctamente o en casa de haya un error avise
            try {
                if (fichero != null) {
                	fichero.close();               
                }
            } catch (IOException e) {
                System.out.println("Ocurrió un error inesperado al cerrar el fichero.");
                e.printStackTrace();
            }
        }
		return contador1;
	}
	public static int contador(int letra) {
		// Iremos agregando 1 o 0 dependiendo de si es una letra
		// Determinado todo por la tabla Ascii que nos indica a que número equivale
		int vocal=0;
		switch(letra) {
			// Letra -> A
			case 65:
			case 97:
			// Letra -> E
			case 69:
			case 101:
			// Letra -> I
			case 73:
			case 105:
			// Letra -> O
			case 79:
			case 111:
			// Letra -> U
			case 85:
			case 117:
				vocal=1;
		}
		return vocal;
	}
	//Funciones sobre los permisos del fichero
	public static boolean esejecuable(File f) {		
		return f.canExecute();
	}
	public static boolean esleible(File f) {
		return f.canRead();
	}
	public static boolean escribir(File f) {
		return f.canWrite();
	}
	//Funciones información extra
	public static String cantidadficheros(File f) {
		// Calcula simplemente los fichero que hay en cada directorio
		String cantidad="-";
		int contador=0;
		for(int i=0;i<f.listFiles().length;i++) {
			contador++;
		}
		if(contador>0) {
			cantidad=Integer.toString(contador);
		}
		return cantidad;
	}
	public static boolean oculto(File f) {
		// Si es visible o no el fichero
		return f.isHidden();
	}
	public static String recorrerSubdirectorios(File f) {
		//Recorrer los subdirectorios que hay en la ruta actual.
		String texto="";
		// Comprobación de sí son directorios
		if(f.isDirectory()) {
        	System.out.println("\n"+"\t"+"Cantidad de ficheros "+cantidadficheros(f)); 
			File[] listDirectory = f.listFiles();
			// Cada fichero será mostrado con todos sus datos correspondientes
			if(listDirectory!=null) {
				for(File d: listDirectory) {
					texto+="\n"+"\t"+cadenaTextoTipoPermisos(d) + " " + d.getName();
				}
			}
		}
		return texto;
	}
	public static String cadenaTextoTipoPermisos(File f) {
		// Si es oculto tendrá un punto al principio para que sea identificativo
		String texto= oculto(f) ? ".":" ";
		texto+= f.isDirectory() ? "/" : f.isFile() ? "_":"?";
		texto+= esejecuable(f) ? "x" :"-";
		texto+= esleible(f) ? "r" :"-";
		texto+= escribir(f) ? "w" :"-";

        return texto+" "+espacioTotal(f)+" "+fecha(f);
	}
	public static String espacioTotal(File f) {
		// El tamaño de cada fichero en KB, donde primero almacenamos el tamaño long(es ilegible)
	    long kb = f.length();
	    // Así que realizamos una conversación
	    double tamano = kb / 1024;  
	    return String.format("%.2f KB", tamano);
	}
	public static Date fecha(File f) {
		// Generar fecha y conversión de la misma.
		Date fecha = new Date(f.lastModified());
		return fecha;
	}
	public static void main(String[] args)
    {  
		try {
	        String ruta = ".\\prueba";
	        if( args.length > 0 ) ruta = args[0];
	        
	        File fich = new File( ruta );
	        
	        if( !fich.exists() )
	        {
	            System.out.println("No existe el fichero o directorio "  + ruta );
	        }
	        else
	        {
	            if( fich.isFile() )
	            {
	                System.out.println(ruta+" es un fichero.");
	            }
	            else
	            {
	        		System.out.println(" " + ruta + " es un directorio. Contenidos:");
	            	System.out.println("Cantidad de ficheros "+cantidadficheros(fich)); 
	                File[] ficheros = fich.listFiles();
	                recorreFicheros(ficheros, ruta);
	            }
	        }
		}catch(SecurityException e) {
			e.printStackTrace();
			System.out.println("Error al acceder al directorio o fichero");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error. Contacte con el administrador.");
		}
    }   
}
