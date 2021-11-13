package org.pcloud.monolithicarchitecture.global.support.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public abstract class AbstractResponse extends ResponseEntity<Object> {
    private int status;
    private String message;
    private LocalDateTime responseDateTime;

    public AbstractResponse(HttpStatus httpStatus, String overrideMessage) {
        super(httpStatus);
        this.status = httpStatus.value();
        this.message = overrideMessage;
        this.responseDateTime = LocalDateTime.now();
    }

    public AbstractResponse(HttpStatus httpStatus) {
        super(httpStatus);
        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.responseDateTime = LocalDateTime.now();
    }

    public LocalDateTime getResponseDateTime() {
        return responseDateTime;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}