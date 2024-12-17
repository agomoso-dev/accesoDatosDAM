package ActividadesSpringboot.tarea1.Actividad.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ActividadesSpringboot.tarea1.Actividad.entities.Proveedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/busqueda")
    public List<Proveedor> busqueda(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String direccion,
            @RequestParam(required = false) String telefono,
            @RequestParam(required = false) String ordenarPor) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Proveedor> cq = cb.createQuery(Proveedor.class);
        Root<Proveedor> proveedorRoot = cq.from(Proveedor.class);
        
        // Predicados para filtrar
        Predicate finalPredicate = cb.conjunction();
        // LIKE para nombre
        if (nombre != null && !nombre.isEmpty()) {
            finalPredicate = cb.and(finalPredicate, 
                cb.like(cb.lower(proveedorRoot.get("nombre")), "%" + nombre.toLowerCase() + "%"));
        }
        // LIKE para direccion
        if (direccion != null && !direccion.isEmpty()) {
            finalPredicate = cb.and(finalPredicate, 
                cb.like(cb.lower(proveedorRoot.get("direccion")), "%" + direccion.toLowerCase() + "%"));
        }
        
        // LIKE para telefono
        if (telefono != null && !telefono.isEmpty()) {
            finalPredicate = cb.and(finalPredicate, 
                cb.like(proveedorRoot.get("telefono"), "%" + telefono + "%"));
        }

        cq.where(finalPredicate);
        
        // ORDER BY
        if (ordenarPor != null && !ordenarPor.isEmpty()) {
            if (ordenarPor.equalsIgnoreCase("asc")) {
                cq.orderBy(cb.asc(proveedorRoot.get("nombre")));
            } else if (ordenarPor.equalsIgnoreCase("desc")) {
                cq.orderBy(cb.desc(proveedorRoot.get("nombre")));
            }
        }

        return entityManager.createQuery(cq).getResultList();
    }
}
    
