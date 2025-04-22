package org.yalico.backend.apirest.app.domain.ports.input;

import org.yalico.backend.apirest.app.domain.models.Cliente;

import java.util.List;

public interface FindClientesUseCase {
    List<Cliente> findAll();
}
