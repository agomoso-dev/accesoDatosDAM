package ficheros;

import java.io.RandomAccessFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FicherosAccesoAleatorioAvanzado {
    private File f;
    private List<String> campos;
    private List<Integer> camposLength; // V2
    private long longReg;       // Bytes por registro >>> LINEA
    private long numReg = 0;    // Número de registros dentro del fichero
    
    FicherosAccesoAleatorioAvanzado( String path, List<String> campos, List<Integer> camposLength ) throws IOException
    {
        this.campos = campos;
        this.camposLength = camposLength; /// V2
        
        this.f = new File( path );
        this.longReg = 0;
        
        for( Integer campo: camposLength ){
            System.out.print("*"+campo+"*");
            this.longReg += campo;
        }
        
        if( f.exists() ){
            this.numReg = f.length() / this.longReg;
        }
    }
    // Método 1
    public String selectCampo(int numRegistro, String nomColumna) {
    	//	El indice de la columna controlando si sale
    	int indiceDeColumna = campos.indexOf(nomColumna);
        if (indiceDeColumna == -1) {
            return null;
        }
        // La posicion del registro
        long posInicialRegistro = numRegistro * longReg;
        
        // Registrar la posición del registro
        long posInicialCampo = 0;
        for (int i = 0; i < indiceDeColumna; i++) {
            posInicialCampo += camposLength.get(i); 
        }

        //  Tamaño del campo
        int longCampo = camposLength.get(indiceDeColumna);

        RandomAccessFile rndFile =null;
        try {
        	rndFile=new RandomAccessFile(this.f, "r");
            // Posicionarnos en el archivo
            rndFile.seek(posInicialRegistro + posInicialCampo);
            
            // Crear un buffer para leer el campo
            byte[] buffer = new byte[longCampo];
            
            // Leer el campo
            rndFile.read(buffer, 0, longCampo);
            
            String valorCampo = new String(buffer, "UTF-8").trim(); 
            return valorCampo;
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return null;
    }
    // METODO 2
    public List<String> selectColumna(String nomColumna) {
        List<String> valoresColumna = new ArrayList<>();

        try {
            // Iterar sobre todos los registros del archivo
            for (int i = 0; i < this.numReg; i++) {
                // Usar selectCampo para obtener el valor de la columna en el registro actual
                String valorCampo = selectCampo(i, nomColumna);

                if (valorCampo != null) {
                    valoresColumna.add(valorCampo);
                } else {
                    System.out.println("Columna no encontrada en el registro " + i);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer la columna: " + e.getMessage());
            return null; // En caso de error, devolver null
        }

        return valoresColumna;
    }
    // METODO 3
    public List<String> selectRowList(int numRegistro) {
        List<String> valoresRegistro = new ArrayList<>();

        // Iterar sobre todos los campos para obtener los valores
        for (String campo : campos) {
            // Usar selectCampo para obtener el valor de cada campo en el registro
            String valorCampo = selectCampo(numRegistro, campo);
            valoresRegistro.add(valorCampo != null ? valorCampo : ""); // Agregar un valor vacío si el campo no se encuentra
        }

        return valoresRegistro;
    }
    // METODO 4
    public Map<String, String> selectRowMap(int numRegistro) {
        Map<String, String> valoresRegistro = new HashMap<>();

        // Iterar sobre todos los campos para obtener los valores
        for (String campo : campos) {
            // Usar selectCampo para obtener el valor de cada campo en el registro
            String valorCampo = selectCampo(numRegistro, campo);
            valoresRegistro.put(campo, valorCampo != null ? valorCampo : ""); // Agregar un valor vacío si el campo no se encuentra
        }

        return valoresRegistro;
    }
    // METODO 5
    public void update(int row, Map<String, String> reg) {
        // Verificamos que el registro existe
        if (row < 0 || row >= numReg) {
            System.out.println("Registro no existe.");
            return;
        }

        try (RandomAccessFile rndFile = new RandomAccessFile(this.f, "rws")) {
            // Posicionarnos en el archivo para el registro
            rndFile.seek(row * longReg);

            int total = campos.size();
            for (int i = 0; i < total; i++) {
                String nomCampo = campos.get(i);
                Integer longCampo = camposLength.get(i);

                // Valor nuevo del campo
                String valorCampo = reg.getOrDefault(nomCampo, null); // Usar null si no está en el map

                if (valorCampo == null) {
                    // Si no hay nuevo valor, le asignamos un espacio vacío
                    valorCampo = "";
                }

                // Formatear el campo para que tenga el tamaño correcto
                String valorCampoForm = String.format("%1$-" + longCampo + "s", valorCampo);

                // Escribir el nuevo valor en el archivo
                rndFile.write(valorCampoForm.getBytes("UTF-8"), 0, longCampo);
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar el registro: " + e.getMessage());
        }
    }
    // METODO 6
    public void delete(int row) {
        // Verificamos que el registro existe
        if (row < 0 || row >= numReg) {
            System.out.println("Registro no existe.");
            return;
        }

        try (RandomAccessFile rndFile = new RandomAccessFile(this.f, "rws")) {
            // Posicionarnos en el archivo para el registro
            rndFile.seek(row * longReg);

            // Limpiar todos los campos del registro
            for (int i = 0; i < campos.size(); i++) {
                Integer longCampo = camposLength.get(i);
                String valorCampoForm = String.format("%1$-" + longCampo + "s", "");
                rndFile.write(valorCampoForm.getBytes("UTF-8"), 0, longCampo);
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar el registro: " + e.getMessage());
        }
    }

    public void update(int row, String campo, String valor) {
        // Verificamos que el registro existe
        if (row < 0 || row >= numReg) {
            System.out.println("Registro no existe.");
            return;
        }

        // Buscamos el índice del campo a actualizar
        int indiceDeColumna = campos.indexOf(campo);
        if (indiceDeColumna == -1) {
            System.out.println("Campo no encontrado.");
            return;
        }

        try (RandomAccessFile rndFile = new RandomAccessFile(this.f, "rws")) {
            // Posicionarnos en el archivo para el registro
            rndFile.seek(row * longReg);

            // Tamaño del campo
            Integer longCampo = camposLength.get(indiceDeColumna);

            // Si el valor es nulo, asignamos una cadena vacía
            if (valor == null) {
                valor = "";
            }

            // Formatear el campo para que tenga el tamaño correcto
            String valorCampoForm = String.format("%1$-" + longCampo + "s", valor);

            // Posicionarnos en el campo correcto dentro del registro
            long posInicialCampo = 0;
            for (int i = 0; i < indiceDeColumna; i++) {
                posInicialCampo += camposLength.get(i);
            }

            // Escribir el nuevo valor en el archivo
            rndFile.seek(row * longReg + posInicialCampo);
            rndFile.write(valorCampoForm.getBytes("UTF-8"), 0, longCampo);
        } catch (IOException e) {
            System.out.println("Error al actualizar el campo: " + e.getMessage());
        }
    }

    public long getNumReg()
    {
        return numReg;
    }
    
    public void insertar( Map<String,String> reg ) throws IOException
    {
        insertar( reg, this.numReg++);        
    }
    
    public void insertar( Map<String,String> reg, long pos )
    {
        // ABRIR ARCHIVO BINARIO
        try( RandomAccessFile rndFile = new RandomAccessFile( this.f, "rws" ) ) {
            
            // POSICIONARNOS PARA ESCRIBIR
            rndFile.seek( pos * this.longReg );
            
            int total = campos.size();
            for( int i =0; i<total; i++ )
            // for( String campo : campos )
            {
                // Nombre Columna
                String nomCampo = campos.get(i);
                
                // Tamaño Columna
                Integer longCampo = camposLength.get(i);
                
                // VALOR Columna
                String valorCampo = reg.get(nomCampo);
                        
                if( valorCampo == null )
                {
                    valorCampo = "";
                }
                
                String valorCampoForm = String.format("%1$-" + longCampo + "s", valorCampo );
                
                System.out.print(valorCampoForm);
                
                rndFile.write(valorCampoForm.getBytes("UTF-8"),0, longCampo);
            }  
        }
        catch( Exception ex )
        {
        
        }
    }
    public static String opcion(Scanner sc) {
        System.out.println("\n--- Menú ---");
        System.out.println("1. Seleccionar campo");
        System.out.println("2. Seleccionar columna");
        System.out.println("3. Seleccionar fila como lista");
        System.out.println("4. Seleccionar fila como mapa");
        System.out.println("5. Actualizar registro");
        System.out.println("6. Eliminar registro");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        String opcion = sc.nextLine();
        return opcion;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FicherosAccesoAleatorioAvanzado fichero = null;
        try {
            // Definición de los campos y sus longitudes
            List<String> campos = new ArrayList<>();
            campos.add("DNI");
            campos.add("NOMBRE");
            campos.add("DIRECCION");
            campos.add("CP");
            List<Integer> camposLength = new ArrayList<>();
            camposLength.add(9);
            camposLength.add(32);
            camposLength.add(32);
            camposLength.add(5);
            
            // Creación del objeto FicherosAccesoAleatorioAvanzado
            fichero = new FicherosAccesoAleatorioAvanzado("file_binario_2.dat", campos, camposLength);
         // Insertar algunos registros de prueba
            Map<String, String> registro1 = new HashMap<>();
            registro1.put("DNI", "12345678A");
            registro1.put("NOMBRE", "Juan Pérez");
            registro1.put("DIRECCION", "Calle Mayor 1");
            registro1.put("CP", "28001");
            fichero.insertar(registro1);

            Map<String, String> registro2 = new HashMap<>();
            registro2.put("DNI", "98765432B");
            registro2.put("NOMBRE", "María García");
            registro2.put("DIRECCION", "Avenida Libertad 23");
            registro2.put("CP", "41001");
            fichero.insertar(registro2);
        } catch (IOException e) {
            System.out.println("Error al crear el fichero: " + e.getMessage());
            return;
        }

        while (true) {
            switch (opcion(sc)) {
                case "1":
                    System.out.print("Número de registro: ");
                    int numRegistro = Integer.parseInt(sc.nextLine());
                    System.out.print("Nombre de columna: ");
                    String nomColumna = sc.nextLine();
                    if (!fichero.campos.contains(nomColumna)) {
                        System.out.println("Error: La columna especificada no existe.");
                    } else {
                        String resultado = fichero.selectCampo(numRegistro, nomColumna);
                        if (resultado != null) {
                            System.out.println("Resultado: " + resultado);
                        } else {
                            System.out.println("No se pudo obtener el campo. Verifique el número de registro.");
                        }
                    }
                    break;
                case "2":
                	System.out.print("Nombre de columna: ");
                    String columnName = sc.nextLine();
                    if (!fichero.campos.contains(columnName)) {
                        System.out.println("Error: La columna especificada no existe.");
                    } else {
                        List<String> columnValues = fichero.selectColumna(columnName);
                        if (columnValues != null && !columnValues.isEmpty()) {
                            System.out.println("Valores de la columna " + columnName + ":");
                            for (int i = 0; i < columnValues.size(); i++) {
                                System.out.println("Registro " + i + ": " + columnValues.get(i));
                            }
                        } else {
                            System.out.println("No se pudo obtener los valores de la columna o la columna está vacía.");
                        }
                    }
                	break;
                case "3":
                    System.out.print("Número de registro: ");
                    numRegistro = sc.nextInt();
                    List<String> fila = fichero.selectRowList(numRegistro);
                    if (fila != null && !fila.isEmpty()) {
                        System.out.println("Resultado: " + fila);
                    } else {
                        System.out.println("No se pudo obtener la fila. Verifique el número de registro.");
                    }
                    sc.nextLine();
                    break;
                case "4":
                    System.out.print("Número de registro: ");
                    numRegistro = sc.nextInt();
                    Map<String, String> filaMap = fichero.selectRowMap(numRegistro);
                    if (filaMap != null && !filaMap.isEmpty()) {
                        System.out.println("Resultado: " + filaMap);
                    } else {
                        System.out.println("No se pudo obtener la fila. Verifique el número de registro.");
                    }
                    sc.nextLine();

                    break;
                case "5":
                    System.out.print("Número de registro a actualizar: ");
                    int row = sc.nextInt();
                    sc.nextLine();

                    System.out.print("¿Actualizar todo el registro (T) o un solo campo (C)? ");
                    String tipoActualizacion = sc.nextLine().toUpperCase();

                    if (tipoActualizacion.equals("T")) {
                        Map<String, String> nuevosDatos = new HashMap<>();
                        for (String campo : fichero.campos) {
                            System.out.print("Nuevo valor para " + campo + ": ");
                            String valor = sc.nextLine();
                            nuevosDatos.put(campo, valor);
                        }
                        fichero.update(row, nuevosDatos);
                        System.out.println("Registro actualizado.");
                    } else if (tipoActualizacion.equals("C")) {
                        System.out.print("Campo a actualizar: ");
                        String campo = sc.nextLine();
                        if (!fichero.campos.contains(campo)) {
                            System.out.println("Error: El campo especificado no existe.");
                        } else {
                            System.out.print("Nuevo valor: ");
                            String valor = sc.nextLine();
                            fichero.update(row, campo, valor);
                            System.out.println("Campo actualizado.");
                        }
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;
                case "6":
                    System.out.print("Número de registro a eliminar: ");
                    row = sc.nextInt();
                    if (row >= 0 && row < fichero.getNumReg()) {
                        fichero.delete(row);
                        System.out.println("Registro eliminado.");
                    } else {
                        System.out.println("Número de registro no válido.");
                    }
                    break;
                case "0":
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}