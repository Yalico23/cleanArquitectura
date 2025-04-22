package org.yalico.backend.apirest.app.application.usecases;

import lombok.RequiredArgsConstructor;
import org.yalico.backend.apirest.app.domain.exception.ClienteNoEncontrado;
import org.yalico.backend.apirest.app.domain.models.Cliente;
import org.yalico.backend.apirest.app.domain.ports.input.FindClienteUseCase;
import org.yalico.backend.apirest.app.domain.ports.output.ClienteRepositoryPort;

import java.util.Optional;

@RequiredArgsConstructor
public class FindClienteUseCaseImpl implements FindClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public Cliente findById(Long id) {
        validarId(id);
        return clienteRepositoryPort.findById(id)
                .orElseThrow(() -> new ClienteNoEncontrado("Cliente no encontrado con id: " + id));
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id del cliente no puede ser nulo ni menor o igual a cero.");
        }
    }
}
