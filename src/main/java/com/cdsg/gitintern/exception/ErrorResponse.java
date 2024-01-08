package com.cdsg.gitintern.exception;

public class ErrorResponse extends RuntimeException {
    public ErrorResponse(String s) {
        super(s);
    }
}
