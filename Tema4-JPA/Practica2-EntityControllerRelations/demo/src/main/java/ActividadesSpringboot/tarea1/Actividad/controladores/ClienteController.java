package ActividadesSpringboot.tarea1.Actividad.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import ActividadesSpringboot.tarea1.Actividad.entities.Cliente;
import ActividadesSpringboot.tarea1.Actividad.repositories.ClientesRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClientesRepository clientesRepository;

    // Obtener todos los clientes
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clientesRepository.findAll();
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public Optional<Cliente> getClienteById(@PathVariable Long id) {
        return clientesRepository.findById(id);
    }

    // Crear un cliente
    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clientesRepository.save(cliente);
    }

    // Actualizar un cliente
    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clientesRepository.save(cliente);
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clientesRepository.deleteById(id);
    }

    // Consulta personalizada: Obtener clientes por nombre
    @GetMapping("/nombre/{nombre}")
    public List<Cliente> findByName(@PathVariable String nombre) {
        return clientesRepository.findByName(nombre);
    }
}
