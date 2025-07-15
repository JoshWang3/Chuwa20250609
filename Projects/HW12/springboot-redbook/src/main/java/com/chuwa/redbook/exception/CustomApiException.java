package com.chuwa.redbook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomApiException extends RuntimeException {
    public CustomApiException(String message) {
        super(String.format("Custom Error message : '%s'", message));
    }
}
