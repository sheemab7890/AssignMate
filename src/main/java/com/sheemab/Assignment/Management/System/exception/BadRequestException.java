package com.sheemab.Assignment.Management.System.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)  // 400 Bad Request
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
