package org.yalico.backend.apirest.app.domain.exception;

public class ClienteYaExistente extends RuntimeException {
    public ClienteYaExistente(String message) {
        super(message);
    }
}
