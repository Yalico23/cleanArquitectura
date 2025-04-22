package org.yalico.backend.apirest.app.domain.ports.output;

import org.yalico.backend.apirest.app.domain.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {

    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);
    void deleteById(Long id);
    List<Cliente> findAll();
    Boolean existByEmail(String email);
    Optional<Cliente> findByEmail(String email);
}
