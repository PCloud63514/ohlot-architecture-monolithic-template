package org.pcloud.monolithicarchitecture.global.support.http;

import org.springframework.http.HttpStatus;

public class RequestResponse<T> extends AbstractResponse {
    private T data;

    private RequestResponse(T data) {
        super(HttpStatus.OK);
        this.data = data;
    }

    private RequestResponse(HttpStatus httpStatus, T data) {
        super(httpStatus);
        this.data = data;
    }

    public static <T>RequestResponse<T> create(HttpStatus httpStatus, T data) {
        return new RequestResponse<T>(httpStatus, data);
    }

    public static <T>RequestResponse<T> create(T data) {
        return new RequestResponse<T>(data);
    }

    public T getData() {
        return data;
    }
}