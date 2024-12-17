package ActividadesSpringboot.tarea1.Actividad.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ActividadesSpringboot.tarea1.Actividad.entities.Cliente;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Long> {

    // Búsquedas básicas
    Optional<Cliente> findByDni(String dni);
    Optional<Cliente> findByNombreAndApellido(String nombre, String apellido);

    // Consulta personalizada para buscar por nombre
    @Query("SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    List<Cliente> findByName(String nombre);

    // DELETE
    @Modifying
    @Transactional
    @Query("DELETE FROM Cliente c WHERE c.apellido = :apellido")
    void deleteByApellido(String apellido);

    // UPDATE
    @Modifying
    @Transactional
    @Query("UPDATE Cliente c SET c.direccion = :direccion WHERE c.dni = :dni")
    int updateDireccionByDni(String direccion, String dni);
}
