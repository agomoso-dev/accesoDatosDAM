package ActividadesSpringboot.tarea1.Actividad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ActividadesSpringboot.tarea1.Actividad.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {
    //SELECT @Query (nativa SQL o con parámetros con nombre)
    @Query(value = "SELECT * FROM productos WHERE codigo = ?1", nativeQuery = true)
    List<Producto> findByCodigo(String codigo);
}
