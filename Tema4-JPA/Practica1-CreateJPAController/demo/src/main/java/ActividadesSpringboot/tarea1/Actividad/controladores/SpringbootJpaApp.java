package ActividadesSpringboot.tarea1.Actividad.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import ActividadesSpringboot.tarea1.Actividad.entities.Cliente;
import ActividadesSpringboot.tarea1.Actividad.repositories.ClientesRepository;


@SpringBootApplication
public class SpringbootJpaApp implements CommandLineRunner {
 
    @Autowired
    private ClientesRepository clientRepository;
    
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
        String nombre="Mario";
        String apellido="Pérez Garrido";
        
        System.out.println("######################## Todos los clientes PRUEBAS DE BÚSQUEDA ##########################");
        System.out.println("##########################################################################################");

        // LECTURAS DE BASE DE DATOS
        Optional<Cliente> OptNomApell = clientRepository.findByNombreAndApellido(nombre,apellido);
        System.out.println("Cliente por nombre y apellido: ");
        lecturaBDD(OptNomApell);
        //+++++++++
        Optional<Cliente> OptDni = clientRepository.findByDni("15443403F"); // bien
        System.out.println("Cliente por DNI: ");
        lecturaBDD(OptDni);
        //++++++++
        Optional<Cliente> OptDni_prueba = clientRepository.findByDni("11111111F"); // mal
        System.out.println("Cliente por DNI (mal): ");
        lecturaBDD(OptDni_prueba);
        //++++++++
        mostrarTodosClientes();
        System.out.println("##########################################################################################");
        System.out.println("Borrar un cliente");
        clientRepository.delete(cl2);  // Esto elimina el cliente cl2 
        mostrarTodosClientes();
    }
    public static Optional lecturaBDD(Optional busqueda){
        if(busqueda.isPresent()){
            System.out.println("Cliente encontrado: "+ busqueda.toString());
        } else {
            System.out.println("No se ha encontrado el cliente");
        }
        return busqueda;
    }
    public void mostrarTodosClientes(){
        List<Cliente> todo = clientRepository.findAll();
        System.out.println("####################### Todos los clientes ###############################");
        int contador= 0;
        for(Cliente c : todo){
            System.out.println("Cliente "+contador+" - "+c.toString());
            contador++;
        }
    }
}
