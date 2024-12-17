package ActividadesSpringboot.tarea1.Actividad.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import ActividadesSpringboot.tarea1.Actividad.entities.Producto;
import ActividadesSpringboot.tarea1.Actividad.repositories.ProductoRepository;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productosRepository;

    // Obtener todos los productos
    @GetMapping
    public List<Producto> getAllProductos() {
        return productosRepository.findAll();
    }

    // Crear un producto
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productosRepository.save(producto);
    }
    // Eliminar un producto
    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable String id) {
        productosRepository.deleteById(id);
    }
}
