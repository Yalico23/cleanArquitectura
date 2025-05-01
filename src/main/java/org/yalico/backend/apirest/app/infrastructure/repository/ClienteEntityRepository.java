package org.yalico.backend.apirest.app.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yalico.backend.apirest.app.domain.models.Cliente;
import org.yalico.backend.apirest.app.infrastructure.entities.ClienteEntity;

import java.util.Optional;

@Repository
public interface ClienteEntityRepository extends JpaRepository<ClienteEntity, Long> {
    Boolean existsByEmail(String email);
    Optional<ClienteEntity> findByEmail(String email);
    Page<ClienteEntity> findByNombreContainingOrEmailContaining(String nombre, String email, Pageable pageable);
}