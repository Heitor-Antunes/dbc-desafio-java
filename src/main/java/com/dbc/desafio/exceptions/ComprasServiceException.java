package com.dbc.desafio.exceptions;

public class ComprasServiceException extends RuntimeException {
    public ComprasServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}