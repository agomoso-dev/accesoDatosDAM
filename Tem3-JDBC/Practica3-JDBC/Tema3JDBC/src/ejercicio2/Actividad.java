package ejercicio2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * AUTOR: ANTONIO JESÚS GÓMEZ OSORIO
 * 2º DAM
 */
public class Actividad {
	// ESTABLECER CONEXIÓN
	private static final String URL = "jdbc:mysql://localhost:3306/ventas";
	private static Connection conn = null;

	// CONECTANDO A LA BASE DE DATOS
	public static boolean conectado() {
		boolean conectado = false;
		System.out.println("Conectando a la base de datos");
		try {
			conn = DriverManager.getConnection(URL,"root","");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conectado;
	}

    // CERRAR LA BASE DE DATOS
	public static void cerrar() {
        try {
            System.out.print("Cerrando la conexión...");
            conn.close();
            System.out.println("OK!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static boolean insertarLicencias(String DNI, String direccion, String cp, String nombre, List<List<Object>> licencias) {
        // Ambas querys
    	String usuarioQuery = "INSERT INTO usuarios (dni, direccion, cp, nombre) VALUES (?, ?, ?, ?)";
        String licenciaQuery = "INSERT INTO licencias (tipo, expedicion, caducidad, usuario_id) VALUES (?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false); // Lo desactivamos para que sea en bloque 

            PreparedStatement usuarioState = conn.prepareStatement(usuarioQuery);
            PreparedStatement licenciaState = conn.prepareStatement(licenciaQuery);
            // Indicar todas los campos asignadose a la query
            usuarioState.setString(1, DNI);
            usuarioState.setString(2, direccion);
            usuarioState.setString(3, cp);
            usuarioState.setString(4, nombre);
            usuarioState.executeUpdate();

            for (List<Object> licencia : licencias) {
            	// Recorremos cada licencia y la vamos a introduciendo
                licenciaState.setString(1, (String) licencia.get(0));
                licenciaState.setTimestamp(2, Timestamp.valueOf((String) licencia.get(1))); 
                licenciaState.setTimestamp(3, Timestamp.valueOf((String) licencia.get(2))); 
                licenciaState.setString(4, DNI);
                licenciaState.executeUpdate();
            }

            conn.commit(); // está marca el final de la sentencia en las tablas relacionales
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback(); // usamos el rollback para que la integridad de los datos sea correcta
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static boolean eliminarLicencias(String DNI) {
        String eliminarLicenciasQuery = "DELETE FROM licencias WHERE usuario_id = ?";
        String eliminarUsuarioQuery = "DELETE FROM usuarios WHERE dni = ?";

        try {
            PreparedStatement elilicenState = conn.prepareStatement(eliminarLicenciasQuery);
            PreparedStatement eliusuaState = conn.prepareStatement(eliminarUsuarioQuery);
            // Eliminar primero las licencias y luego los usuarios para que no haya conflicto, ya que la primary Key esta en el usuario.
            elilicenState.setString(1, DNI);
            elilicenState.executeUpdate();

            eliusuaState.setString(1, DNI);
            eliusuaState.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void mostrarTodo() {
        String query = "SELECT * FROM usuarios,licencias WHERE usuarios.dni = licencias.usuario_id";

        try {
            Statement State = conn.createStatement();
            ResultSet result = State.executeQuery(query);

            while (result.next()) {
                String dni = result.getString("dni");
                String direccion = result.getString("direccion");
                int cp = result.getInt("cp");
                String nombre = result.getString("nombre");
                String tipo = result.getString("tipo");
                Timestamp expedicion = result.getTimestamp("expedicion");
                Timestamp caducidad = result.getTimestamp("caducidad");

                System.out.printf("DNI: %s, Dirección: %s, CP: %d, Nombre: %s, Tipo Licencia: %s, Expedición: %s, Caducidad: %s%n",
                                  dni, direccion, cp, nombre, tipo, expedicion, caducidad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        conectado();
        // Insertar un usuario con licencias
        List<List<Object>> licencias = new ArrayList<>();
        licencias.add(Arrays.asList("B1", "2025-02-02 10:02:00", "2027-01-01 20:00:00"));
        licencias.add(Arrays.asList("D1", "2022-05-15 00:00:00", "2030-05-15 00:00:00"));

        if (insertarLicencias("15443402k", "Calle Magica, 32", "41001", "Juan Manuel", licencias)) {
            System.out.println("Usuario y licencias insertados");
        } else {
            System.out.println("Error al insertar usuario y licencias.");
        }
        System.out.println("Antes de borrar y agregar");
        mostrarTodo();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ ###########################################");
        System.out.println("Mostrando todos los datos:");
        eliminarLicencias("12345678A");
        mostrarTodo();
        
        cerrar();
    }
 
}
