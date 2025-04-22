package org.yalico.backend.apirest.app.application.usecases;

import lombok.RequiredArgsConstructor;
import org.yalico.backend.apirest.app.domain.models.Cliente;
import org.yalico.backend.apirest.app.domain.ports.input.FindClientesUseCase;
import org.yalico.backend.apirest.app.domain.ports.output.ClienteRepositoryPort;

import java.util.List;

@RequiredArgsConstructor
public class FindClientesUseCaseImpl implements FindClientesUseCase {

    private final ClienteRepositoryPort repositoryPort;

    @Override
    public List<Cliente> findAll() {
        return repositoryPort.findAll();
    }
}
