package org.yalico.backend.apirest.app.domain.ports.output;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yalico.backend.apirest.app.domain.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {

    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);
    void deleteById(Long id);
    List<Cliente> findAll();
    Page<Cliente> findAll(Pageable pageable);
    Page<Cliente> findByNombreContainingOrEmailContaining(String nombre, String email, Pageable pageable);
    Boolean existByEmail(String email);
    Optional<Cliente> findByEmail(String email);
}
