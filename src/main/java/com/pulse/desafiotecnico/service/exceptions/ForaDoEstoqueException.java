package com.pulse.desafiotecnico.service.exceptions;

public class ForaDoEstoqueException extends RuntimeException {

    public ForaDoEstoqueException(String msg) {
        super(msg);
    }

    public ForaDoEstoqueException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
