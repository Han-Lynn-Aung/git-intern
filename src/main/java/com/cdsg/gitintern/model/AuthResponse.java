package com.cdsg.gitintern.model;

import lombok.Data;


public class AuthResponse {
    private  String token;

    public AuthResponse(String successful,String token) {
        this.token = token;
    }
}
