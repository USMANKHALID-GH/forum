package com.usman.forum.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class  BusinessException extends  RuntimeException {
    private static final long serialVersionUID = 1L;

    @Getter
    private HttpStatus httpStatus;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
