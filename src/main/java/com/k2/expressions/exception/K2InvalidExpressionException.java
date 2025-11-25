package com.k2.expressions.exception;

public class K2InvalidExpressionException extends RuntimeException {
    public K2InvalidExpressionException(String token, String message, int row, int column
    ) {
        super("'" + token + "' " + message + ". @[" + row + ", " + column + "]");
    }
}
