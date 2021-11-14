package org.pcloud.monolithicarchitecture.global.support.http;

import org.springframework.http.HttpStatus;

public interface StatusCode {
    // 별도로 정의한 것이 없으면 httpStatus, 있으면 정의한 내용 반환이 목표
    HttpStatus getHttpStatus();
    int getStatus();
    String getCode();
}