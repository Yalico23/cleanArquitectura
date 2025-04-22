package org.yalico.backend.apirest.app.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yalico.backend.apirest.app.application.services.ClienteService;
import org.yalico.backend.apirest.app.application.usecases.*;
import org.yalico.backend.apirest.app.domain.ports.output.ClienteRepositoryPort;

@Configuration
public class ApplicationConfig {

    @Bean
    public ClienteService clienteService(ClienteRepositoryPort clienteRepositoryPort){
        return new ClienteService(
                new FindClienteUseCaseImpl(clienteRepositoryPort),
                new ActualizarClienteUseCaseImpl(clienteRepositoryPort),
                new FindClientesUseCaseImpl(clienteRepositoryPort),
                new DeleteClienteUseCaseImpl(clienteRepositoryPort),
                new CrearClienteUseCaseImpl(clienteRepositoryPort));
    }

//    @Bean
//    public ClienteRepositoryPort repositoryPort(ClienteEntityAdapter clienteEntityRepository){
//        return clienteEntityRepository;
//    }

}
