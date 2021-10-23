package org.pcloud.monolithicarchitecture.global.support.http;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public abstract class AbstractResponse {
    private int status;
    private String message;
    private LocalDateTime responseDateTime;

    public AbstractResponse(HttpStatus httpStatus, String overrideMessage) {
        this.status = httpStatus.value();
        this.message = overrideMessage;
        this.responseDateTime = LocalDateTime.now();
    }

    public AbstractResponse(HttpStatus httpStatus) {
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