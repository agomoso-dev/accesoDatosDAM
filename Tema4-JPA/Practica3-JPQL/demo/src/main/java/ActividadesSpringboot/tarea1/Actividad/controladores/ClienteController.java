package ActividadesSpringboot.tarea1.Actividad.controladores;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ActividadesSpringboot.tarea1.Actividad.entities.Cliente;
import ActividadesSpringboot.tarea1.Actividad.entities.Proveedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private EntityManager em;

    @GetMapping("/busqueda")
    public List<Cliente> busqueda(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) String dni,
            @RequestParam(required = false) String direccion,
            @RequestParam(required = false) String telefono,
            @RequestParam(required = false) Long proveedorId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaCompraDesde,
            @RequestParam(required = false) Boolean compraReciente,
            @RequestParam(required = false) String ordenarPor) {


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        Root<Cliente> cliente = query.from(Cliente.class);

        Predicate predicate = cb.conjunction();

        if (nombre != null) {
            predicate = cb.and(predicate, cb.like(cliente.get("nombre"), "%" + nombre + "%"));
        }
        if (apellido != null) {
            predicate = cb.and(predicate, cb.like(cliente.get("apellido"), "%" + apellido + "%"));
        }
        if (dni != null) {
            predicate = cb.and(predicate, cb.equal(cliente.get("dni"), dni));
        }
        if (direccion != null) {
            predicate = cb.and(predicate, cb.like(cliente.get("direccion"), "%" + direccion + "%"));
        }
        if (telefono != null) {
            predicate = cb.and(predicate, cb.like(cliente.get("telefono"), "%" + telefono + "%"));
        }
        if (proveedorId != null) {
            Join<Cliente, Proveedor> proveedorJoin = cliente.join("proveedor");
            predicate = cb.and(predicate, cb.equal(proveedorJoin.get("id"), proveedorId));
        }
        // Filtro por fecha de compra - USO DEL GREATERTHAN OR EQUAL TO
        if (fechaCompraDesde != null) {
            predicate = cb.and(predicate, cb.greaterThanOrEqualTo(cliente.get("fechaCompra"), fechaCompraDesde));
        }
        if (compraReciente != null && compraReciente) {
            predicate = cb.and(predicate, cb.greaterThan(cliente.get("fechaCompra"), cb.currentDate()));
        }

        query.where(predicate);
        // Ordenamiento por nombre y apellido
        if (ordenarPor != null && !ordenarPor.isEmpty()) {
            if (direccion.equalsIgnoreCase("asc")) {
                query.orderBy(cb.asc(cliente.get(ordenarPor)));
            } else if (direccion.equalsIgnoreCase("desc")) {
                query.orderBy(cb.desc(cliente.get(ordenarPor)));
            }
        } else {
            query.orderBy(cb.asc(cliente.get("nombre")));
        }
        return em.createQuery(query).getResultList();
    }
}