package ActividadesSpringboot.tarea1.Actividad.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ActividadesSpringboot.tarea1.Actividad.entities.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    // Usando @Param con parámetros con nombre
    @Query("SELECT p FROM Proveedor p WHERE p.cliente.nombre = :nombreCliente")
    List<Proveedor> findByClienteNombre(@Param("nombreCliente") String nombreCliente);
    Optional<Proveedor> findByCodigoProveedor(String codigoProveedor);

}
