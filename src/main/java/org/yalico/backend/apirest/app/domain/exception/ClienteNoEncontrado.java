package org.yalico.backend.apirest.app.domain.exception;

public class ClienteNoEncontrado extends RuntimeException {

    public ClienteNoEncontrado(String message) {
        super(message);
    }

}
