package ActividadesSpringboot.tarea1.Actividad;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ActividadesSpringboot.tarea1.Actividad.controladores.ClienteController;
import ActividadesSpringboot.tarea1.Actividad.controladores.ProductoController;
import ActividadesSpringboot.tarea1.Actividad.controladores.ProveedorController;
import ActividadesSpringboot.tarea1.Actividad.entities.Cliente;
import ActividadesSpringboot.tarea1.Actividad.entities.Producto;
import ActividadesSpringboot.tarea1.Actividad.entities.Proveedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class Tarea1Application implements CommandLineRunner {
    @PersistenceContext
    private EntityManager pruebas;

    // He agregado está interfaz para realizar las pruebas respecto a los controller
	// ES un framework Spring que proporciona info de la configuración .
	@Autowired
    private ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		SpringApplication.run(Tarea1Application.class, args);
	}

	@Override
	@Transactional

	public void run(String... args) throws Exception {
		// Crear datos de prueba
		Cliente cliente_1 = new Cliente("12345678A", "Juan", "Pérez", "Calle Mayor 1", "123456789", new SimpleDateFormat("yyyy-MM-dd").parse("2027-10-01"));
		Cliente cliente_2 = new Cliente("87654321B", "María", "García", "Avenida Principal 2", "987654321", new SimpleDateFormat("yyyy-MM-dd").parse("2025-02-06"));
		Cliente cliente_3 = new Cliente("11223344C", "Carlos", "López", "Plaza Central 3", "555666777", new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-15"));

		pruebas.persist(cliente_1);
		pruebas.persist(cliente_2);
		pruebas.persist(cliente_3);

		Proveedor proveedor_1 = new Proveedor("PROV1", "Proveedor Uno", "111222333", "Calle Proveedores 1");
		Proveedor proveedor_2 = new Proveedor("PROV2", "Proveedor Dos", "444555666", "Avenida Suministros 2");

		pruebas.persist(proveedor_1);
		pruebas.persist(proveedor_2);

		Producto producto_1 = new Producto("PRD01", "Producto 1", "Descripción del producto 1", 19.99, proveedor_1);
		Producto producto_2 = new Producto("PRD02", "Producto 2", "Descripción del producto 2", 29.99, proveedor_1);
		Producto producto_3 = new Producto("PRD03", "Producto 3", "Descripción del producto 3", 39.99, proveedor_2);

		pruebas.persist(producto_1);
		pruebas.persist(producto_2);
		pruebas.persist(producto_3);
		System.out.println("Datos de prueba creados correctamente.");
		// Ahora a realizar peticiones
		// Realizar las pruebas
		// En todos tenemos pruebas de las cosas ya sean Fecha, Ordenamiento,etc.
		pruebasFechas();
		pruebasProductos();
		pruebasProveedores();
	}

	private void pruebasFechas() {
		ClienteController clienteController = applicationContext.getBean(ClienteController.class);
		Date fechaPrueba;
		try {
			// Fecha que va filtrar
			fechaPrueba = new SimpleDateFormat("yyyy-MM-dd").parse("2026-01-01");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Fecha de prueba: " + new SimpleDateFormat("yyyy-MM-dd").format(fechaPrueba));

		List<Cliente> clientes = clienteController.busqueda(
			null,  // nombre
			null,  // apellido
			null,  // dni
			null,  // direccion
			null,  // telefono
			null,  // proveedorId
			fechaPrueba,  // fechaCompraDesde
			null,  // Compra Reciente
			null   // ordenarPor
		);
		if (clientes.isEmpty()) {
			System.out.println("No se encontraron clientes " + new SimpleDateFormat("yyyy-MM-dd").format(fechaPrueba));
		} else {
			System.out.println("Clientes encontrados con fecha de compra a " + new SimpleDateFormat("yyyy-MM-dd").format(fechaPrueba) + ":");
			clientes.forEach(cliente -> {
				System.out.println("Cliente: " + cliente.getNombre() + " " + cliente.getApellido() + 
								", Fecha de compra: " + new SimpleDateFormat("yyyy-MM-dd").format(cliente.getFechaCompra()));
			});
		}
	}
    private void pruebasProductos() {
        ProductoController productoController = applicationContext.getBean(ProductoController.class);
        
        System.out.println("\n--- Prueba de búsqueda de productos ---");
        List<Producto> productos = productoController.busqueda(
            "Producto",  // nombre
            null,  // descripcion
            null,  // precioMinimo
            20.0,  // precioMaximo
            null,  // proveedorId
            "nombre"  // ordenarPor
        );

        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos");
        } else {
            System.out.println("Productos encontrados:");
            productos.forEach(producto -> {
                System.out.println("Producto: " + producto.getNombre() + 
                                   ", Código: " + producto.getCodigo() + 
                                   ", Precio: " + producto.getPrecio());
            });
        }
    }

    private void pruebasProveedores() {
        ProveedorController proveedorController = applicationContext.getBean(ProveedorController.class);
        
        System.out.println("\n--- Prueba de búsqueda de proveedores ---");
		List<Proveedor> proveedores = proveedorController.busqueda(
			"Proveedor",  // nombre
			null,         // direccion
			null,         // telefono
			"nombre"      // ordenarPor
		);
        if (proveedores.isEmpty()) {
            System.out.println("No se encontraron proveedores");
        } else {
            System.out.println("Proveedores encontrados:");
            proveedores.forEach(proveedor -> {
                System.out.println("Proveedor: " + proveedor.getNombre() + 
                                   ", Código: " + proveedor.getCodigoProveedor() + 
                                   ", Teléfono: " + proveedor.getTelefono());
            });
        }
    }
}
