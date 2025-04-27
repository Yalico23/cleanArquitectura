package org.yalico.backend.apirest.app.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yalico.backend.apirest.app.domain.models.Cliente;
import org.yalico.backend.apirest.app.domain.ports.input.FindClientePageableUseCase;
import org.yalico.backend.apirest.app.domain.ports.output.ClienteRepositoryPort;

@RequiredArgsConstructor
public class FindClientesPageableUseCaseImpl implements FindClientePageableUseCase {

    private final ClienteRepositoryPort repositoryPort;

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return repositoryPort.findAll(pageable);
    }
}
