package ActividadesSpringboot.tarea1.Actividad;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import ActividadesSpringboot.tarea1.Actividad.entities.Cliente;
import ActividadesSpringboot.tarea1.Actividad.entities.Producto;
import ActividadesSpringboot.tarea1.Actividad.entities.Proveedor;
import ActividadesSpringboot.tarea1.Actividad.repositories.ClientesRepository;
import ActividadesSpringboot.tarea1.Actividad.repositories.ProductoRepository;
import ActividadesSpringboot.tarea1.Actividad.repositories.ProveedorRepository;


@SpringBootApplication
public class SpringbootJpaApp implements CommandLineRunner {
 
    @Autowired
    private ClientesRepository clientRepository;
    @Autowired
    private ProductoRepository  proRepository;
    @Autowired
    private ProveedorRepository pRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApp.class, args);
        }
        
        @Override
        public void run(String... args) throws Exception {
        funcionPruebas1();
        }
        
        @Transactional
        public void funcionPruebas1() {
                
                // ESCRITURA EN BBDD
        // Productos y proveedores
        Proveedor p1 = new Proveedor("P001", "Producto 1", "622809673","CAlle jugarro");
            
        // Constructor con dos parámetros
        Cliente cl1 = new Cliente("15443403F", "Antonio Jesús");
        // Constructor con todos los parámetros
        Cliente cl2 = new Cliente("71243403A", "José", "Martínez", "Calle de la Cruz 12, Sevilla", "666555444");          
        
        // Guardar en la BBDD cliente por cliente
        clientRepository.save(cl1);
        clientRepository.save(cl2);

        // Guardar varios clientes a la vez
        Cliente cl3 = new Cliente("15411123G", "Mario", "Pérez Garrido", "Calle del Puente 15, Granada", "666777888");
        Cliente cl4 = new Cliente("82121233B", "Ubango", "Jimenez Ama", "Calle Hoyo del boyero, Arcos", "666555444");   
      
        clientRepository.saveAll(List.of(cl3, cl4));
        
        Proveedor p2 = new Proveedor("P002", "Producto 2", "622809673","Calle jugarro");
        pRepository.save(p2);
        // Productos y Clientes
        Producto producto_1 = new Producto("P001", "Producto 1", "magico magico magic",10.0, p2);
        Producto producto_2 = new Producto("P002", "Producto 2", "vainilla vainilla vaina",5.0, p2);
        proRepository.saveAll(List.of(producto_1, producto_2));
            // Asociar productos con clientes
        cl1.agregarProducto(producto_1);
        cl1.agregarProducto(producto_2);
            // ACTUALIZAR DIRECCIÓN DE UN CLIENTE POR DNI
    System.out.println("Actualizando la dirección del cliente con DNI 15443403F...");
    int direccionActu = clientRepository.updateDireccionByDni("Calle Nueva, Madrid", "15443403F");
    if (direccionActu > 0) {
        System.out.println("Dirección actualizada correctamente.");
    } else {
        System.out.println("No se encontró el cliente para actualizar.");
    }

    // VERIFICAR ACTUALIZACIÓN
    Optional<Cliente> clienteActualizado = clientRepository.findById(1L); 
    clienteActualizado.ifPresent(cliente -> System.out.println("Cliente actualizado: " + cliente));

    // ELIMINAR CLIENTES POR APELLIDO
    System.out.println("Eliminando clientes con apellido 'Martínez'...");
    clientRepository.deleteByApellido("Martínez");

    // VERIFICAR ELIMINACIÓN
    Optional<Cliente> clienteEliminado = clientRepository.findByNombreAndApellido("José", "Martínez");	
    if (clienteEliminado.isEmpty()) {
        System.out.println("Cliente eliminado correctamente.");
    } else {
        System.out.println("El cliente aún existe en la base de datos.");
    }

    }
}
