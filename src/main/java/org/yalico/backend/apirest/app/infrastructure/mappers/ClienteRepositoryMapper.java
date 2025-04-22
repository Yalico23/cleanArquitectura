package org.yalico.backend.apirest.app.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.yalico.backend.apirest.app.domain.models.Cliente;
import org.yalico.backend.apirest.app.infrastructure.dto.request.ClienteDTORequest;
import org.yalico.backend.apirest.app.infrastructure.entities.ClienteEntity;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ClienteRepositoryMapper {
    ClienteEntity toClienteEntity(Cliente cliente);
    Cliente toCliente(ClienteEntity clienteEntity);

    ClienteDTORequest toClienteDTORequest(Cliente cliente);
    Cliente toCliente(ClienteDTORequest clienteDTORequest);


}
