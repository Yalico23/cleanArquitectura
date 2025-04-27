package org.yalico.backend.apirest.app.application.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yalico.backend.apirest.app.domain.models.Cliente;
import org.yalico.backend.apirest.app.domain.ports.input.*;

import java.util.List;

@Service
public class ClienteService implements FindClientesUseCase,
        FindClientePageableUseCase,
        ActualizarClienteUseCase,
        CrearClienteUseCase,
        DeleteClienteUseCase,
        FindClienteUseCase {

    private final FindClienteUseCase findClienteUseCase;
    private final ActualizarClienteUseCase actualizarClienteUseCase;
    private final FindClientesUseCase findClientesUseCase;
    private final DeleteClienteUseCase deleteClienteUseCase;
    private final CrearClienteUseCase crearClienteUseCase;
    private final FindClientePageableUseCase findClientesPageableUseCase;

    public ClienteService(FindClienteUseCase findClienteUseCase,
                          ActualizarClienteUseCase actualizarClienteUseCase,
                          FindClientesUseCase findClientesUseCase,
                          DeleteClienteUseCase deleteClienteUseCase,
                          CrearClienteUseCase crearClienteUseCase,
                          FindClientePageableUseCase findClientesPageableUseCase) {
        this.findClienteUseCase = findClienteUseCase;
        this.actualizarClienteUseCase = actualizarClienteUseCase;
        this.findClientesUseCase = findClientesUseCase;
        this.deleteClienteUseCase = deleteClienteUseCase;
        this.crearClienteUseCase = crearClienteUseCase;
        this.findClientesPageableUseCase = findClientesPageableUseCase;
    }

    @Transactional
    @Override
    public Cliente clienteActualizar(Cliente cliente, Long id) {
        return actualizarClienteUseCase.clienteActualizar(cliente, id);
    }

    @Transactional
    @Override
    public Cliente crearCliente(Cliente cliente) {
        return crearClienteUseCase.crearCliente(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Long id) {
        return findClienteUseCase.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cliente> findAll() {
        return findClientesUseCase.findAll();
    }

    @Transactional
    @Override
    public void deleteCliente(Long id) {
        deleteClienteUseCase.deleteCliente(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return findClientesPageableUseCase.findAll(pageable);
    }
}
