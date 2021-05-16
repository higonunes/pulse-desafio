package com.pulse.desafiotecnico.service.exceptions;

public class AuthorizationException extends RuntimeException {

    public AuthorizationException() {
        super("Token Inválido");
    }

    public AuthorizationException(String msg) {
        super(msg);
    }

    public AuthorizationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}