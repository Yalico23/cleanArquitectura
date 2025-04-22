package org.yalico.backend.apirest.app.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.yalico.backend.apirest.app.domain.exception.ClienteDuplicado;
import org.yalico.backend.apirest.app.domain.exception.ClienteNoEncontrado;
import org.yalico.backend.apirest.app.domain.models.Cliente;
import org.yalico.backend.apirest.app.domain.ports.input.ActualizarClienteUseCase;
import org.yalico.backend.apirest.app.domain.ports.output.ClienteRepositoryPort;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class ActualizarClienteUseCaseImpl implements ActualizarClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public Cliente clienteActualizar(Cliente cliente, Long id) {
        if (id == null || id <= 0) {
            log.warn("ID inválido para actualización: {}", id);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "El ID del cliente debe ser un número positivo");
        }

        if (cliente == null) {
            log.warn("Se intentó actualizar un cliente nulo para el ID: {}", id);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "El objeto Cliente no puede ser nulo");

        }
        clienteRepositoryPort.findById(id)
                .orElseThrow(() -> new ClienteNoEncontrado("No se encontró un cliente con el ID: " + id));

        Optional<Cliente> duplicado = clienteRepositoryPort.findByEmail(cliente.getEmail());
        if(duplicado.isPresent() && !duplicado.get().getId().equals(id)){
            log.warn("Intento de duplicación de cliente con email existente: {}", cliente.getEmail());
            throw new ClienteDuplicado("Ya existe otro cliente con el mismo email "  + cliente.getEmail());
        }

        cliente.setId(id);
        cliente.setCreateAt(LocalDate.now());
        return clienteRepositoryPort.save(cliente);

    }
}
