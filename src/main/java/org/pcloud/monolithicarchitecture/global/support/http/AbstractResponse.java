package org.pcloud.monolithicarchitecture.global.support.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import java.time.LocalDateTime;

public abstract class AbstractResponse<T> extends ResponseEntity<ResponseBody<T>> {

    public AbstractResponse(T data, Headers headers, HttpStatus httpStatus) {
        super(ResponseBody.create(data, httpStatus), headers.asHttpHeaders(), httpStatus);
    }

    public AbstractResponse(T data, HttpStatus httpStatus) {
        super(ResponseBody.create(data, httpStatus), httpStatus);
    }

    public AbstractResponse(Headers headers, HttpStatus httpStatus) {
        super(ResponseBody.create(httpStatus), headers.asHttpHeaders(), httpStatus);
    }

    public AbstractResponse(HttpStatus httpStatus) {
        super(ResponseBody.create(httpStatus), httpStatus);
    }

    public LocalDateTime getResponseDateTime() {
        return this.getBody().getResponseDateTime();
    }

    public int getStatus() {
        return this.getBody().getStatus();
    }

    public String getMessage() {
        return this.getBody().getMessage();
    }

    public T getData() {
        return this.getBody().getData();
    }
}