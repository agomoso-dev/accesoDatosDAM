package ActividadesSpringboot.tarea1.Actividad.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ActividadesSpringboot.tarea1.Actividad.entities.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@RestController
@RequestMapping("/productos")
public class ProductoController {
 @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/busqueda")
    public List<Producto> busqueda(
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax,
            @RequestParam(required = false) String ordenarPor) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
        Root<Producto> productoRoot = cq.from(Producto.class);

        // Predicados para filtrar
        Predicate finalPredicate = cb.conjunction();

        // LIKE para código
        if (codigo != null && !codigo.isEmpty()) {
            finalPredicate = cb.and(finalPredicate, 
                cb.like(productoRoot.get("codigo"), "%" + codigo + "%"));
        }

        // LIKE para nombre
        if (nombre != null && !nombre.isEmpty()) {
            finalPredicate = cb.and(finalPredicate, 
                cb.like(productoRoot.get("nombre"), "%" + nombre + "%"));
        }

        // LIKE para descripción
        if (descripcion != null && !descripcion.isEmpty()) {
            finalPredicate = cb.and(finalPredicate, 
                cb.like(productoRoot.get("descripcion"), "%" + descripcion + "%"));
        }

        // AND para rango de precios
        if (precioMin != null) {
            finalPredicate = cb.and(finalPredicate, 
                cb.greaterThanOrEqualTo(productoRoot.get("precio"), precioMin));
        }
        if (precioMax != null) {
            finalPredicate = cb.and(finalPredicate, 
                cb.lessThanOrEqualTo(productoRoot.get("precio"), precioMax));
        }

        cq.where(finalPredicate);

        // ORDER BY
        if (ordenarPor != null && !ordenarPor.isEmpty()) {
            if (ordenarPor.equalsIgnoreCase("asc")) {
                cq.orderBy(cb.asc(productoRoot.get("nombre")));
            } else if (ordenarPor.equalsIgnoreCase("desc")) {
                cq.orderBy(cb.desc(productoRoot.get("nombre")));
            }
        }

        return entityManager.createQuery(cq).getResultList();
    }
}
