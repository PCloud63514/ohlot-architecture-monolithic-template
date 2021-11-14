package org.pcloud.monolithicarchitecture.global.support.http;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ResponseBody<T> {
    private final int status;
    private final String message;
    private final LocalDateTime responseDateTime;
    private final T data;

    public ResponseBody(int status, String message, LocalDateTime responseDateTime, T data) {
        this.status = status;
        this.message = message;
        this.responseDateTime = responseDateTime;
        this.data = data;
    }

    public ResponseBody(int status, String message, LocalDateTime responseDateTime) {
        this.status = status;
        this.message = message;
        this.responseDateTime = responseDateTime;
        this.data = null;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public LocalDateTime getResponseDateTime() {
        return this.responseDateTime;
    }

    public T getData() {
        return data;
    }

    public static <T>ResponseBody<T> create(T data, HttpStatus httpStatus) {
        return new ResponseBody<T>(httpStatus.value(), httpStatus.getReasonPhrase(), LocalDateTime.now(), data);
    }

    public static <T>ResponseBody<T> create(HttpStatus httpStatus) {
        return new ResponseBody<T>(httpStatus.value(), httpStatus.getReasonPhrase(), LocalDateTime.now());
    }
}
