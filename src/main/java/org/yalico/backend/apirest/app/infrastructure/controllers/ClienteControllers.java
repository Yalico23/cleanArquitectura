package org.yalico.backend.apirest.app.infrastructure.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yalico.backend.apirest.app.application.services.ClienteService;
import org.yalico.backend.apirest.app.domain.models.Cliente;
import org.yalico.backend.apirest.app.infrastructure.dto.request.ClienteDTORequest;
import org.yalico.backend.apirest.app.infrastructure.mappers.ClienteRepositoryMapper;

import java.util.List;

@RestController
@RequestMapping("api/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteControllers {

    private final ClienteService clienteService;
    private final ClienteRepositoryMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(ClienteControllers.class);

    // nota : Obtener clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> findClientes() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    // nota : Obtener cliente con paginable
    @GetMapping("/clientes/page")
    public ResponseEntity<Page<Cliente>> findClientesPageable(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "4") Integer size) {
        logger.info("Buscando clientes con paginable");
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(clienteService.findAll(pageable));
    }

    // nota : Obtener cliente con paginable y filtro
    @GetMapping("/clientes/search")
    public ResponseEntity<Page<Cliente>> searchClientes(
            @RequestParam(name = "filter", required = false) String filter,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "direccion", defaultValue = "asc") String direccion) {

        logger.info("Buscando clientes con filtro: {}", filter);

        Sort sort = direccion.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Cliente> result;
        if (filter == null || filter.trim().isEmpty()) {
            result = clienteService.findAll(pageable);
        } else {
            result = clienteService.findByFilter(filter, pageable);
        }
        return ResponseEntity.ok(result);
    }

    // nota : Obtener cliente por id
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable("id") Long id) {
        logger.info("Buscando cliente por ID: {}", id);
        return ResponseEntity.ok(clienteService.findById(id));
    }

    // nota : Crear Cliente
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody ClienteDTORequest cliente) {
        logger.info("Creando cliente: {}", cliente);
        return ResponseEntity.ok(clienteService.crearCliente(mapper.toCliente(cliente)));
    }

    // nota : Actualziar Cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualziarCLiente(@Valid @RequestBody ClienteDTORequest cliente, @PathVariable("id") Long id) {
        logger.info("Actualizando cliente: {}", cliente);
        return ResponseEntity.ok(clienteService.clienteActualizar(mapper.toCliente(cliente), id));
    }

    // nota : Eliminar Cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable("id") Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
