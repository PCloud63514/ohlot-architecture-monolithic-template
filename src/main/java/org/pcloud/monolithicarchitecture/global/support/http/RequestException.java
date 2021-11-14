package org.pcloud.monolithicarchitecture.global.support.http;

import java.util.ArrayList;
import java.util.List;

public abstract class RequestException extends Throwable {
    private final StatusCode statusCode;
    private final List<FieldExplanation> fieldExplanations;

    public RequestException(StatusCode statusCode) {
        super(statusCode.getCode());
        this.statusCode = statusCode;
        this.fieldExplanations = new ArrayList<FieldExplanation>();
    }

    public void addFieldExplanation(RequestException e) {
        this.addFieldExplanation(e.statusCode.getCode(), e.getFieldExplanations());
//        this.fieldExplanations.addAll(e.getFieldExplanations());
    }

    public void addFieldExplanation(String explanation) {
        this.fieldExplanations.add(new FieldExplanation(null, explanation));

    }

    public void addFieldExplanation(String field, Object explanation) {
        this.fieldExplanations.add(new FieldExplanation(field, explanation));
    }

    public List<FieldExplanation> getFieldExplanations() {
        return this.fieldExplanations;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
