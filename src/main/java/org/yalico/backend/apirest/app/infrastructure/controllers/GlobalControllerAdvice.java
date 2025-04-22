package org.yalico.backend.apirest.app.infrastructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.yalico.backend.apirest.app.domain.exception.ClienteDuplicado;
import org.yalico.backend.apirest.app.domain.exception.ClienteNoEncontrado;
import org.yalico.backend.apirest.app.domain.exception.ClienteYaExistente;
import org.yalico.backend.apirest.app.domain.models.ErrorResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClienteNoEncontrado.class)
    public ErrorResponse handleClienteNotFound(ClienteNoEncontrado ex) {
        return ErrorResponse.builder()
                .code("CLIENTE_NOT_FOUND_001")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClienteYaExistente.class)
    public ErrorResponse handleClienteExiste(ClienteYaExistente ex) {
        return ErrorResponse.builder()
                .code("CLIENTE_EXISTED_002")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ClienteDuplicado.class)
    public ErrorResponse handleClienteDuplicado(ClienteDuplicado ex) {
        return ErrorResponse.builder()
                .code("CLIENTE_DUPLICATED_003")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGeneralException(Exception exception) {
        return ErrorResponse.builder()
                .code("INTERNAL_SERVER_ERROR_500")
                .message("Ocurrio un error inesperado")
                .details(List.of(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleErrorResponse(MethodArgumentNotValidException exception) {

        BindingResult result = exception.getBindingResult();

        return ErrorResponse.builder()
                .code("VALIDATION_ERROR_400")
                .message("Error de Validacion")
                .details(result.getFieldErrors()
                        .stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .toList())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
