package org.yalico.backend.apirest.app.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.yalico.backend.apirest.app.domain.exception.ClienteYaExistente;
import org.yalico.backend.apirest.app.domain.models.Cliente;
import org.yalico.backend.apirest.app.domain.ports.input.CrearClienteUseCase;
import org.yalico.backend.apirest.app.domain.ports.output.ClienteRepositoryPort;

@RequiredArgsConstructor
public class CrearClienteUseCaseImpl implements CrearClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public Cliente crearCliente(Cliente cliente) {
        if (cliente == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "El cliente no puede ser nulo.");
        }

        if(clienteRepositoryPort.existByEmail(cliente.getEmail())){
            throw new ClienteYaExistente("Ya existe un cliente registrado con el email: " + cliente.getEmail());
        }

        return clienteRepositoryPort.save(cliente);
    }
}
