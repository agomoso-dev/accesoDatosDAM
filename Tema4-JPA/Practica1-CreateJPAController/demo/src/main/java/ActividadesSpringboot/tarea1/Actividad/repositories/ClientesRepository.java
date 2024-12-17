package ActividadesSpringboot.tarea1.Actividad.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ActividadesSpringboot.tarea1.Actividad.entities.Cliente;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Long> {
    Optional <Cliente> findByDni(String dni);
    Optional<Cliente> findByNombreAndApellido(String nombre, String apellido);
}

