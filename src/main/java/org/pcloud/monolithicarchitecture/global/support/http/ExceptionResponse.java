package org.pcloud.monolithicarchitecture.global.support.http;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ExceptionResponse extends AbstractResponse<List<FieldExplanation>> {

    public ExceptionResponse(RequestException e) {
        super(e.getFieldExplanations(), e.getStatusCode().getHttpStatus());
    }

    public ExceptionResponse(HttpStatus httpStatus) {
        super(httpStatus);
    }
}
