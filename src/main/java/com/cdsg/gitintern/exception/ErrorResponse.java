package com.cdsg.gitintern.exception;

import org.apache.tomcat.util.net.jsse.JSSEUtil;

public class ErrorResponse extends RuntimeException {
    public ErrorResponse(String s) {
        super(s);
    }
}
