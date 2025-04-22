package org.yalico.backend.apirest.app.infrastructure.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteDTORequest(
        @NotBlank String nombre,
        @NotBlank String apellidos,
        @NotBlank @Email String email
) {
}
