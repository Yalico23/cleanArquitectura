package org.yalico.backend.apirest.app.domain.ports.input;

import org.yalico.backend.apirest.app.domain.models.Cliente;

public interface ActualizarClienteUseCase {
    Cliente clienteActualizar(Cliente cliente, Long idCliente);
}
