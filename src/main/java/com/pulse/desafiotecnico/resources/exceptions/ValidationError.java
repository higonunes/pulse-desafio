package com.pulse.desafiotecnico.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    public static final long serialVersionUID = 1L;

    private final List<FieldMessage> list = new ArrayList<>();

    public ValidationError(Long timeStamp, Integer status, String error, String message, String path) {
        super(timeStamp, status, error, message, path);
    }

    public List<FieldMessage> getErros() {
        return list;
    }

    public void addError(String fieldName, String message) {
        list.add(new FieldMessage(fieldName, message));
    }
}
