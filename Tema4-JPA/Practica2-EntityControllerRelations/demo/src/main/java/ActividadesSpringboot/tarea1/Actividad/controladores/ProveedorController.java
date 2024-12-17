package ActividadesSpringboot.tarea1.Actividad.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import ActividadesSpringboot.tarea1.Actividad.entities.Proveedor;
import ActividadesSpringboot.tarea1.Actividad.repositories.ProveedorRepository;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Obtener todos los proveedores
    @GetMapping
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    // Obtener un proveedor por ID
    @GetMapping("/{id}")
    public Optional<Proveedor> getProveedorById(@PathVariable Long id) {
        return proveedorRepository.findById(id);
    }

    // Crear un proveedor
    @PostMapping
    public Proveedor createProveedor(@RequestBody Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    // Actualizar un proveedor
    @PutMapping("/{id}")
    public Proveedor updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        proveedor.setId(id);
        return proveedorRepository.save(proveedor);
    }

    // Eliminar un proveedor
    @DeleteMapping("/{id}")
    public void deleteProveedor(@PathVariable Long id) {
        proveedorRepository.deleteById(id);
    }
}
