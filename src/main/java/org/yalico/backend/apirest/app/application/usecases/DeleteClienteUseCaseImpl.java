package org.yalico.backend.apirest.app.application.usecases;

import lombok.RequiredArgsConstructor;
import org.yalico.backend.apirest.app.domain.exception.ClienteNoEncontrado;
import org.yalico.backend.apirest.app.domain.ports.input.DeleteClienteUseCase;
import org.yalico.backend.apirest.app.domain.ports.output.ClienteRepositoryPort;

@RequiredArgsConstructor
public class DeleteClienteUseCaseImpl implements DeleteClienteUseCase {

    private final ClienteRepositoryPort repositoryPort;

    @Override
    public void deleteCliente(Long id) {
        validarId(id);

        boolean existe = repositoryPort.findById(id).isPresent();
        if (!existe) {
            throw new ClienteNoEncontrado("No se puede eliminar. Cliente no encontrado con id: " + id);
        }

        repositoryPort.deleteById(id);
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id del cliente no puede ser nulo ni menor o igual a cero.");
        }
    }
}
