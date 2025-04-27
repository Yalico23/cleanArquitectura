package org.yalico.backend.apirest.app.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.yalico.backend.apirest.app.domain.models.Cliente;
import org.yalico.backend.apirest.app.domain.ports.output.ClienteRepositoryPort;
import org.yalico.backend.apirest.app.infrastructure.mappers.ClienteRepositoryMapper;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ClienteEntityAdapter implements ClienteRepositoryPort {

    private final ClienteEntityRepository clienteEntityRepository;
    private final ClienteRepositoryMapper repositoryMapper;

    @Override
    public Cliente save(Cliente cliente) {
        return repositoryMapper.toCliente(clienteEntityRepository.save(repositoryMapper.toClienteEntity(cliente)));
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteEntityRepository.findById(id)
                .map(repositoryMapper::toCliente);
    }

    @Override
    public void deleteById(Long id) {
        clienteEntityRepository.deleteById(id);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteEntityRepository.findAll()
                .stream()
                .map(repositoryMapper::toCliente)
                .toList();
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteEntityRepository.findAll(pageable)
                .map(repositoryMapper::toCliente);
    }

    @Override
    public Boolean existByEmail(String email) {
        return clienteEntityRepository.existsByEmail(email);
    }

    @Override
    public Optional<Cliente> findByEmail(String email) {
        return clienteEntityRepository.findByEmail(email)
                .map(repositoryMapper::toCliente);
    }
}
