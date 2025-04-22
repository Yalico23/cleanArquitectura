package org.yalico.backend.apirest.app.infrastructure.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public ResponseEntity<List<Cliente>> findClientes(){
        return ResponseEntity.ok(clienteService.findAll());
    }
    // nota : Obtener cliente por id
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable("id") Long id){
        logger.info("Buscando cliente por ID: {}", id);
        return ResponseEntity.ok(clienteService.findById(id));
    }

    // nota : Crear Cliente
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody ClienteDTORequest cliente){
        logger.info("Creando cliente: {}", cliente);
        return ResponseEntity.ok(clienteService.crearCliente(mapper.toCliente(cliente)));
    }

    // nota : Actualziar Cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualziarCLiente(@Valid @RequestBody ClienteDTORequest cliente, @PathVariable("id") Long id){
        logger.info("Actualizando cliente: {}", cliente);
        return ResponseEntity.ok(clienteService.clienteActualizar(mapper.toCliente(cliente), id));
    }

    // nota : Eliminar Cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable("id") Long id){
        clienteService.deleteCliente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
