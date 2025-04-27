package org.yalico.backend.apirest.app.domain.ports.input;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yalico.backend.apirest.app.domain.models.Cliente;

public interface FindClientePageableUseCase {
    Page<Cliente> findAll(Pageable pageable);
}
