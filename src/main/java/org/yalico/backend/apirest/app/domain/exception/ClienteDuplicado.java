package org.yalico.backend.apirest.app.domain.exception;

public class ClienteDuplicado extends RuntimeException {
    public ClienteDuplicado(String message) {
        super(message);
    }
}
