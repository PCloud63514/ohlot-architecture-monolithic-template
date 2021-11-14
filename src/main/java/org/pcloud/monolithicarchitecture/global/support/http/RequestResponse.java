package org.pcloud.monolithicarchitecture.global.support.http;

import org.springframework.http.HttpStatus;

public class RequestResponse<T> extends AbstractResponse<T> {

    private RequestResponse(T data) {
        super(data, HttpStatus.OK);
    }

    private RequestResponse(HttpStatus httpStatus, T data) {
        super(data, httpStatus);
    }

    public static <T>RequestResponse<T> create(HttpStatus httpStatus, T data) {
        return new RequestResponse<T>(httpStatus, data);
    }

    public static <T>RequestResponse<T> create(T data) {
        return new RequestResponse<T>(data);
    }
}