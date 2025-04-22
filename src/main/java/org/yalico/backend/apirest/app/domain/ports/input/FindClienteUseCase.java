package org.yalico.backend.apirest.app.domain.ports.input;

import org.yalico.backend.apirest.app.domain.models.Cliente;

public interface FindClienteUseCase {
    Cliente findById(Long id);
}
