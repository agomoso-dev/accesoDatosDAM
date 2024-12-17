package ejercicio3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * AUTOR: ANTONIO JESÚS GÓMEZ OSORIO
 * 2º DAM
 */
public class Actividad {
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
	/*
	 *  1- Método string selectCampo( int numRegistro, string nomColumna), devuelve el campo 
	 *  correspondiente a la columna de nombre nomColumna del registro indicado en numRegistro.
	 */
    public static String selectCampo(int numRegistro, String nomColumna) {
        String resultado = null;
        String query = "SELECT " + nomColumna + " FROM productos LIMIT 1 OFFSET ?";
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            conn.setAutoCommit(false); // commit INiciado a False

            state.setInt(1, numRegistro - 1);	// Establece la query
            result = state.executeQuery();	// Ejecuta la consulta.
            if (result.next()) {
                // Sí se consiguió devolverá correctamente el registro 
                resultado = result.getString(nomColumna);
            }

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback(); // Rollback
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
        	// Cerrar tanto el rs como el state(siempre)
            try {
            	
                if (result != null) {
                    result.close();
                }
                if (state != null) {
                    state.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }
    /*
     * 2) Método List<String> selectColumna( string nomColumna ) Devuelve una lista
     * con TODOS los valores del campo buscado. (SELECT columna FROM fichero).
     */
    public static List<String> selectColumna(String nomColumna) {
        List<String> resultados = new ArrayList<>();
        String query = "SELECT " + nomColumna + " FROM productos";
        Statement state = null;
        ResultSet result = null;
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            conn.setAutoCommit(false); // Commit establecido a false

            result = state.executeQuery(query);
            while (result.next()) {
	        	// Aquí carga la columna concretamnete que le pasamos por parámetro.
                resultados.add(result.getString(nomColumna));
            }

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback(); // RollBack
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
            	// Cerrar tanto el rs como el state(siempre)
                if (result != null) {
                    result.close();
                }
                if (state != null) {
                    state.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultados;
    }
    /*
     * 3) Método List<String> selectRowList( in numRegistro ) Devuelve una lista con los 
     * datos del registro de la posición numRegistro. (SELECT CAMPOS FROM fichero WHERE ... )

     */
    public static List<String> selectRowList(int numRegistro) {
        List<String> resultado = new ArrayList<>();
        String query = "SELECT * FROM productos LIMIT 1 OFFSET ?";
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            conn.setAutoCommit(false); // Commit establecido a false

            state.setInt(1, numRegistro - 1);
            result = state.executeQuery();
            if (result.next()) {
                String id = result.getString("id");
                String nombre = result.getString("nombre");
                double precio = result.getDouble("precio");
                int cantidad = result.getInt("cantidad");
                
                // Agregamos los valores al ArrayList
                resultado.add(id);
                resultado.add(nombre);
                resultado.add(String.valueOf(precio));
                resultado.add(String.valueOf(cantidad));
            }

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback(); // RollBack
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
            	// Cerrar tanto el rs como el state(siempre)
                if (result != null) {
                    result.close();
                }
                if (state != null) {
                    state.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }
    /*
     * 4) Método Map selectRowMap( in numRegistro ) Igual resultado que el anterior pero en una clase HashMap.
     */
    public static Map<String, String> selectRowMap(int numRegistro) {
        Map<String, String> resultado = new HashMap<>();
        String query = "SELECT * FROM productos LIMIT 1 OFFSET ?";
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            state = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            conn.setAutoCommit(false); // Commit establecido a false

            state.setInt(1, numRegistro - 1);
            result = state.executeQuery();

            if (result.next()) {
                // Tendremos las columnas por sus nombres al igual que antes
                String id = result.getString("id");
                String nombre = result.getString("nombre");
                String precio = String.valueOf(result.getDouble("precio"));
                String cantidad = String.valueOf(result.getInt("cantidad"));
                
                // Agregamos los resultados al Map con las claves como el nombre de las columnas
                resultado.put("id", id);
                resultado.put("nombre", nombre);
                resultado.put("precio", precio);
                resultado.put("cantidad", cantidad);
            }

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback(); // RollBAck
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
            	// Cerrar tanto el rs como el state(siempre)
                if (result != null) {
                    result.close();
                }
                if (state != null) {
                    state.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }
	/*
	 * 5.1) Método update( int row, Map ) Modifica en el fichero todos los campos para el 
	 * registro indicado en row que se reciben en un Map<string campo,string valor>

	 */
    public static void update(int row, Map<String, String> valores) {
        String query = "UPDATE productos SET nombre = ?, precio = ?, cantidad = ? WHERE id = ?";
        PreparedStatement state = null;
        try {
            conn.setAutoCommit(false); // Commit establecido a false

            state = conn.prepareStatement(query);
            state.setString(1, valores.get("nombre"));
            state.setString(2, valores.get("precio"));
            state.setString(3, valores.get("cantidad"));
            
            // ID que va identificar cual va ser cambiado
            state.setInt(4, row);
            
            // Actualizar
            state.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback(); // RollBack
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
            	// Cerrar tanto el rs como el state(siempre)
                if (state != null) {
                    state.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /*
     * 5.2) Método update( int row, string campo, string valor ) Modifica SOLO el valor del campo "campo" para el registro indicado en row.
     */
    public static void update2(int row, String campo, String valor) {
        String query = "UPDATE productos SET " + campo + " = ? WHERE id = ?";
        PreparedStatement state = null;
        try {
            conn.setAutoCommit(false); // Commit establecido a false

            state = conn.prepareStatement(query);
            state.setString(1, valor); // Es lo mismo que el anterior pero usando un campo especifico, aquí le pasamos el valor que vamos asignarle
            state.setInt(2, row);

            state.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback(); // RollBack
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
            	// Cerrar tanto el rs como el state(siempre)
                if (state != null) {
                    state.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /*
     * 6) Método delete( int row ) limpia los datos del registro indicado.
     */
    public static void delete(int row) {
        String query = "DELETE FROM productos WHERE id = ?";
        PreparedStatement state = null;
        try {
            conn.setAutoCommit(false); // Commit establecido a false

            state = conn.prepareStatement(query);
            state.setInt(1, row);

            state.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback(); // Rollback
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
            	// Cerrar tanto el rs como el state(siempre)
                if (state != null) {
                    state.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void mostrarTodo() {
        String query = "SELECT * FROM productos";
        Statement state = null;
        ResultSet result = null;

        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            conn.setAutoCommit(false); // Commit establecido a false

            result = state.executeQuery(query);

            while (result.next()) {
                System.out.print(result.getInt(1) + "\t");
                System.out.print(result.getString(2) + "\t");
                System.out.print(result.getDouble(3) + "\t");
                System.out.print(result.getInt(4) + "\t");
                System.out.println();
            }

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback(); // RollBack
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (state != null) {
                    state.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	public static void main(String[] args) {
		conectado();
		// Pruebas
		System.out.println("Mostrar antes de empezar");
		mostrarTodo();
		System.out.println("Metodo 1");
        System.out.println("- "+selectCampo(1, "nombre"));
        
        System.out.println("Metodo 2");
        System.out.println("- "+selectColumna("cantidad"));
        
        System.out.println("Metodo 3");
        System.out.println(selectRowList(2));
        
        System.out.println("Metodo 4");
        System.out.println(selectRowMap(1));
        
        System.out.println("Metodo 5.1");
        Map<String, String> valores = new HashMap<>();
        valores.put("nombre", "Producto Actualizado");
        valores.put("precio", "100");
        valores.put("cantidad", "12");
        int row = 1; 
        update(row, valores);
        System.out.println(selectRowList(1));
        System.out.println("Metodo 5.2");
        update2(1, "precio", "150.00"); // Aquí solo actualiza el campo precio
        System.out.println(selectRowList(1)); 
        
        System.out.println("Metodo 6");
        delete(3);
		System.out.println("Mostrar final");
		mostrarTodo();
		cerrar();
	}
}
