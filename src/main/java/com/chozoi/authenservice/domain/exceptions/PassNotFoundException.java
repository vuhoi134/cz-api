package com.chozoi.authenservice.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PassNotFoundException extends RuntimeException{
    public PassNotFoundException(String msg) {
        super(msg);
    }
}