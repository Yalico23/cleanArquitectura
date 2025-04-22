package org.yalico.backend.apirest.app.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Cliente {

    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private LocalDate createAt;

}
